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

import com.games.firmaGames.Model.User;
import com.games.firmaGames.Model.UserDTO;
import com.games.firmaGames.Repository.UserRepository;

@Service
public class UserService {

	 @Autowired
	    private UserRepository repository;

	 public Optional<User> registerUser(User user) {
			if (repository.findByEmail(user.getEmail()).isPresent())
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
			user.setPassword(criptografarSenha(user.getPassword()));
			return Optional.of(repository.save(user));
		}

	 public Optional<UserDTO> userLogin(Optional<UserDTO> userLogin) {
			Optional<User> user = repository.findByEmail(userLogin.get().getEmail());
			if (user.isPresent()) {
				if (compararSenhas(userLogin.get().getPassword(), user.get().getPassword())) {
					userLogin.get()
					.setToken(generatorBasicToken(userLogin.get().getEmail(), userLogin.get().getPassword()));
					userLogin.get().setId(user.get().getId());
					userLogin.get().setName(user.get().getName());
					userLogin.get().setPassword(user.get().getPassword());
					userLogin.get().setEmail(user.get().getEmail());
					
					return userLogin;
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
	    
	    public ResponseEntity<User> updateUser(User user) {
	        Optional<User> userM = repository.findById(user.getId());

	        if (userM.isPresent()) {
	            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	            user.setPassword(encoder.encode(user.getPassword()));
	            repository.save(user);
	            return ResponseEntity.status(HttpStatus.OK).body(user);
	        } else {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
	        }
	    }
	}
