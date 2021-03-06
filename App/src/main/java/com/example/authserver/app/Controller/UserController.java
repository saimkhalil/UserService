package com.example.authserver.app.Controller;

import com.example.authserver.app.Service.UserService;
import com.example.authserver.app.Utils.SError;
import com.example.authserver.app.Validator.UserValidator;
import com.example.authserver.contracts.Enums.City;
import com.example.authserver.contracts.Request.UserRequest;
import com.example.authserver.contracts.Response.ResponseModel;
import com.example.authserver.contracts.Response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator requestValidator;

    @RequestMapping(value = "/createUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<String> createUser(@RequestBody UserRequest userRequest)
    {
        ResponseModel<String> responseModel = new ResponseModel<String>();

        try {
                requestValidator.validateUserRequest(userRequest);
                responseModel = userService.createUser(userRequest);
        }
        catch (SError se)
        {
            responseModel.setMessage("Invalid input : " + se.getMessage());
            responseModel.setHttpStatus(se.getHttpStatus());
        }
        return responseModel;
    }

    @RequestMapping(value= "/fetchUserById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<UserResponse> fetchUserByName(@RequestParam("id") String id)
    {
        ResponseModel<UserResponse> responseModel = new ResponseModel<>();

        if (null == id || "".equals(id.trim()))
        {
            responseModel.setMessage("Empty user id");
            responseModel.setHttpStatus(HttpStatus.BAD_REQUEST);
        }

        else
        {
            responseModel = userService.fetchUserById(id);
        }

        return responseModel;
    }

    @RequestMapping(value= "/fetchUsers", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<List<UserResponse>> fetchUsersByCity(@RequestParam City city)
    {
        ResponseModel<List<UserResponse>> responseModel = new ResponseModel<>();

        List<UserResponse> userResponses = userService.fetchUsers(city.getVal());
        if (userResponses.isEmpty())
        {
            responseModel.setMessage("No user exists living in the following city");
            responseModel.setHttpStatus(HttpStatus.BAD_REQUEST);
            responseModel.setData(null);
        }
        else
        {
            responseModel.setData(userResponses);
            responseModel.setHttpStatus(HttpStatus.OK);
            responseModel.setMessage("Users fetched by city");
        }

        return responseModel;
    }

    @RequestMapping(value = "/updateStatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<String> updateStatus(@RequestParam("id") String id, @RequestParam("status") boolean status)
    {
        ResponseModel<String> responseModel = new ResponseModel<>();

        //validate both the fields in validator.

        try
        {
            requestValidator.validateStatusRequest(id, status);
            String userResponse = userService.updateStatus(id, status);
            responseModel.setData(userResponse);
            responseModel.setHttpStatus(HttpStatus.OK);

        }
        catch (SError se)
        {
            throw new NullPointerException();
        }


        return responseModel;
    }

    //create an api to delete a user. Only Admin can call this api. If any other user tries to delete a user throw Forbidden exception.

    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<String> deleteUser(@RequestParam("id") String callingUserId , @RequestParam("id") String userId)
    {
        ResponseModel<String> responseModel = new ResponseModel<>();
        try
        {
            requestValidator.deleteStatusRequest(callingUserId, userId);
            String userResponse = userService.deletedUser(callingUserId, userId);
            responseModel.setData(userResponse);

        }
        catch (SError se)
        {
            throw new NullPointerException();
        }

        return responseModel;

    }

}
