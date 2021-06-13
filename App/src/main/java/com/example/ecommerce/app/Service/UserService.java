package com.example.ecommerce.app.Service;

import com.example.ecommerce.app.Persistence.Model.EcommerceUser;
import com.example.ecommerce.app.Persistence.Repository.UserRepository;
import com.example.ecommerce.contracts.Request.UserRequest;
import com.example.ecommerce.contracts.Response.UserBasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    public UserBasicResponse createUser(UserRequest userRequest)
    {
        EcommerceUser ecommerceUser = new EcommerceUser();
        ecommerceUser.setName(userRequest.getName());
        ecommerceUser.setContact(userRequest.getContact());
        ecommerceUser.setEmail(userRequest.getEmail());
        System.out.println("Creating a user ---->" + ecommerceUser);
        ecommerceUser = userRepository.save(ecommerceUser);
        System.out.println("Saved ------> " + ecommerceUser );
        UserBasicResponse userBasicResponse = new UserBasicResponse();
        userBasicResponse.setHttpStatus(HttpStatus.OK);
        userBasicResponse.setMessage("User created successfully");
        return userBasicResponse;
    }
}
