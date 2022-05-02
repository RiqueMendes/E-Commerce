package com.games.firmaGames.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.games.firmaGames.Model.Products;

public interface ProductRepository extends JpaRepository<Products, Long> {
	
	public List<Products> findAllByDescriptionContainingIgnoreCase(String description);
	
	public List<Products> findAllByValue(Double value);

}
