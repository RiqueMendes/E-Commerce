package com.games.firmaGames.Service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.games.firmaGames.Model.Salesman;
import com.games.firmaGames.Model.SalesmanDTO;

import com.games.firmaGames.Repository.SalesmanRepository;


@Service
public class SalesmanService {

	@Autowired
    private SalesmanRepository repository;

 public Optional<Salesman> registerSalesman(Salesman salesman) {
		if (repository.findByEmail(salesman.getEmail()).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
		salesman.setPassword(criptografarSenha(salesman.getPassword()));
		return Optional.of(repository.save(salesman));
	}

 public Optional<SalesmanDTO> salesmanLogin(Optional<SalesmanDTO> salesmanDTO) {
		Optional<Salesman> salesman = repository.findByEmail(salesmanDTO.get().getEmail());
		if (salesman.isPresent()) {
			if (compararSenhas(salesmanDTO.get().getPassword(), salesman.get().getPassword())) {
				salesmanDTO.get()
				.setToken(generatorBasicToken(salesmanDTO.get().getEmail(), salesmanDTO.get().getPassword()));
				salesmanDTO.get().setId(salesman.get().getId());
				salesmanDTO.get().setName(salesman.get().getName());
				salesmanDTO.get().setPassword(salesman.get().getPassword());
				salesmanDTO.get().setEmail(salesman.get().getEmail());
				
				return salesmanDTO;
			}
		}
		throw new ResponseStatusException(
				HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos!", null);
	}
	
	private String criptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(senha);
		return senhaEncoder;
	}
	
	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(senhaDigitada, senhaBanco);
	}
	
	private String generatorBasicToken(String email, String password) {
		String structure = email + ":" + password;
		byte[] structureBase64 = Base64.encodeBase64(structure.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(structureBase64);
	}
    
    public ResponseEntity<Salesman> updateSalesman(Salesman salesman) {
        Optional<Salesman> salesmanM = repository.findById(salesman.getId());

        if (salesmanM.isPresent()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            salesman.setPassword(encoder.encode(salesman.getPassword()));
            repository.save(salesman);
            return ResponseEntity.status(HttpStatus.OK).body(salesman);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
    }
}
