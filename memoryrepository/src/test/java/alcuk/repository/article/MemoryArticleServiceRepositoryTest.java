package alcuk.repository.article;

import dto.ArticleDto;
import dto.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MemoryArticleServiceRepositoryTest.class})
public class MemoryArticleServiceRepositoryTest {

    @Autowired
    private MemoryArticleServiceRepositoryTest repository;

    @Test
    @DisplayName("게시글 생성 및 조회")
    public void CREATE_ARTICLES() {
        // given
        ArticleDto articleDto = new ArticleDto(1, "title1", "conttent1", new Author(1, "simhani1"));

        // when
        repository.createArticle(articleDto);
        ArticleDto result = repository.readArtilce(articleDto.getAuthor().getName());

        // then
        Assertions.assertAll(
                () -> Assertions.assertEquals(articleDto.getId(), result.getId()),
                () -> Assertions.assertEquals(articleDto.getTitle(), result.getTitle()),
                () -> Assertions.assertEquals(articleDto.getContent(), result.getContent()),
                () -> Assertions.assertEquals(articleDto.getAuthor().getId(), result.getAuthor().getId()),
                () -> Assertions.assertEquals(articleDto.getAuthor().getName(), result.getAuthor().getName()),
                () -> Assertions.assertEquals(articleDto.getCreatedAt(), result.getCreatedAt())
        );

    }
}
