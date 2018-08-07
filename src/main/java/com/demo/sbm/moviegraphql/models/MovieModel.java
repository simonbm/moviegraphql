package com.demo.sbm.moviegraphql.models;

import com.demo.sbm.moviegraphql.models.pojo.TMDBMovie;
import com.demo.sbm.moviegraphql.models.pojo.TMDBSortType;

import java.util.List;

public interface MovieModel {

    public List<TMDBMovie> getMovies(TMDBSortType sortBy, int page);
}
