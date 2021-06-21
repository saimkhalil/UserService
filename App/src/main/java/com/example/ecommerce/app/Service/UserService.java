package com.example.ecommerce.app.Service;

import com.example.ecommerce.app.Mapper.UserMapper;
import com.example.ecommerce.app.Persistence.Model.User;
import com.example.ecommerce.app.Persistence.Repository.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public ResponseModel<String> createUser(UserRequest userRequest)
    {
        User user = userMapper.mapUserRequestToUser(userRequest);
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
            UserResponse userResponse = userMapper.mapUserToUserResponse(user);
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
            UserResponse u = userMapper.mapUserToUserResponse(user);
            userResponses.add(u);
        }
        return userResponses;
    }

    public String updateStatus(String id, boolean status)
    {
        long modifiedCount = userRepository.updateActiveStatus(id, status);
        if (modifiedCount == 1)
            return "Successfully updated status.";
        else
            return "Could not update the document";
    }

    public String deletedUser(String callingUserId, String userId)
    {
        boolean isAdmin = userRepository.checkIfAdmin(callingUserId,userId)
        if (isAdmin)
        {
            long modifiedCount = userRepository.updateActiveStatus(userId, false);
            if (modifiedCount == 1)
            {
                return "User has been deleted successfully";
            }
            else
            {
                return "Users forbidden to use this API, only Admins are allowed";
            }
        }
        else
        {
            return "Users forbidden to use this API, only Admins are allowed";
        }
    }

}
