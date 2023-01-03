package alcuk.article.service;

import alcuk.article.service.spi.ArticleServiceRepository;
import alcuk.article.service.dto.AuthorDto;
import alcuk.article.service.dto.ArticleDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DefaultArticleService.class})
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @MockBean
    private ArticleServiceRepository articleServiceRepository;

    @Test
    @DisplayName("게시글 생성 및 조회")
    public void CREAT_AND_READ_ARTICLE() {
        // given
        String title = "title1";
        String content = "content1";
        AuthorDto authorDto = new AuthorDto(1, "simhani1");
        ArticleDto createArticleRequest = new ArticleDto(title, content, authorDto);
        List<ArticleDto> response = new ArrayList<ArticleDto>();
        response.add(createArticleRequest);

        // when
        Mockito.when(articleServiceRepository.readArticle()).thenReturn(response);

        articleService.createArticle(createArticleRequest);
        List<ArticleDto> result = articleService.readArticle();

        // then
        Assertions.assertAll(
                () -> Assertions.assertEquals(title, result.get(0).getTitle()),
                () -> Assertions.assertEquals(content, result.get(0).getContent()),
                () -> Assertions.assertEquals(authorDto.getId(), result.get(0).getAuthor().getId()),
                () -> Assertions.assertEquals(authorDto.getName(), result.get(0).getAuthor().getName())
        );
    }

    @Test
    @DisplayName("게시글 삭제")
    public void DELETE_ARTICLE() {
        // given
        int id = 1;

        // then
        Assertions.assertDoesNotThrow(() -> articleService.deleteArticle(id));
    }

}
