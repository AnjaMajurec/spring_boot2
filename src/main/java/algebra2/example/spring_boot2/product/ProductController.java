package algebra2.example.spring_boot2.product;

import algebra2.example.spring_boot2.product.dto.CreateProductDto;
import algebra2.example.spring_boot2.product.dto.UpdateProductDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> fetchAll(){
        List<Product> products=productService.fetchAll();
        return ResponseEntity.status(200).body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        Optional<Product> product=productService.findById(id);
        if(product.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(product.get());
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody CreateProductDto dto){
        Product product=productService.create(dto);
        return ResponseEntity.status(200).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@Valid @RequestBody UpdateProductDto dto, @PathVariable Long id){
        Product product=productService.update(id,dto);
        return ResponseEntity.status(200).body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/name")
    public ResponseEntity<Product> findByName(@RequestParam String name) {
        Optional<Product> product = productService.findByName(name);
        if(product.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(product.get());
    }
    @GetMapping("/searchByPrice")
    public ResponseEntity<List<Product>> findByPriceBetween(@RequestParam BigDecimal minPrice,@RequestParam BigDecimal maxPrice) {

        List<Product> products = productService.findByPriceBetween(minPrice, maxPrice);
        if(products.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(products);
    }
    @GetMapping("/available")
    public ResponseEntity<List<Product>> findByAvailableTrue() {
        List<Product> products = productService.findByAvailableTrue();
        if(products.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/description/containingIgnoreCase")
    public ResponseEntity<List<Product>> getProductsByDescriptionContaining(@RequestParam String description) {
        List<Product> products = productService.findByDescriptionContainingIgnoreCase(description);
        if (products.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/unavailable")
    public ResponseEntity<List<Product>> getUnavailableProducts() {
        List<Product> products = productService.findUnavailableProducts();
        if (products.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(products);
    }


    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsNotInPriceRange(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice) {
        List<Product> products = productService.findProductsNotInPriceRange(minPrice, maxPrice);
        if (products.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(products);
    }


    @GetMapping("/name2")
    public ResponseEntity<Product> getProductByName2(@RequestParam String name) {
        Product product = productService.findByName2(name);
        if (product==null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(product);
    }


    @GetMapping("/name/containing")
    public ResponseEntity<List<Product>> getProductsByNameContaining(@RequestParam String name) {
        List<Product> products = productService.findByNameContaining(name);
        if (products.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(products);
    }


    @GetMapping("/name/not-containing")
    public ResponseEntity<List<Product>> getProductsByNameNotContaining(@RequestParam String name) {
        List<Product> products = productService.findByNameNotContaining(name);
        if (products.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(products);
    }


    @GetMapping("/description-null")
    public ResponseEntity<List<Product>> getProductsByDescriptionIsNull() {
        List<Product> products = productService.findByDescriptionIsNull();
        if (products.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(products);
    }

}
