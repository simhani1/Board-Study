package alcuk.article.service.dto;

import alcuk.domain.article.Author;

import java.time.LocalDate;

public class ArticleDto {
    private int id;
    private String title;
    private String content;
    private LocalDate createdAt;
    private Author author;

    public ArticleDto(int id, String title, String content, LocalDate createdAt, Author author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.author = author;
    }

    public ArticleDto(String title, String content, Author author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public Author getAuthor() {
        return author;
    }
}
