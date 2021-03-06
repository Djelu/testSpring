package com.fantasy.dao.objects;

/**
 * Created by Djelu on 03.09.2017.
 */
public class MP3 {

    private int id;
    private String name;
    private Author author;

    public MP3(String name, Author author) {
        this.name = name;
        this.author = author;
    }

    public MP3(int id, String name, Author author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
}
