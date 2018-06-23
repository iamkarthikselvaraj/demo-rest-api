package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Login;
import com.example.demo.models.User;
import com.example.demo.repositories.LoginRepository;
import com.example.demo.repositories.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

	@Autowired
	LoginRepository loginRepository;

	@Autowired
	UserRepository userRepository;

	@GetMapping("/user")
	public List<Login> getAllUsers() {
		Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
		return loginRepository.findAll(sortByCreatedAtDesc);
	}

	@PostMapping("/create-user")
	public Login createUser(@Valid @RequestBody Login login) {
		login.setCreatedBy("admin");
		userRepository.save(login.getUser());
//		login.setUser(user);
		return loginRepository.save(login);
	}

	@GetMapping(value = "/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
		return userRepository.findById(id).map(user -> ResponseEntity.ok().body(user))
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping(value = "/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") String id, @Valid @RequestBody User user) {
		return userRepository.findById(id).map(userData -> {
			userData.setFirstname(user.getFirstname());
			userData.setLastname(user.getLastname());
			User updatedUser = userRepository.save(userData);
			return ResponseEntity.ok().body(updatedUser);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(value = "/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
		return userRepository.findById(id).map(user -> {
			userRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}