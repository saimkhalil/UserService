package com.example.authserver.app.Persistence.Repository;


import com.example.authserver.app.Persistence.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository
{
    long updateActiveStatus(String id, boolean status);

    Optional<List<User>> findByCity(String city);

    Optional<User> findById(String id);

    User save(User user);

    boolean checkIfAdmin(String callingUserId , String userId);
}
