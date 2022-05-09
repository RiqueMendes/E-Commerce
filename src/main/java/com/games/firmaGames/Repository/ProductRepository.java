package com.games.firmaGames.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.games.firmaGames.Model.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
	
	public List<Products> findAllByGenderContainingIgnoreCase(String gender);
	
	public List<Products> findAllByNameContainingIgnoreCase(String name);
	
	public List<Products> findAllByDescriptionContainingIgnoreCase(String description);
	
	public List<Products> findAllByValue(Double value);

}
