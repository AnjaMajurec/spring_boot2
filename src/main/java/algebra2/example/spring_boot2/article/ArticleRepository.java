package algebra2.example.spring_boot2.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Repository
public interface ArticleRepository extends JpaRepository<Article,Integer> {
    //Pretraga artikala po nazivu ili opisu
    @Query("SELECT a FROM Article a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR LOWER(a.description) LIKE LOWER(CONCAT('%', :searchWord, '%'))")
    List<Article> searchByNameOrDescription(@Param("searchWord") String searchWord);

    //Dohvat najskupljeg artikla
    @Query("SELECT a FROM Article a ORDER BY a.price DESC LIMIT 1")
    Optional<Article> searchByMostExpansiveArticle();

    //Dohvaćanje broja artikala za kategoriju po id-u
    @Query("SELECT COUNT(a) FROM Article a WHERE a.category.id = :categoryId")
    double countArticlesByCategoryId(@Param("categoryId") Integer categoryId);

    //Dohvaćanje svih artikala s mogućnošću filtriranja po cijeni i kategoriji
    @Query("SELECT a FROM Article a WHERE a.price >= :minPrice AND a.price <= :maxPrice AND a.category.id = :categoryId")
    List<Article> searchByMinAndMaxPriceAndCategoryId(BigDecimal minPrice, BigDecimal maxPrice, Integer categoryId);
}

