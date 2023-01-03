package alcuk.acceptance.article;

import alcuk.controller.article.request.ArticleCreateRequest;
import alcuk.controller.article.response.ArticleResponse;
import alcuk.domain.article.Author;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ArticleAcceptanceTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final List<ArticleResponse> resultList;
    {
        resultList = new ArrayList<>();
    }

    @BeforeEach
    @AfterEach
    public void DELETE_ALL_ARTICLE() throws Exception {
        for (ArticleResponse articleResponse : resultList) {
            mvc.perform(MockMvcRequestBuilders.delete("/article/{id}", articleResponse.getId()));
        }
        throwIfDoesNotDeletedAllUser();
    }

    private void throwIfDoesNotDeletedAllUser() throws Exception {
        for (ArticleResponse articleResponse : resultList) {
            Assertions.assertThrows(AssertionError.class, () ->
                    mvc.perform(MockMvcRequestBuilders.get("/article"))
                            .andExpectAll(
                                    MockMvcResultMatchers.status().isOk(),
                                    MockMvcResultMatchers.content().string("")
                            )
            );
        }
    }

    @Test
    @DisplayName("게시글 생성 및 조회 성공 테스트")
    public void CREATE_ARTICLE() throws Exception {
        // given
        String url = "/article";
        ArticleCreateRequest request = new ArticleCreateRequest("title1", "content1", new Author(1, "simhani1"));

        // when
        mvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)));
        ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/article"));
        List<ArticleResponse> articleResponse = Arrays.asList(objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), ArticleResponse[].class));
        resultList.add(articleResponse.get(0));

        // then
        result.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.jsonPath("$.[0].title").value(request.getTitle()),
                MockMvcResultMatchers.jsonPath("$.[0].content").value(request.getContent()),
                MockMvcResultMatchers.jsonPath("$.[0].author.id").value(request.getAuthor().getId()),
                MockMvcResultMatchers.jsonPath("$.[0].author.name").value(request.getAuthor().getName())
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("전체 게시글 조회 테스트")
    public void READ_ALL_ARTICLES() throws Exception {
        // given
        String saveUrl = "/article";
        String readUrl = "/article";
        ArticleCreateRequest articleRequest1 = new ArticleCreateRequest("title1", "content1", new Author(1, "simhani1"));
        ArticleCreateRequest articleRequest2 = new ArticleCreateRequest("title2", "content2", new Author(1, "simhani1"));

        // when
        mvc.perform(MockMvcRequestBuilders.post(saveUrl).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(articleRequest1)));
        mvc.perform(MockMvcRequestBuilders.post(saveUrl).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(articleRequest2)));
        ResultActions result = mvc.perform(MockMvcRequestBuilders.get(readUrl));
        List<ArticleResponse> articleResponse = Arrays.asList(objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), ArticleResponse[].class));
        resultList.add(articleResponse.get(0));
        resultList.add(articleResponse.get(1));

        // then
        result.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.jsonPath("$.[0].title").value(articleRequest1.getTitle()),
                MockMvcResultMatchers.jsonPath("$.[0].content").value(articleRequest1.getContent()),
                MockMvcResultMatchers.jsonPath("$.[0].author.id").value(articleRequest1.getAuthor().getId()),
                MockMvcResultMatchers.jsonPath("$.[0].author.name").value(articleRequest1.getAuthor().getName()),

                MockMvcResultMatchers.jsonPath("$.[1].title").value(articleRequest2.getTitle()),
                MockMvcResultMatchers.jsonPath("$.[1].content").value(articleRequest2.getContent()),
                MockMvcResultMatchers.jsonPath("$.[1].author.id").value(articleRequest2.getAuthor().getId()),
                MockMvcResultMatchers.jsonPath("$.[1].author.name").value(articleRequest2.getAuthor().getName())
        ).andDo(MockMvcResultHandlers.print());
    }

}
