package alcuk.controller.article.response;

import alcuk.domain.article.Author;

import java.time.LocalDate;

public class ArticleResponse {
    private int id;
    private String title;
    private String content;
    private LocalDate createdAt;
    private Author author;

    public ArticleResponse() {};

    public ArticleResponse(int id, String title, String content, LocalDate createdAt, Author author) {
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
