package com.example.ecommerce.app.Persistence.Repository;


import com.example.ecommerce.app.Persistence.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepoCustom
{
    void updateActiveStatus(String id, boolean status);

    Optional<List<User>> findByCity(String city);

    Optional<User> findById(String id);

    User save(User user);
}
