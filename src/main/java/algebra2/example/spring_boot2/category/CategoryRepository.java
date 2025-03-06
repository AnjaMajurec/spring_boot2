package algebra2.example.spring_boot2.category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Optional<Category> findById(Integer id);

    List<Category> fetchAll();

    Category create(Category category);

    Category update(Category category);

    void delete(Integer id);
}
