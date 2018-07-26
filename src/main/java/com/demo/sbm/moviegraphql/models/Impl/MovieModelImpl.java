package com.demo.sbm.moviegraphql.models.Impl;

import com.demo.sbm.moviegraphql.models.CastModel;
import com.demo.sbm.moviegraphql.models.MovieModel;
import com.demo.sbm.moviegraphql.models.pojo.TMDBCast;
import com.demo.sbm.moviegraphql.models.pojo.TMDBMovie;
import com.demo.sbm.moviegraphql.models.pojo.MovieRoot;
import com.demo.sbm.moviegraphql.models.pojo.TMDBSortType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MovieModelImpl implements MovieModel {
    private static final Logger log = LoggerFactory.getLogger(MovieModelImpl.class);

    @Autowired
    CastModel castModel;

    @Value("${moviedb.url}") String movieBaseURL;
    @Value("${moviedb.apiKey}") String movieDBApiKey;




    @Override
    public List<TMDBMovie> getMovies(TMDBSortType sortBy, int page) {


        TMDBSortType selectedSortBy = sortBy != null  ? sortBy : TMDBSortType.RELEASE_DATE;


        StringBuilder sb  = new StringBuilder();
        sb.append(movieBaseURL).
                append("discover/movie").
                append("?page=").
                append(page).
                append("&sort_by=").
                append(selectedSortBy.toString()).
                append("&api_key=").
                append(movieDBApiKey);

        ObjectMapper mapper = new ObjectMapper();
        try {
            MovieRoot movieRoot = mapper.readValue(new URL(sb.toString()), MovieRoot.class);
            return movieRoot.getResults();
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }

        return new ArrayList<TMDBMovie>();
    }
}
