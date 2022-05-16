package com.games.firmaGames.Repository;

import java.util.List;

import com.games.firmaGames.Model.ShoppingCart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends JpaRepository<ShoppingCart, Long> {
	

    public List<ShoppingCart> findAllById(long quantity);
    
    public List<ShoppingCart> findAll();

}
