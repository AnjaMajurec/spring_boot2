package algebra2.example.spring_boot2.product;

import org.springframework.jdbc.core.RowMapper;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("Id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        BigDecimal price = rs.getBigDecimal("price");
        Boolean available=rs.getBoolean("available");
        LocalDate createdAt = rs.getDate("createdAt").toLocalDate();

        return new Product(id, name, description, price, available,createdAt);
    }

}
