package com.demo.sbm.moviegraphql.models.Impl;

import com.demo.sbm.moviegraphql.models.CastModel;
import com.demo.sbm.moviegraphql.models.MovieModel;
import com.demo.sbm.moviegraphql.models.pojo.TMDBCast;
import com.demo.sbm.moviegraphql.models.pojo.TMDBMovie;
import com.demo.sbm.moviegraphql.models.pojo.MovieRoot;
import com.demo.sbm.moviegraphql.models.pojo.TMDBSortType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class MovieModelImpl implements MovieModel {
    private static final Logger log = LoggerFactory.getLogger(MovieModelImpl.class);


    private CastModel castModel;

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


        try {
            MovieRoot movieRoot = getMovieRoot(sb.toString());
            return movieRoot.getResults();
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }

        return new ArrayList<>();
    }


    @HystrixCommand(fallbackMethod = "defaultMovieRoot")
    private MovieRoot getMovieRoot(String url) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return  mapper.readValue(new URL(url), MovieRoot.class);
    }

    private MovieRoot defaultMovieRoot(Map<String, Object> parameters) {
        return new MovieRoot();
    }


    @Autowired
    public void setCastModel(CastModel castModel) {
        this.castModel = castModel;
    }
}
