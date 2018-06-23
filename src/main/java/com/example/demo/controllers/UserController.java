package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Login;
import com.example.demo.models.Response;
import com.example.demo.models.User;
import com.example.demo.repositories.LoginRepository;
import com.example.demo.repositories.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	LoginRepository loginRepository;

	@Autowired
	UserRepository userRepository;

	@GetMapping("/list")
	public ResponseEntity<?> getAllUsers() {
		Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
		return new ResponseEntity<>(userRepository.findAll(sortByCreatedAtDesc), HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<?> createUser(@Valid @RequestBody Login login) {
		login.setCreatedBy("admin");
		
		if (!loginRepository.existsByUsername(login.getUsername())) {
			userRepository.save(login.getUser());

			loginRepository.save(login);
			return new ResponseEntity<>(new Response("success", "User created successfully."),
					HttpStatus.OK);			
			
		} else {
			return new ResponseEntity<>(new Response("failure", "Username already exist."), HttpStatus.OK);
		}

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") String id) {
		return userRepository.findById(id).map(user -> ResponseEntity.ok().body(new Response("success", "",user)))
				.orElse(ResponseEntity.ok().body(new Response("failure", "User not found")));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") String id, @Valid @RequestBody User user) {
		return userRepository.findById(id).map(userData -> {
			userData.setFirstname(user.getFirstname());
			userData.setLastname(user.getLastname());
			User updatedUser = userRepository.save(userData);
			return ResponseEntity.ok().body(updatedUser);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
		return userRepository.findById(id).map(user -> {
			userRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());

	}
}