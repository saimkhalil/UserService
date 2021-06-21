package com.example.ecommerce.app.Mapper;

import com.example.ecommerce.app.Persistence.Model.User;
import com.example.ecommerce.contracts.Request.UserRequest;
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
}
