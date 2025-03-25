package algebra2.example.spring_boot2.article;

import algebra2.example.spring_boot2.article.dto.CreateArticleDto;
import algebra2.example.spring_boot2.category.Category;
import algebra2.example.spring_boot2.category.CategoryRepository;
import org.apache.logging.log4j.util.InternalException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticleServiceImplTest {
    @InjectMocks
    private ArticleServiceImpl articleService;

    @Mock
    private ArticleRepository articleRepositoryMock;

    @Mock
    private CategoryRepository categoryRepositoryMock;

    @BeforeEach
    public void init(){ //da se uključe sve potrebne anotacije
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateSuccess(){
        CreateArticleDto dto=new CreateArticleDto("Article name", null, BigDecimal.TEN,1); //create metoda zahtjeva dto
        //imitiramo request iz Postmana
        Category mockCategory=new Category(1,"Testna kategorija","nekakav description");
        Article article=new Article(dto.getName(), dto.getDescription(),dto.getPrice(),mockCategory); //ne uspoređuj s tim

        //kad imamo vanjsku implementaciju onda pozivamo when jer mockamo te podatke, u ovom slucaju iz Category Repository
        when(categoryRepositoryMock.findById(dto.getCategoryId())).thenReturn(Optional.of(mockCategory));
        when(articleRepositoryMock.save(article)).thenReturn(article);
        Article savedArticle=articleService.create(dto);
        assertNotNull(savedArticle);
        assertEquals(savedArticle.getName(), dto.getName());
        assertEquals(savedArticle.getCategory().getId(), dto.getCategoryId());
        assertNull(savedArticle.getDescription());

        verify(articleRepositoryMock,times(1)).save(any());  //koliko puta se pozvala neka funkcija s određenim parametrima
        verify(categoryRepositoryMock, times(1)).findById(dto.getCategoryId());

    }
    @Test
    public void testCreateWhenCategoryNotFound(){
        CreateArticleDto dto = new CreateArticleDto("Article name", null, BigDecimal.TEN, 1);

        assertThrows(InternalException.class, () -> articleService.create(dto));
    }



}