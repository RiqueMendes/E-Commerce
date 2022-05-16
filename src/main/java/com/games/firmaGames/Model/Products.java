package com.games.firmaGames.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_products")
public class Products {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

    @NotNull
    private String description;

    @NotNull
	private String name;

	@NotNull
	private double value;

	 private String gender;
    
	 @ManyToOne
	 @JsonIgnoreProperties("products")
	 private Category category;



    @OneToMany(mappedBy= "products", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("products")

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("product")

	private List<ShoppingCart> shoppingCart;

    @ManyToOne
    @JsonIgnoreProperties("products")
    private Salesman salesman;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

     public Category getCategory() {
         return category;
     }

     public void setCategory(Category category) {
         this.category = category;
     }

	public List<ShoppingCart> getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(List<ShoppingCart> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

}
