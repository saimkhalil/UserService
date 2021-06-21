package com.example.ecommerce.app.Service;

import com.example.ecommerce.app.Persistence.Model.User;
import com.example.ecommerce.app.Persistence.Repository.UserRepoCustom;
import com.example.ecommerce.contracts.Request.UserRequest;
import com.example.ecommerce.contracts.Response.ResponseModel;
import com.example.ecommerce.contracts.Response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    @Autowired
    private UserRepoCustom userRepository;

    public ResponseModel<String> createUser(UserRequest userRequest)
    {
        User user = new User();
        user.setName(userRequest.getName());
        user.setContact(userRequest.getContact());
        user.setEmail(userRequest.getEmail());
        user.setCity(userRequest.getCity());
        user.setCountry(userRequest.getCountry());
        user.setRoles(userRequest.getRoles());
        user.setActive(true);
        long currentTime = System.currentTimeMillis();
        user.setCreationTime(currentTime);
        System.out.println("Creating a user ---->" + user);
        user = userRepository.save(user);
        System.out.println("Saved ------> " + user);
        ResponseModel<String> responseModel = new ResponseModel<String>();
        responseModel.setHttpStatus(HttpStatus.OK);
        responseModel.setMessage("User created successfully");
        responseModel.setData("The id is " + user.getId());
        return responseModel;
    }

    public ResponseModel<UserResponse> fetchUserById(String id)
    {
        Optional<User> optionalUser = userRepository.findById(id);
        ResponseModel<UserResponse> response = new ResponseModel<>();
        if (optionalUser.isPresent())
        {
            User user = optionalUser.get();
            UserResponse userResponse = new UserResponse();
            userResponse.setName(user.getName());
            userResponse.setEmail(user.getEmail());
            userResponse.setCountry(user.getCountry());
            userResponse.setCity(user.getCity());
            userResponse.setContact(user.getContact());
            userResponse.setRoles(user.getRoles());
            userResponse.setCreationTime(user.getCreationTime());
            userResponse.setActive(user.isActive());
            response.setData(userResponse);
            response.setHttpStatus(HttpStatus.OK);
            response.setMessage("User fetched successfully");

            return response;
        }

        else
        {
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("User with the give id is not valid");
            return response;
        }
    }

    public List<UserResponse> fetchUsers(String city)
    {
        Optional<List<User>> usersOptional = userRepository.findByCity(city);
        List<UserResponse> userResponses = new ArrayList<>();

        if (!usersOptional.isPresent())
            return userResponses;

        List<User> users = usersOptional.get();

        for (User user : users)
        {
            UserResponse u = new UserResponse();
            u.setName(user.getName());
            u.setContact(user.getContact());
            u.setCity(user.getCity());
            u.setCountry(user.getCountry());
            u.setEmail(user.getEmail());
            u.setRoles(user.getRoles());
            u.setCreationTime(user.getCreationTime());
            u.setActive(user.isActive());
            userResponses.add(u);
        }
        return userResponses;
    }

    public String updateStatus(String id, boolean status)
    {
        userRepository.updateActiveStatus(id, status);
        return "Successfully updated status.";
    }


}
