package alcuk.repository.article;

import alcuk.article.service.dto.ArticleDto;
import alcuk.article.service.spi.ArticleServiceRepository;
import alcuk.domain.article.Article;
import memorydatabase.ArticleMemoryDataBase;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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
        List<ArticleDto> articles = new ArrayList<>();
        for (Article article : ArticleMemoryDataBase.articles) {
            articles.add(new ArticleDto(article.getId(),
                    article.getTitle(),
                    article.getContent(),
                    article.getCreatedAt(),
                    article.getAuthor()));
        }
        return articles;
    }

    @Override
    public void deleteArticle(int id) {
        for (Article article : ArticleMemoryDataBase.articles) {
            if (article.getId() == id) {
                ArticleMemoryDataBase.articles.remove(id);
                return;
            }
        }
    }

    public void deleteAll() {
        ArticleMemoryDataBase.articles.clear();
    }
}
