package algebra2.example.spring_boot2.product;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl {
    private final JdbcTemplate jdbcTemplate;

    public List<Product> fetchAll(){
        return jdbcTemplate.query("SELECT p.id, p.name, p.description, p.price, p.available, p.createdAt FROM Product p", new ProductRowMapper());
    }


    public Optional<Product> findById(Long id){
        String query = "SELECT p.id, p.name, p.description, p.price, p.available, p.createdAt FROM Product p WHERE p.id = ?";

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(query, new ProductRowMapper(), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    public Product create(Product product){
        String query = "INSERT INTO Product (name, description, price, available, createdAt) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, product.getName(), product.getDescription(), product.getPrice(), product.getAvailable(),product.getCreatedAt());
        return product;
    }


    public Product update (Product product){
        String query = "UPDATE Product SET name = ?,  description = ?,  price = ?,  available = ?, createdAt=? WHERE id = ?";
        jdbcTemplate.update(query, product.getName(), product.getDescription(), product.getPrice(), product.getAvailable(), product.getCreatedAt(), product.getId());
        return product;
    }


    public void delete (Long id){
        String query = "DELETE FROM Product WHERE id = ?";

        var result= jdbcTemplate.update(query, id);
        System.out.println("Obrisano je " + result  + " stupaca");
    }
}
