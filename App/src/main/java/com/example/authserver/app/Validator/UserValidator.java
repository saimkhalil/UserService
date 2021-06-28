package com.example.authserver.app.Validator;

import com.example.authserver.app.Utils.SError;
import com.example.authserver.contracts.Request.UserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UserValidator
{
    public void validateUserRequest(UserRequest userRequest) throws SError
    {
        if (null == userRequest.getName() || "".equals(userRequest.getName().trim()))
        {
            throw new SError("Empty user name", HttpStatus.BAD_REQUEST);
        }
        else if (null == userRequest.getContact() || "".equals(userRequest.getContact().trim()))
        {
            throw new SError("Empty user contact", HttpStatus.BAD_REQUEST);
        }
        else if (null == userRequest.getEmail() || "".equals(userRequest.getEmail().trim()))
        {
            throw new SError("Empty user email", HttpStatus.BAD_REQUEST);
        }
        else if (null == userRequest.getCountry() || "".equals(userRequest.getCountry().trim()))
        {
            throw new SError("Empty user country", HttpStatus.BAD_REQUEST);
        }
        else if (null == userRequest.getCity())
        {
            throw new SError("Empty user city", HttpStatus.BAD_REQUEST);
        }
        else if (null == userRequest.getRoles())
        {
            throw new SError("Empty user roles", HttpStatus.BAD_REQUEST);
        }
    }

    public void validateStatusRequest(String id, boolean status) throws SError
    {
        if (null == id || "".equals(id.trim()))
        {
            throw new SError("Not a valid id", HttpStatus.BAD_REQUEST);
        }

    }

    public void deleteStatusRequest(String callingUserId , String userId) throws SError
    {
        if (null == callingUserId || "".equals(callingUserId.trim()) || null == userId || "".equals(userId.trim()))
        {
            throw new SError("Not a valid id",  HttpStatus.BAD_REQUEST);
        }
    }

    //validate ...
}
