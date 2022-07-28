package ru.job4j.generics;

public class Role extends Base {

    private final String post;

    public Role(String id, String post) {
        super(id);
        this.post = post;
    }

    public String getPost() {
        return post;
    }
}
