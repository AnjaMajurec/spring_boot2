package algebra2.example.spring_boot2.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ArticleRepository extends JpaRepository<Article,Integer> {
    @Query("SELECT a FROM Article a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :searchWord, '%')) OR LOWER(a.description) LIKE LOWER(CONCAT('%', :searchWord, '%'))")
    List<Article> searchByNameOrDescription(@Param("searchWord") String searchWord);


}
