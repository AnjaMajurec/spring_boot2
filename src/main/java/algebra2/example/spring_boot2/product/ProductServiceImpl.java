package algebra2.example.spring_boot2.product;

import algebra2.example.spring_boot2.product.dto.CreateProductDto;
import algebra2.example.spring_boot2.product.dto.UpdateProductDto;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    @Override
    public List<Product> fetchAll(){
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    @Override
    public Product create(CreateProductDto dto){
        Product product = new Product(dto.getName(), dto.getDescription(), dto.getPrice(), dto.getAvailable(),dto.getCreatedAt());
        return productRepository.save(product);
    }

    @Override
    public Product update (Long id, UpdateProductDto dto){
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new InternalException("Product not found");
        }

        Product productForUpdate = product.get();
        productForUpdate.setName(dto.getName());
        productForUpdate.setDescription(dto.getDescription());
        productForUpdate.setPrice(dto.getPrice());
        productForUpdate.setAvailable(dto.getAvailable());
        productForUpdate.setCreatedAt(dto.getCreatedAt());

        return productRepository.save(productForUpdate);
    }


    @Override
    public void delete (Long id){
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new InternalException("Product not found");
        }

        productRepository.delete(product.get());
    }
    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }
    @Override
    public List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }
    @Override
    public List<Product> findByAvailableTrue() {
        return productRepository.findByAvailableTrue();
    }
    @Override
    public List<Product> findByDescriptionContainingIgnoreCase(String description) {
        return productRepository.findByDescriptionContainingIgnoreCase(description);
    }
    @Override
    public List<Product> findUnavailableProducts() {
        return productRepository.findUnavailableProducts();
    }

    @Override
    public List<Product> findProductsNotInPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findProductsNotInPriceRange(minPrice, maxPrice);
    }

    @Override
    public Product findByName2(String name) {
        return productRepository.findByName2(name);
    }

    @Override
    public List<Product> findByNameContaining(String name) {
        return productRepository.findByNameContaining(name);
    }

    @Override
    public List<Product> findByNameNotContaining(String name) {
        return productRepository.findByNameNotContaining(name);
    }

    @Override
    public List<Product> findByDescriptionIsNull() {
        return productRepository.findByDescriptionIsNull();
    }
}
