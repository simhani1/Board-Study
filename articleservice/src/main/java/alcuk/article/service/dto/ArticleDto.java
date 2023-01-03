package alcuk.article.service.dto;

import java.time.LocalDate;

public class ArticleDto {
    private int id;
    private String title;
    private String content;
    private LocalDate createdAt;
    private AuthorDto authorDto;

    // 게시글 생성
    public ArticleDto(String title, String content, AuthorDto authorDto) {
        this.title = title;
        this.content = content;
        this.authorDto = authorDto;
    }

    // 게시글 조회
    public ArticleDto(int id, String title, String content, LocalDate createdAt, AuthorDto authorDto) {
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
