package com.example.authserver.app.Mapper;

import com.example.authserver.app.Persistence.Model.User;
import com.example.authserver.contracts.Request.UserRequest;
import com.example.authserver.contracts.Response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper
{
    public User mapUserRequestToUser(UserRequest userRequest)
    {
        User user = new User();
        user.setName(userRequest.getName());
        user.setCity(userRequest.getCity());
        user.setContact(userRequest.getContact());
        user.setEmail(userRequest.getEmail());
        user.setCountry(userRequest.getCountry());
        return user;
    }

    //add a mapper here
    public UserResponse mapUserToUserResponse(User user)
    {
        UserResponse userResponse = new UserResponse();
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setCountry(user.getCountry());
        userResponse.setCity(user.getCity());
        userResponse.setContact(user.getContact());
        userResponse.setRoles(user.getRoles());
        userResponse.setCreationTime(user.getCreationTime());
        userResponse.setActive(user.isActive());
        return userResponse;
    }
}
