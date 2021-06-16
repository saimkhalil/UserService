package com.example.ecommerce.app.Persistence.Repository;

import com.example.ecommerce.app.Persistence.Model.EcommerceUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<EcommerceUser, String>
{
    @Query("{'city' : ?0 }")
    List<EcommerceUser> findByCity(String city);
}
