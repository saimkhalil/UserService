package com.example.ecommerce.app.Service;

import com.example.ecommerce.app.Persistence.Model.EcommerceUser;
import com.example.ecommerce.app.Persistence.Repository.UserRepository;
import com.example.ecommerce.contracts.Request.UserRequest;
import com.example.ecommerce.contracts.Response.ResponseModel;
import com.example.ecommerce.contracts.Response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    public ResponseModel<String> createUser(UserRequest userRequest)
    {
        EcommerceUser ecommerceUser = new EcommerceUser();
        ecommerceUser.setName(userRequest.getName());
        ecommerceUser.setContact(userRequest.getContact());
        ecommerceUser.setEmail(userRequest.getEmail());
        ecommerceUser.setCity(userRequest.getCity());
        ecommerceUser.setCountry(userRequest.getCountry());
        System.out.println("Creating a user ---->" + ecommerceUser);
        ecommerceUser = userRepository.save(ecommerceUser);
        System.out.println("Saved ------> " + ecommerceUser );
        ResponseModel<String> responseModel = new ResponseModel<String>();
        responseModel.setHttpStatus(HttpStatus.OK);
        responseModel.setMessage("User created successfully");
        responseModel.setData("The id is " + ecommerceUser.getId());
        return responseModel;
    }

    public UserResponse fetchUserById(String id)
    {
        EcommerceUser ecommerceUser =  userRepository.findById(id).get();
        UserResponse userResponse = new UserResponse();
        userResponse.setName(ecommerceUser.getName());
        userResponse.setEmail(ecommerceUser.getEmail());
        userResponse.setCountry(ecommerceUser.getCountry());
        userResponse.setCity(ecommerceUser.getCity());
        userResponse.setContact(ecommerceUser.getContact());

        return userResponse;
    }

    public List<UserResponse> fetchUsers(String city)
    {
        List<EcommerceUser> ecommerceUsers = userRepository.findByCity(city);
        List<UserResponse> userResponses = new ArrayList<>();

        for (EcommerceUser user : ecommerceUsers)
        {
            UserResponse u = new UserResponse();
            u.setName(user.getName());
            u.setContact(user.getContact());
            u.setCity(user.getCity());
            u.setCountry(user.getCountry());
            u.setEmail(user.getEmail());
            userResponses.add(u);
        }
        return userResponses;
    }
}
