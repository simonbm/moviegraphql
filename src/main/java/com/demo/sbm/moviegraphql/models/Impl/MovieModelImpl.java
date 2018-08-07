package com.demo.sbm.moviegraphql.models.Impl;

import com.demo.sbm.moviegraphql.models.CastModel;
import com.demo.sbm.moviegraphql.models.MovieModel;
import com.demo.sbm.moviegraphql.models.pojo.TMDBMovie;
import com.demo.sbm.moviegraphql.models.pojo.MovieRoot;
import com.demo.sbm.moviegraphql.models.pojo.TMDBSortType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieModelImpl implements MovieModel {
    private static final Logger log = LoggerFactory.getLogger(MovieModelImpl.class);


    private CastModel castModel;

    @Value("${moviedb.url}") String movieBaseURL;
    @Value("${moviedb.apiKey}") String movieDBApiKey;

    @HystrixCommand(fallbackMethod = "getDefaultMovies",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value="60"),
                    @HystrixProperty(name = "execution.isolation.strategy", value="SEMAPHORE"),
            })
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



    private MovieRoot getMovieRoot(String url) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return  mapper.readValue(new URL(url), MovieRoot.class);
    }

    private List<TMDBMovie> getDefaultMovies(TMDBSortType sortBy, int page) {
        return new ArrayList<>();
    }


    @Autowired
    public void setCastModel(CastModel castModel) {
        this.castModel = castModel;
    }
}
