package algebra2.example.spring_boot2.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ArticleRepository extends JpaRepository<Article,Integer> {

    @Query("SELECT a FROM Article a WHERE a.category.name=?1")
    Optional <Article> findByName(String name);

}
