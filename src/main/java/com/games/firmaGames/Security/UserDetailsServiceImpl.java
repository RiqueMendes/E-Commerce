package com.games.firmaGames.Security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.games.firmaGames.Repository.UserRepository;



@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername (String userName) throws UsernameNotFoundException {	
		Optional<com.games.firmaGames.Model.User> user = repository.findByUser(userName);
		user.orElseThrow(() -> new UsernameNotFoundException(userName + "nenhum resultado encontrado."));
		
		return user.map(UserDetailsImpl::new).get();		
	}
}

