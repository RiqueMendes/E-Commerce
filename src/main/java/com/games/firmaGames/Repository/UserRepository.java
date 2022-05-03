package com.games.firmaGames.Repository;

import java.util.Optional;

import com.games.firmaGames.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> fidByUser(String user);
	
}
