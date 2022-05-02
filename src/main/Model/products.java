import java.util.Locale.Category;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table(name = "product")
public class products {

    @Id
    @GeneratedValue(strategy = GeneratedType.IDENTITY)
    private long id;

    @NotNull
    private String description;

    @NotNull
	private String name;

	@NotNull
	private double value;


    
	@ManyToOne
	@JsonIgnoreProperties("products")
	private Category category;



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



    public Category getCategory() {
        return category;
    }



    public void setCategory(Category category) {
        this.category = category;
    }
    
}
