package com.learn.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.learn.vo.User;

public interface UserRepository extends MongoRepository<User, String> {
}
