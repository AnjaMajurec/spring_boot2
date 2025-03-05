package algebra2.example.spring_boot2.category.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateCategoryDto {
    @NotBlank
    private String name;

    private String description;
}
