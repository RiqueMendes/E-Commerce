package com.games.firmaGames.Model;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "tb_shoppingCart")
public class ShoppingCart {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	
   
	@ManyToOne
	@JsonIgnoreProperties("shoppingCart")
	private User user;
	
	@ManyToOne
	@JsonIgnoreProperties("shoppingCart")
	private Products product;
	
   

    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public Products getProduct() {
        return product;
    }


    public void setProduct(Products product) {
        this.product = product;
    }
    
}
