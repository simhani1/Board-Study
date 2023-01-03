package alcuk.repository.article;

import alcuk.article.service.dto.ArticleDto;
import alcuk.article.service.spi.ArticleServiceRepository;
import alcuk.domain.article.Article;
import memorydatabase.ArticleMemoryDataBase;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MemoryArticleServiceRepository implements ArticleServiceRepository {

    @Override
    public void createArticle(ArticleDto articleDto) {
        Article article = new Article(ArticleMemoryDataBase.autoIncrementId.getAndIncrement(),
                articleDto.getTitle(),
                articleDto.getContent(),
                LocalDate.now(),
                articleDto.getAuthor());

        ArticleMemoryDataBase.articles.add(article);
    }

    @Override
    public List<ArticleDto> readArticle() {
        return null;
    }

    @Override
    public void deleteArticle(int id) {

    }
}
