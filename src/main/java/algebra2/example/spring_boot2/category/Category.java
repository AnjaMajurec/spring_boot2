package algebra2.example.spring_boot2.category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Integer id;
    private String name;
    private String description;

    public Category(String name, String description){
        this.name=name;
        this.description=description;
    }
}
