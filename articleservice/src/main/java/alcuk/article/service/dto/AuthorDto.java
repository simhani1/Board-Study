package alcuk.article.service.dto;

public class AuthorDto {
    int id;
    String name;

    public AuthorDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
