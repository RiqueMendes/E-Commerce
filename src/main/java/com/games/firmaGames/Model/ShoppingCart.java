package com.games.firmaGames.Model;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "tb_shoppingCart")
public class ShoppingCart {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;


    @NotNull
    private long quantity;

	@ManyToOne
	@JsonIgnoreProperties("shoppingCart")
	private Purchases purchases;
	
	@ManyToOne
	@JsonIgnoreProperties("shoppingCart")
	private Products products;
	
   

    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

    public Products getProduct() {
        return product;
    }


    public void setProduct(Products product) {
        this.product = product;
    }


    public long getQuantity() {
        return quantity;
    }


    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }


    public Purchases getPurchases() {
        return purchases;
    }


    public void setPurchases(Purchases purchases) {
        this.purchases = purchases;
    }
    
    
}
