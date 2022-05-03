package com.games.firmaGames.Controller;


import java.util.List;

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

import com.games.firmaGames.Model.Products;
import com.games.firmaGames.Repository.ProductRepository;


@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductsController {

	@Autowired
	private ProductRepository repository;
	
	@GetMapping 
	public ResponseEntity<List<Products>> getAllProducts() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Products> getByIdProducts(@PathVariable Long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/gender/{gender}")
	public ResponseEntity<List<Products>> getAllProductsByGender(@PathVariable String gender){
		if(repository.findAllByGenderContainingIgnoreCase(gender).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products with this genre found");
		}
		return ResponseEntity.ok(repository.findAllByGenderContainingIgnoreCase(gender));
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<Products>> getAllProductsByName(@PathVariable String name){
		if(repository.findAllByNameContainingIgnoreCase(name).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products with this name found");
		}
		return ResponseEntity.ok(repository.findAllByNameContainingIgnoreCase(name));
	}
	
	@GetMapping("/description/{description}")
	public ResponseEntity<List<Products>> getAllProductsByDescription(@PathVariable String description){
		if(repository.findAllByDescriptionContainingIgnoreCase(description).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products with this description found");
		}
		return ResponseEntity.ok(repository.findAllByDescriptionContainingIgnoreCase(description));
	}
	
	@GetMapping("/value/{value}")
	public ResponseEntity<List<Products>> getAllProductsByValue(@PathVariable Double value){
		if(repository.findAllByValue(value).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products with this value found");
		}
		return ResponseEntity.ok(repository.findAllByValue(value));
	}
	
	@PostMapping
	public ResponseEntity<Products> postProduct (@Valid @RequestBody Products products){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(products));
	}
	
	@PutMapping
	public ResponseEntity<Products> putProduct (@Valid @RequestBody Products products){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(products));
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
