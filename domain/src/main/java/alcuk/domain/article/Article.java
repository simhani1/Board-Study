package alcuk.domain.article;

import alcuk.article.service.dto.AuthorDto;

import java.time.LocalDate;

public class Article {

    private final int id;
    private final String title;
    private final String content;
    private final LocalDate createdAt;
    private final AuthorDto authorDto;

    public Article(int id, String title, String content, LocalDate createdAt, AuthorDto authorDto) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.authorDto = authorDto;
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

    public AuthorDto getAuthor() {
        return authorDto;
    }
}
