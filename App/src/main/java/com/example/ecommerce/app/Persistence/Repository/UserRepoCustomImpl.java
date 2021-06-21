package com.example.ecommerce.app.Persistence.Repository;

import com.example.ecommerce.app.Persistence.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepoCustomImpl implements UserRepoCustom
{
    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserRepoCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void updateActiveStatus(String id, boolean status)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where(User.Constants.ID).is(id));
        Update update = new Update();
        update.set("isActive", status);
        mongoTemplate.updateFirst(query, update, User.class);
    }

    @Override
    public Optional<List<User>> findByCity(String city) {
        Query query = new Query();
        query.addCriteria(Criteria.where(User.Constants.CITY).is(city));
        query.fields().include("_id", "name", "city", "email");
        return Optional.of(mongoTemplate.find(query, User.class));
    }

    @Override
    public Optional<User> findById(String id)
    {
        return Optional.ofNullable(mongoTemplate.findById(User.Constants.ID,User.class));
    }

    @Override
    public User save(User user)
    {
        return mongoTemplate.save(user);
    }
}
