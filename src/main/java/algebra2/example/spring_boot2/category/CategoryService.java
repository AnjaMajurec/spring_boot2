package algebra2.example.spring_boot2.category;

import algebra2.example.spring_boot2.category.dto.CreateCategoryDto;
import algebra2.example.spring_boot2.category.dto.UpdateCategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<Category> findById(Integer id);

    List<Category> fetchAll();

    Category create(CreateCategoryDto dto);

    Category update(Integer id, UpdateCategoryDto dto);

    void delete(Integer id);
}
