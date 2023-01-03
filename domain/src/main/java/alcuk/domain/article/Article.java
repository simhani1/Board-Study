package alcuk.domain.article;

import alcuk.article.service.dto.*;

import java.time.LocalDate;

public class Article {

    private final int id;
    private final String title;
    private final String content;
    private final LocalDate createdAt;
    private final Author author;

    public Article(int id, String title, String content, LocalDate createdAt, Author author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
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
