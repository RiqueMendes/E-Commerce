package com.games.firmaGames.Service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.games.firmaGames.Model.User;
import com.games.firmaGames.Model.UserDTO;
import com.games.firmaGames.Repository.UserRepository;

@Service
public class UserService {

	 @Autowired
	    private UserRepository repository;

	    public User cadastrUsuario(User user){
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	        String senhaEncoder = encoder.encode(user.getPassword());
	        user.setPassword(senhaEncoder);

	        return repository.save(user);
	    }

	    public Optional<UserDTO> Logar(Optional<UserDTO> user){
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        Optional<User> userM = repository.findByUser(user.get().getUser());

	        if(user.isPresent()){
	            if(encoder.matches(user.get().getPassword(), user.get().getPassword())){

	                String auth = user.get().getUser() + ":" + user.get().getPassword();
	                byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
	                String authHeader = "Basic " + new String(encodeAuth);

	                user.get().setToken(authHeader);
	                user.get().setName(user.get().getName());

	                return user;
	            }
	        }
	        return null;
	    }

	    
	}
