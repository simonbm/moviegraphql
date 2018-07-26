package com.demo.sbm.moviegraphql.types;

import com.demo.sbm.moviegraphql.models.pojo.TMDBCast;

public class Cast {

    String id;
    String name;
    String gender;
    String character;

    private TMDBCast tmdbCast;

    public Cast(TMDBCast tmdbCast) {
        this.tmdbCast = tmdbCast;
        id = tmdbCast.getId().toString();
        name = tmdbCast.getName();
        gender = tmdbCast.getGender() == 1 ? "f" : tmdbCast.getGender() == 2 ? "m" : null;
        character = tmdbCast.getCharacter();
    }

    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String photo(int size) {

        String path = "https://image.tmdb.org/t/p/w" + size + tmdbCast.getProfilePath();

        return  path;
    }
}
