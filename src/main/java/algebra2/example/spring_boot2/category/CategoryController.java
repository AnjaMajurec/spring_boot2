package algebra2.example.spring_boot2.category;

import algebra2.example.spring_boot2.category.dto.CreateCategoryDto;
import algebra2.example.spring_boot2.category.dto.UpdateCategoryDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Integer id){
        Optional<Category> category=categoryService.findById(id);
        if(category.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(category.get());
    }

    @GetMapping
    public ResponseEntity<List<Category>> fetchAll(){
        List<Category> categories=categoryService.fetchAll();
        return ResponseEntity.status(200).body(categories);
    }

    @PostMapping
    public ResponseEntity<Category> create(@Valid @RequestBody CreateCategoryDto dto){
        Category category=categoryService.create(dto);
        return ResponseEntity.status(200).body(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@Valid @RequestBody UpdateCategoryDto dto, @PathVariable Integer id){
        Category category=categoryService.update(id,dto);
        return ResponseEntity.status(200).body(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        categoryService.delete(id);
        return ResponseEntity.status(204).build();
    }

}