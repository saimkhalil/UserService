package com.example.ecommerce.app.Controller;

import com.example.ecommerce.app.Service.UserService;
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

    @RequestMapping(value = "/createUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<String> createUser(@RequestBody UserRequest userRequest)
    {
        ResponseModel<String> responseModel = new ResponseModel<String>();

        if (null == userRequest.getName() || "".equals(userRequest.getName().trim()))
        {
            responseModel.setMessage("Empty user name");
            responseModel.setHttpStatus(HttpStatus.BAD_REQUEST);
        }
        else if (null == userRequest.getContact() || "".equals(userRequest.getContact().trim()))
        {
            responseModel.setMessage("Empty user contact");
            responseModel.setHttpStatus(HttpStatus.BAD_REQUEST);
        }
        else if (null == userRequest.getEmail() || "".equals(userRequest.getEmail().trim()))
        {
            responseModel.setMessage("Empty user email");
            responseModel.setHttpStatus(HttpStatus.BAD_REQUEST);
        }
        else if (null == userRequest.getCountry() || "".equals(userRequest.getCountry().trim()))
        {
            responseModel.setMessage("Empty user country");
            responseModel.setHttpStatus(HttpStatus.BAD_REQUEST);
        }
        else if (null == userRequest.getCity() || "".equals(userRequest.getCity().trim()))
        {
            responseModel.setMessage("Empty user city");
            responseModel.setHttpStatus(HttpStatus.BAD_REQUEST);
        }
        else
        {
            responseModel = userService.createUser(userRequest);
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

    @RequestMapping(value = "/deactivateById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel<String> deactivateById(@RequestParam String id)
    {
        ResponseModel<String> responseModel = new ResponseModel<>();

        if (null == id || "".equals(id.trim()))
        {
            responseModel.setMessage("Empty user id");
            responseModel.setHttpStatus(HttpStatus.BAD_REQUEST);
        }


        else
        {
            String userResponse = userService.deactivateById(id);
            responseModel.setData(userResponse);
            responseModel.setHttpStatus(HttpStatus.OK);

        }

        return responseModel;
    }


}
