package com.games.firmaGames.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.games.firmaGames.Model.Salesman;
import com.games.firmaGames.Model.User;

@Repository
public interface SalesmanRepository extends JpaRepository<Salesman, Long> {

	public List<Salesman> findAllByEmailContainingIgnoreCase(String email);
	
	public List<Salesman> findAllByNameContainingIgnoreCase(String name);
	
	public List<Salesman> findAllByStoreNameContainingIgnoreCase(String storeName);
	
	public Optional<Salesman> findByCpfContainingIgnoreCase(String cpf);
	
	public Optional<Salesman> findByEmail(String email);
}
