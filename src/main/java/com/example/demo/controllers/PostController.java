package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Likes;
import com.example.demo.models.Login;
import com.example.demo.models.Post;
import com.example.demo.models.Response;
import com.example.demo.models.User;
import com.example.demo.repositories.LikeRepository;
import com.example.demo.repositories.LoginRepository;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post")
@CrossOrigin("*")
public class PostController {

	@Autowired
	PostRepository postRepository;
	@Autowired
	LikeRepository likeRepository;
	@Autowired
	UserRepository userRepository;

	@GetMapping("/list")
	public ResponseEntity<?> getAllPosts() {
		Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
		return new ResponseEntity<>(postRepository.findAll(sortByCreatedAtDesc), HttpStatus.OK);
	}
	@PostMapping("/create")
	public ResponseEntity<?> createPost(@Valid @RequestBody Post post) {
		postRepository.save(post);
		return new ResponseEntity<>(new Response("success", "Post created successfully.",post), HttpStatus.OK);
	}
	@PostMapping("/like")
	public ResponseEntity<?> likePost(@Valid @RequestBody Likes like) {
		likeRepository.save(like);
		return new ResponseEntity<>(new Response("success", "liked"), HttpStatus.OK);
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getPostById(@PathVariable("id") String id) {
		return postRepository.findById(id).map(post -> ResponseEntity.ok().body(new Response("success", "",post)))
				.orElse(ResponseEntity.ok().body(new Response("failure", "Post not found")));
	}
	
}