package com.demo.sbm.moviegraphql.models.pojo;

public enum TMDBSortType {

    POPULARITY("popularity.desc"),
    RELEASE_DATE("popularity.desc");

    private final String text;

    TMDBSortType(final String s) {
        this.text = s;
    }

    @Override
    public String toString() {
        return text;
    }

}
