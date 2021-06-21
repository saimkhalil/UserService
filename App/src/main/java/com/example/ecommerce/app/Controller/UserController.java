package com.example.ecommerce.app.Controller;

import com.example.ecommerce.app.Service.UserService;
import com.example.ecommerce.app.Utils.SError;
import com.example.ecommerce.app.Validator.UserRequestValidator;
import com.example.ecommerce.contracts.Request.UserRequest;
import com.example.ecommerce.contracts.Response.ResponseModel;
import com.example.ecommerce.contracts.Response.UserResponse;
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
    private UserRequestValidator requestValidator;

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
    public ResponseModel<UserResponse> fetchUserByName(@RequestParam String id)
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
    public ResponseModel<List<UserResponse>> fetchUsersByCity(@RequestParam String city)
    {
        ResponseModel<List<UserResponse>> responseModel = new ResponseModel<>();
        if (null == city || "".equals(city.trim()))
        {
            responseModel.setMessage("Empty user city");
            responseModel.setHttpStatus(HttpStatus.BAD_REQUEST);
        }
        else
        {
            List<UserResponse> userResponses = userService.fetchUsers(city);
            if (userResponses.size() == 0)
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

        }
        return responseModel;
    }

    @RequestMapping(value = "/updateStatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<String> updateStatus(@RequestParam("id") String id, @RequestParam("status") boolean status)
    {
        ResponseModel<String> responseModel = new ResponseModel<>();

        if (null == id || "".equals(id.trim()))
        {
            responseModel.setMessage("Empty user id");
            responseModel.setHttpStatus(HttpStatus.BAD_REQUEST);
        }


        else
        {
            String userResponse = userService.updateStatus(id, status);
            responseModel.setData(userResponse);
            responseModel.setHttpStatus(HttpStatus.OK);

        }

        return responseModel;
    }


}
