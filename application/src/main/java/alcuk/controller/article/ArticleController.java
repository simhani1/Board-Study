package alcuk.controller.article;

import alcuk.article.service.ArticleService;
import alcuk.article.service.dto.ArticleDto;
import alcuk.controller.article.request.ArticleCreateRequest;
import alcuk.controller.article.response.ArticleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ArticleController {

    private ArticleService articleService;

    @PostMapping("/article")
    public void createArticle(@RequestBody ArticleCreateRequest articleCreateRequest) {
        ArticleDto articleDto = new ArticleDto(articleCreateRequest.getTitle(), articleCreateRequest.getContent(), articleCreateRequest.getAuthor());
        articleService.createArticle(articleDto);
    }

    @GetMapping("/article")
    public List<ArticleResponse> readArticle() {
        List<ArticleDto> articleDtos = articleService.readArticle();
        List<ArticleResponse> articleResponseList = new ArrayList<ArticleResponse>();
        for (ArticleDto articleDto : articleDtos) {
            ArticleResponse articleResponse;
            articleResponseList.add(articleResponse = new ArticleResponse(articleDto.getId(), articleDto.getTitle(), articleDto.getContent(), articleDto.getCreatedAt(), articleDto.getAuthor()));
        }
        return articleResponseList;
    }

    @DeleteMapping("/article/{id}")
    public void deleteArticle(@PathVariable("id") int id) {
        articleService.deleteArticle(id);
    }

    @Autowired
    private ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }
}
