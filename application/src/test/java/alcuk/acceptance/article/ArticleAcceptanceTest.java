package alcuk.acceptance.article;

import alcuk.controller.user.response.UserResponse;
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
    }

    @Test
    @DisplayName("게시글 생성 성공 테스트")
    public void CREATE_ARTICLE() throws Exception {
        // given
        String url = "/article";
        ArticleCreateRequest request = new ArticleCreateRequest("title1", "content1", new Author(1, "simhani1"));

        // when
        mvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)));
        ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/article"));
        ArticleResponse articleResponse = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), ArticleResponse.class);
        resultList.add(articleResponse);

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
        List<ArticleResponse> articleResponse = Arrays.asList(objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), ArticleResponse.class));
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

    class ArticleResponse {

        public ArticleResponse() {
        }

        public ArticleResponse(int id, String title, String content, String createdAt, Author author) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.createdAt = createdAt;
            this.author = author;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }

        int id;
        String title;
        String content;
        String createdAt;
        Author author;
    }



    class ArticleCreateRequest {
        public ArticleCreateRequest(){}
        public ArticleCreateRequest(String title, String content, Author author) {
            this.title = title;
            this.content = content;
            this.author = author;
        }

        String title;
        String content;
        Author author;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }


    }

    class Author {
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Author(int id, String name) {
            this.id = id;
            this.name = name;
        }

        int id;
        String name;
    }

}
