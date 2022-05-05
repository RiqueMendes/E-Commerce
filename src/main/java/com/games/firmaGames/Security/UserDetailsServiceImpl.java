package com.games.firmaGames.Security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.games.firmaGames.Model.User;
import com.games.firmaGames.Repository.UserRepository;



@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = repository.findByEmail(email);
		user.orElseThrow(() -> new UsernameNotFoundException (email + " Not Found.. "));
		
		
		return user.map(UserDetailsImpl::new).get();
					
	}
}

