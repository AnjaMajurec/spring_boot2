package algebra2.example.spring_boot2.category.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCategoryDto {
    @NotBlank
    private String name;

    private String description;

    public void category(String name, String description){
        this.name=name;
        this.description=description;
    }
}
