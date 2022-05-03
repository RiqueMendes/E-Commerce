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

import com.games.firmaGames.Model.Category;
import com.games.firmaGames.Repository.CategoryRepository;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {

	@Autowired
	private CategoryRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Category>> getAllCategory(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/producer/{producer}")
	public ResponseEntity<List<Category>> getAllCategoryByProducerType(@PathVariable String producer){
		if(repository.findAllByProducerContainingIgnoreCase(producer).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No producer with that name found...");
		}
		return ResponseEntity.ok(repository.findAllByProducerContainingIgnoreCase(producer));
	}
	
	@GetMapping("/description/{description}")
	public ResponseEntity<List<Category>> getAllCategoryBydescription(@PathVariable String description){
		if(repository.findAllByDescriptionContainingIgnoreCase(description).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No description found...");
		}
		return ResponseEntity.ok(repository.findAllByDescriptionContainingIgnoreCase(description));
	}
	
	@PostMapping
	public ResponseEntity<Category> postCategory(@Valid @RequestBody Category category){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(category));
	}
	
	@PutMapping
	public ResponseEntity<Category> putCategory(@Valid @RequestBody Category category){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(category));
	}
	
	@DeleteMapping("/{id}")
	public void deleteCategory(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
