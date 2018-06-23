package com.example.demo.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Login;


@Repository
public interface LoginRepository extends MongoRepository<Login, String> {

}