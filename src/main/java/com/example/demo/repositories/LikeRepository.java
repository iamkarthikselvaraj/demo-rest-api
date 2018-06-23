package com.example.demo.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Likes;
import com.example.demo.models.Login;
import com.example.demo.models.Post;


@Repository
public interface LikeRepository extends MongoRepository<Likes, String> {
	
}