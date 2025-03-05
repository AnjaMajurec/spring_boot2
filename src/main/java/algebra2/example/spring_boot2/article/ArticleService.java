package algebra2.example.spring_boot2.article;
import algebra2.example.spring_boot2.article.dto.CreateArticleDto;
import algebra2.example.spring_boot2.article.dto.UpdateArticleDto;
import algebra2.example.spring_boot2.category.Category;
import algebra2.example.spring_boot2.category.CategoryRepository;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;

    public ArticleService(ArticleRepository articleRepository, CategoryRepository categoryRepository){
        this.articleRepository=articleRepository;
        this.categoryRepository = categoryRepository;
    }
    public List<Article> fetchAll(){
        return articleRepository.fetchAll();
    }
    public Optional<Article> findById(Integer id){
        return articleRepository.findById(id);
    }
    public Article create(CreateArticleDto dto){
        Optional<Category> category=categoryRepository.findById((dto.getCategoryId()));
        if(category.isEmpty()){
            throw  new InternalException(("Category not found"));
        }
        Article article=new Article(dto.getName(),dto.getDescription(),dto.getPrice(),category.get());
        return articleRepository.create(article);
    }

    public Article update(Integer id, UpdateArticleDto dto){
        Optional<Article> article=articleRepository.findById(id);
        if(!article.isPresent()){
            throw new InternalException("Article not found");
        }
        Optional<Category> category=categoryRepository.findById((dto.getCategoryId()));
        if(category.isEmpty()){
            throw  new InternalException(("Category not found"));
        }
        Article articleForUpdate=article.get();
        articleForUpdate.setName(dto.getName());
        articleForUpdate.setDescription(dto.getDescription());
        articleForUpdate.setPrice(dto.getPrice());
        articleForUpdate.setCategory(category.get());

        return articleRepository.update(articleForUpdate);


    }
}
