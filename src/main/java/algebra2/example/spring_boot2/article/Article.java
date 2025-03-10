package algebra2.example.spring_boot2.article;
import algebra2.example.spring_boot2.category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private  String name;
    private String description;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name="categoryId", referencedColumnName = "id")
    private Category category;

    public Article(Integer id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
    public Article(String name, String description, BigDecimal price, Category category){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category=category;
    }
}
