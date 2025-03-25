package algebra2.example.spring_boot2.article;

import algebra2.example.spring_boot2.article.dto.CreateArticleDto;
import algebra2.example.spring_boot2.auth.AuthController;
import algebra2.example.spring_boot2.auth.dto.LoginDto;
import algebra2.example.spring_boot2.auth.dto.LoginResponseDto;
import algebra2.example.spring_boot2.category.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ArticleControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc; //preko tog saljemo request na endpointe, staticki dio svakog integ. testa

    @Autowired
    private ObjectMapper objectMapper; //konvertanje body-ja(dto) u json oblik

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleController articleController;

    @Autowired
    private AuthController authController;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired //automatsko injectanje, isto ko final, koristi ili jedno ili drugo
    private CategoryRepository categoryRepository;

    private String accessToken;
    private CreateArticleDto createArticleDto = new CreateArticleDto("Test Hardware",  "Central processing unit", new BigDecimal("999.99"), 1);

    @BeforeEach
    void setUp() throws Exception {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("admin");
        loginDto.setPassword("password");

        LoginResponseDto jwtResponse = authController.login(loginDto).getBody();
        accessToken = jwtResponse.getAccessToken();
    }

    @Test
    void createNewArticle() throws Exception {
        mockMvc.perform(post("/articles")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createArticleDto))
                )
                .andExpect(status().isCreated());
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get("/articles")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(4)))); //$ da respone ima četiri elementa
    }
}
