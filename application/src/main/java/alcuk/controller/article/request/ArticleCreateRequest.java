package alcuk.controller.article.request;

import alcuk.domain.article.Author;

public class ArticleCreateRequest {

    private String title;
    private String content;
    private Author author;

    public ArticleCreateRequest() {}

    public ArticleCreateRequest(String title, String content, Author author) {
        this.title = title;
        this.content = content;
        this.author = author;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
