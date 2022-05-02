package com.games.firmaGames.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.games.firmaGames.Model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	public List<Category> findAllByGenderContainingIgnoreCase(String gender);
	
	public List<Category> findAllByDescriptionContainingIgnoreCase(String description);
    
}
