package com.example.ecommerce.app.Validator;

import com.example.ecommerce.app.Utils.SError;
import com.example.ecommerce.contracts.Request.UserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UserRequestValidator
{
    public void validateUserRequest(UserRequest userRequest) throws SError {
        throw new SError("Empty user name", HttpStatus.BAD_REQUEST);
    }
}
