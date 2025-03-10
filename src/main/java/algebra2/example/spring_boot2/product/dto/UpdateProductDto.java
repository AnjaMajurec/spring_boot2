package algebra2.example.spring_boot2.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UpdateProductDto {
    @NotBlank
    private String name;

    private String description;

    @NotNull
    private BigDecimal price;

    private Boolean available;

    private LocalDate createdAt;
}
