package alcuk.article.service;

import dto.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
    @DisplayName("게시글 생성")
    public void CREATE_ARTICLE() {
        // given
        String title = "title1";
        String content = "content1";
        Author author = new Author(1, "simhani1");



        // when


        // then

    }
}
