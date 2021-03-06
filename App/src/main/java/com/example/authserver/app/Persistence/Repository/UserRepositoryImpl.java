package com.example.authserver.app.Persistence.Repository;

import com.example.authserver.app.Persistence.Model.User;
import com.example.authserver.contracts.Enums.Role;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository
{
    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    //use proper constant names.

    @Override
    public long updateActiveStatus(String id, boolean status)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where(User.Constants.ID).is(id));
        Update update = new Update();
        update.set(User.Constants.IS_ACTIVE, status);
        UpdateResult result = mongoTemplate.updateFirst(query, update, User.class);
        return result.getModifiedCount();
    }

    @Override
    public Optional<List<User>> findByCity(String city) {
        Query query = new Query();
        query.addCriteria(Criteria.where(User.Constants.CITY).is(city));
        query.fields().include(User.Constants.ID,
                User.Constants.NAME, User.Constants.CITY, User.Constants.EMAIL);
        return Optional.of(mongoTemplate.find(query, User.class));
    }

    @Override
    public Optional<User> findById(String id)
    {
        return Optional.ofNullable(mongoTemplate.findById(id,User.class));
    }

    @Override
    public User save(User user)
    {
        return mongoTemplate.save(user);
    }

    @Override
    public boolean checkIfAdmin(String callingUserId, String userId)
    {
        Query query = new Query();
        query.addCriteria((Criteria.where(User.Constants.ID).is(callingUserId)));
        query.addCriteria((Criteria.where(User.Constants.ROLES)).is(Role.ADMIN));
        return !mongoTemplate.find(query, User.class).isEmpty();
    }
}
