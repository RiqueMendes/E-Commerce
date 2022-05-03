package com.games.firmaGames.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_product")
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

	 @NotNull
	 private String gender;
    
	// @ManyToOne
	// @JsonIgnoreProperties("products")
	// private Category category;



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

    

    // public Category getCategory() {
    //     return category;
    // }



    // public void setCategory(Category category) {
    //     this.category = category;
    // }
    
}
