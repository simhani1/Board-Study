package dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ArticleDto {
    private int id;
    private String title;
    private String content;
    private Author author;
    private LocalDate createdAt;

    public ArticleDto(int id, String title, String content, Author author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public ArticleDto(int id, String title, String content, Author author, LocalDate createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
    }

}
