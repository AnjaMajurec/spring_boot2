package algebra2.example.spring_boot2.article;

import algebra2.example.spring_boot2.article.dto.CreateArticleDto;
import algebra2.example.spring_boot2.article.dto.UpdateArticleDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ArticleService {

    List<Article> fetchAll();

    Optional<Article> findById(Integer id);

    Article create(CreateArticleDto dto);

    Article update(Integer id, UpdateArticleDto dto);

    void delete(Integer id);

    List<Article> searchByNameOrDescription(String searchWord);

    Optional<Article> searchByMostExpansiveArticle();

    double countArticlesByCategoryId(Integer categoryId);

    List<Article> searchByMinAndMaxPriceAndCategoryId(BigDecimal minPrice, BigDecimal maxPrice, Integer categoryId);



}
