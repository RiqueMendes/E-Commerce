package com.games.firmaGames.Model;

import java.util.List;

public class PurchasesDTO {
    

	private List<ShoppingCart> shoppingList;

    private Purchases purchase;
	

    

    public List<ShoppingCart> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(List<ShoppingCart> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public Purchases getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchases purchase) {
        this.purchase = purchase;
    }

    

    
}
