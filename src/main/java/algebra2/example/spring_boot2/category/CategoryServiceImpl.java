package algebra2.example.spring_boot2.category;

import algebra2.example.spring_boot2.article.Article;
import algebra2.example.spring_boot2.category.dto.CreateCategoryDto;
import algebra2.example.spring_boot2.category.dto.UpdateCategoryDto;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    @Override
    public Optional<Category> findById(Integer id){
        return categoryRepository.findById(id);
    }
    @Override
    public List<Category> fetchAll(){
        return categoryRepository.fetchAll();
    }
    @Override
    public Category create(CreateCategoryDto dto){
        Category category=new Category(dto.getName(),dto.getDescription());
        return categoryRepository.create(category);
    }
    @Override
    public Category update(Integer id, UpdateCategoryDto dto){
        Optional<Category> category=categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new InternalException("Category not found");
        }
        Category categoryForUpdate=category.get();
        categoryForUpdate.setName(dto.getName());
        categoryForUpdate.setDescription(dto.getDescription());
        return categoryRepository.update(categoryForUpdate);
    }
    @Override
    public void delete(Integer id){
        Optional<Category> category=categoryRepository.findById(id);
        if(!category.isPresent()){
            throw new InternalException("Category not found");
        }
        categoryRepository.delete(id);
    }
}
