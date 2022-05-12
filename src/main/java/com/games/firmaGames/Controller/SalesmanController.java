package com.games.firmaGames.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.games.firmaGames.Model.Salesman;
import com.games.firmaGames.Model.SalesmanDTO;
import com.games.firmaGames.Repository.SalesmanRepository;
import com.games.firmaGames.Service.SalesmanService;

@Controller
@RequestMapping("/sallers")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SalesmanController {

	@Autowired
	private SalesmanRepository repository;
	
	@Autowired
	private SalesmanService service;
	
	@GetMapping
	public ResponseEntity<List<Salesman>> getAllSallers(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Salesman> getByIdSaller(@PathVariable Long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<List<Salesman>> getByEmailSallers(@PathVariable String email){
		if(repository.findAllByEmailContainingIgnoreCase(email).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No seller with this email was found.");
		}
		return ResponseEntity.ok(repository.findAllByEmailContainingIgnoreCase(email));
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<Salesman>> getByNameSallers(@PathVariable String name){
		if(repository.findAllByNameContainingIgnoreCase(name).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No seller with this name was found.");
		}
		return ResponseEntity.ok(repository.findAllByNameContainingIgnoreCase(name));
	}
	
	@GetMapping("/storename/{storeName}")
	public ResponseEntity<List<Salesman>> getByStoreNameSallers(@PathVariable String storeName){
		if(repository.findAllByStoreNameContainingIgnoreCase(storeName).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No seller with this store name was found.");
		}
		return ResponseEntity.ok(repository.findAllByStoreNameContainingIgnoreCase(storeName));
	}
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<Salesman> getByCpfSaller(@PathVariable String cpf){
		return repository.findByCpfContainingIgnoreCase(cpf)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/register")
	private ResponseEntity<Salesman> registerSaller(@Valid @RequestBody Salesman salesman){
		return service.registerSalesman(salesman)
				.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@PostMapping("/login")
	private ResponseEntity<SalesmanDTO> loginSaller(@Valid @RequestBody Optional<SalesmanDTO> salesman){
		return service.salesmanLogin(salesman).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@PutMapping("/update")
	public ResponseEntity<Salesman> updateSaller(@Valid @RequestBody Salesman salesman){
		return service.updateSalesman(salesman);
	}
	
	@DeleteMapping
	public void deleteSaller(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
