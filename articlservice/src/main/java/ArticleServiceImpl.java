import dto.ArticleDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ArticleServiceImpl implements ArticleService {

    private final ArticleServiceImplRepository articleServiceImplRepository;

    @Autowired
    public ArticleServiceImpl(ArticleServiceImplRepository articleServiceImplRepository) {
        this.articleServiceImplRepository = articleServiceImplRepository;
    }

    @Override
    public void createArticle(ArticleDto articleDto) {
        articleServiceImplRepository.createArticle(articleDto);
    }

    @Override
    public List<ArticleDto> readArticle() {
        return articleServiceImplRepository.readArticle();
    }

    @Override
    public void deleteArticle(int id) {
        articleServiceImplRepository.deleteArticle(id);
    }
}
