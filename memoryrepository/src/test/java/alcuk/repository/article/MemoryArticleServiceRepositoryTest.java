package alcuk.repository.article;

import alcuk.article.service.dto.ArticleDto;
import alcuk.domain.article.Article;
import alcuk.domain.article.Author;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MemoryArticleServiceRepository.class})
public class MemoryArticleServiceRepositoryTest {

    @Autowired
    private MemoryArticleServiceRepository repository;

    @BeforeEach
    @AfterEach
    public void DELETE_ALL_ARTICLE(){
        repository.deleteAll();
    }

    @Test
    @DisplayName("게시글 생성 및 조회 테스트")
    public void CREATE_AND_READ_ARTICLE_TEST() {
        // given
        ArticleDto articleDto = new ArticleDto("title1", "content1", new Author(1, "simhani1"));

        // when
        repository.createArticle(articleDto);
        List<ArticleDto> result = repository.readArticle();

        // then
        Assertions.assertAll(
                () -> Assertions.assertEquals(articleDto.getTitle(), result.get(0).getTitle()),
                () -> Assertions.assertEquals(articleDto.getContent(), result.get(0).getContent()),
                () -> Assertions.assertEquals(articleDto.getAuthor().getId(), result.get(0).getAuthor().getId()),
                () -> Assertions.assertEquals(articleDto.getAuthor().getName(), result.get(0).getAuthor().getName())
        );
    }

    @Test
    @DisplayName("게시글 생성 및 삭제 테스트")
    public void CREATR_ARTICLE_AND_DELETE_ARTICLE_TEST() {
        // given
        ArticleDto articleDto = new ArticleDto("title1", "content1", new Author(1, "simhani1"));

        // when
        repository.createArticle(articleDto);
        List<ArticleDto> savedArtilce = repository.readArticle();
        repository.deleteArticle(savedArtilce.get(0).getId());
        List<ArticleDto>result = repository.readArticle();

        // then
        Assertions.assertEquals(result.isEmpty(), true);
    }
}
