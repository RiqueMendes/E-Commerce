package com.games.firmaGames.Controller;

import java.util.ArrayList;
import java.util.List;

import com.games.firmaGames.Model.PurchasesDTO;
import com.games.firmaGames.Model.ShoppingCart;
import com.games.firmaGames.Repository.CartRepository;
import com.games.firmaGames.Repository.PurchasesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ShoppingList")
public class PurchasesController {

    @Autowired
	private PurchasesRepository repository;

    @Autowired
    private CartRepository cartRepository;
		
	@GetMapping("/{id}")
	public ResponseEntity <PurchasesDTO> getAllPurchases(Long Id){
		List<ShoppingCart> meu = cartRepository.findAll();
        PurchasesDTO purchaseDto = new PurchasesDTO();
		purchaseDto.setPurchase(repository.findById(Id).get());
		List<ShoppingCart> seu = new ArrayList<ShoppingCart>();
		
        for (ShoppingCart shoppingCart : meu) {
        	if (shoppingCart.getPurchases().getId() == Id) {
        		seu.add(shoppingCart);
        	}
		} 
        
        purchaseDto.setShoppingList(seu);
        
        return ResponseEntity.ok(purchaseDto);
	}


    
    
}
