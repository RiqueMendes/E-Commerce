package com.games.firmaGames.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.games.firmaGames.Model.User;
import com.games.firmaGames.Model.UserDTO;
import com.games.firmaGames.Repository.UserRepository;
import com.games.firmaGames.Service.UserService;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("name/{name}")
	public ResponseEntity<List<User>> getAllUsersByName(@PathVariable String name) {
		if(repository.findAllByNameContainingIgnoreCase(name).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with that name found");
		}
		return ResponseEntity.ok(repository.findAllByNameContainingIgnoreCase(name));
	}
	
	@GetMapping("/user/{email}")
	public ResponseEntity<Optional<User>> getUserByEmail(@PathVariable String email){
		return ResponseEntity.ok(repository.findByEmail(email));
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@Valid @RequestBody User user) {
		return service.registerUser(user).map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());	
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserDTO> autenticationUsuario(@Valid @RequestBody Optional<UserDTO> user) {
		return service.userLogin(user)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PutMapping("/update")
	public ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
		return service.updateUser(user);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
