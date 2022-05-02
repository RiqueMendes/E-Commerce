package com.games.firmaGames.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.games.firmaGames.Model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	public List<Category> findAllByGenderContainingIgnoreCase(String gender);
	
	public List<Category> findAllByDescriptionContainingIgnoreCase(String description);
    
}
