package com.games.firmaGames.Controller;

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
		
	@GetMapping
	public ResponseEntity <PurchasesDTO> getAllPurchases(Long Id){
        PurchasesDTO purchaseDto = new PurchasesDTO();
		purchaseDto.setPurchase(repository.findById(Id).get());
        purchaseDto.setShoppingList(cartRepository);
        return ResponseEntity.ok(purchaseDto);
	}


    
    
}
