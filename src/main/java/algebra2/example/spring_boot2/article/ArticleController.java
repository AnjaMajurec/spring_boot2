package algebra2.example.spring_boot2.article;

import algebra2.example.spring_boot2.article.dto.CreateArticleDto;
import algebra2.example.spring_boot2.article.dto.UpdateArticleDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/articles")

public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(@Qualifier("articleServiceImpl") ArticleService articleService){
        this.articleService=articleService;
    }

    @GetMapping
    public ResponseEntity<List<Article>> fetchAll(){
        List<Article> articles= articleService.fetchAll();

        List<String> articleNamesFromForLoop = new ArrayList<>();
        for (Article article : articles) {
            articleNamesFromForLoop.add(article.getName());
        }

        List<String> articleNames = articles.stream().map(Article::getName).toList(); //stream-prođi, map-mapiraj
        // TODO: Proučiti svakako jer ce vam trebati u svakodnevnom programiranju kasnije

        return ResponseEntity.status(200).body(articles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> findById(@PathVariable Integer id){
        Optional<Article> article= articleService.findById(id);
        if(article.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(article.get());

    }
    @PostMapping()
    public ResponseEntity<Article> create(@Valid @RequestBody CreateArticleDto dto){
        Article article= articleService.create(dto);
        return ResponseEntity.status(201).body(article);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Article> update(@Valid @RequestBody UpdateArticleDto dto, @PathVariable Integer id){
        Article article= articleService.update(id,dto);
        return ResponseEntity.status(200).body(article);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        articleService.delete(id);
        return ResponseEntity.status(204).build();
    }
    @GetMapping("/search")
    public ResponseEntity<List<Article>> searchArticles(@RequestParam String searchWord) {
        List<Article> articles = articleService.searchByNameOrDescription(searchWord);
        if (articles.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(articles);
    }
    @GetMapping("/top-expensive")
    public ResponseEntity<Article> searchByMostExpansiveArticle(){
        Optional<Article> article=articleService.searchByMostExpansiveArticle();
        if(article.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(article.get());
    }

    @GetMapping("/count-articles/{categoryId}")
    public ResponseEntity<Double> countArticlesByCategoryId(@PathVariable Integer categoryId){
        Double result= articleService.countArticlesByCategoryId(categoryId);
        return ResponseEntity.status(200).body(result);

    }

    @GetMapping("/priceRange-category")
    public ResponseEntity<List<Article>> searchByMinAndMaxPriceAndCategoryId(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice, @RequestParam Integer categoryId){
        List<Article> articles=articleService.searchByMinAndMaxPriceAndCategoryId(minPrice,maxPrice,categoryId);
        if(articles.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(articles);
    }
}
