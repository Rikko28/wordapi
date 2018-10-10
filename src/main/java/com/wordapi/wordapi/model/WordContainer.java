package com.wordapi.wordapi.model;

public class WordContainer {
    private String word;
    private long id;

    public WordContainer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
