package com.example.ecommerce.app.Persistence.Repository;

import com.example.ecommerce.app.Persistence.Model.EcommerceUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<EcommerceUser, String> {
}
