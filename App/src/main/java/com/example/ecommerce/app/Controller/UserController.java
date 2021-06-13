package com.example.ecommerce.app.Controller;

import com.example.ecommerce.app.Service.UserService;
import com.example.ecommerce.contracts.Request.UserRequest;
import com.example.ecommerce.contracts.Response.UserBasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/createUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserBasicResponse createUser(@RequestBody UserRequest userRequest)
    {
        UserBasicResponse userBasicResponse = new UserBasicResponse();

        if (null == userRequest.getName() || "".equals(userRequest.getName()))
        {
            userBasicResponse.setMessage("Empty user name");
            userBasicResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        }
        else if (null == userRequest.getContact() || "".equals(userRequest.getContact()))
        {
            userBasicResponse.setMessage("Empty user contact");
            userBasicResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        }
        else if (null == userRequest.getEmail() || "".equals(userRequest.getEmail()))
        {
            userBasicResponse.setMessage("Empty user email");
            userBasicResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        }
        else
        {
            userBasicResponse = userService.createUser(userRequest);
        }

        return userBasicResponse;
    }


}
