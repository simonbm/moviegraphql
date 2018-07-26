package com.demo.sbm.moviegraphql.types;

import com.demo.sbm.moviegraphql.models.pojo.TMDBMovie;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    private String id;
    private String title;
    private String overview;
    private Double score;
    private int voteCount;
    private Double popularity;
    private List<String> genres = new ArrayList<>();
    private String releaseDate;
    private List<Cast> cast = new ArrayList<>();
    private boolean isLiked;

    private TMDBMovie tmdbMovie;

    public Movie(TMDBMovie tmdbMovie) {
        this.tmdbMovie = tmdbMovie;
        id = tmdbMovie.getId().toString();
        title = tmdbMovie.getTitle();
        overview = tmdbMovie.getOverview();
        score = tmdbMovie.getVoteAverage();
        voteCount = tmdbMovie.getVoteCount();
        popularity = tmdbMovie.getPopularity();
        releaseDate = tmdbMovie.getReleaseDate();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }


    public String poster(int size) {

        String path = "https://image.tmdb.org/t/p/w" + size + tmdbMovie.getBackdropPath();

        return  path;
    }
}
