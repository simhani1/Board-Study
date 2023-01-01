package dto;

import lombok.Getter;

@Getter
public class Author {
    private int id;
    private String name;

    public Author(int id, String name) {
        this.id = id;
        this.name = name;
    }

}
