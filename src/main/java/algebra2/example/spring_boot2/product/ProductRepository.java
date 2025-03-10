package algebra2.example.spring_boot2.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    // Pronalaženje proizvoda prema nazivu
    Optional<Product> findByName(String name);

    // Pronalaženje proizvoda s cijenom između određenih vrijednosti
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    // Pronalaženje dostupnih proizvoda
    List<Product> findByAvailableTrue();

    // Pronalaženje proizvoda čiji opis sadrži određenu riječ
    List<Product> findByDescriptionContainingIgnoreCase(String description);

    // Pronalaženje proizvoda koji nisu dostupni
    @Query("SELECT p FROM Product p WHERE p.available = false")
    List<Product> findUnavailableProducts();

    // Pronalaženje proizvoda čija cijena nije između određenih vrijednosti
    @Query("SELECT p FROM Product p WHERE p.price < :minPrice OR p.price > :maxPrice")
    List<Product> findProductsNotInPriceRange(BigDecimal minPrice, BigDecimal maxPrice);

    // Pronalaženje proizvoda koji imaju određeno ime
    @Query("SELECT p FROM Product p WHERE p.name = :name")
    Product findByName2(String name);

    // Pronalaženje proizvoda čiji naziv sadrži zadani izraz (LIKE)
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    List<Product> findByNameContaining(String name);

    // Pronalaženje proizvoda koji ne sadrže određeni izraz u nazivu (NOT LIKE)
    @Query("SELECT p FROM Product p WHERE p.name NOT LIKE %:name%")
    List<Product> findByNameNotContaining(String name);

    // Pronalaženje proizvoda čiji opis je NULL (IS NULL)
    @Query("SELECT p FROM Product p WHERE p.description IS NULL")
    List<Product> findByDescriptionIsNull();
}
