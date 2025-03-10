package algebra2.example.spring_boot2.product;

import algebra2.example.spring_boot2.product.dto.CreateProductDto;
import algebra2.example.spring_boot2.product.dto.UpdateProductDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> fetchAll();

    Optional<Product> findById(Long id);

    Product create(CreateProductDto dto);

    Product update(Long id, UpdateProductDto dto);

    void delete(Long id);

    Optional<Product> findByName(String name);

    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    List<Product> findByAvailableTrue();

    List<Product> findByDescriptionContainingIgnoreCase(String description);

    List<Product> findUnavailableProducts();

    List<Product> findProductsNotInPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    Product findByName2(String name);

    List<Product> findByNameContaining(String name);

    List<Product> findByNameNotContaining(String name);

    List<Product> findByDescriptionIsNull();

}