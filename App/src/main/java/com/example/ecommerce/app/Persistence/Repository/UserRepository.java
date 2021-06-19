package com.example.ecommerce.app.Persistence.Repository;

import com.example.ecommerce.app.Persistence.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String>
{
    @Query(value = "{'city' : ?0 }", fields = "{name : 1, email : 1, contact : 1 }")
    Optional<List<User>> findByCity(String city);
}
