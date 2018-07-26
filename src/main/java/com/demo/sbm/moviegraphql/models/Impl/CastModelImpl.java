package com.demo.sbm.moviegraphql.models.Impl;

import com.demo.sbm.moviegraphql.models.CastModel;
import com.demo.sbm.moviegraphql.models.pojo.TMDBCast;
import com.demo.sbm.moviegraphql.models.pojo.CastRoot;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class CastModelImpl implements CastModel {
    private static final Logger log = LoggerFactory.getLogger(CastModelImpl.class);

    @Value("${moviedb.url}") String movieBaseURL;
    @Value("${moviedb.apiKey}") String movieDBApiKey;

    @Override
    public List<TMDBCast> getCastForMovie(String id) {

        StringBuilder sb  = new StringBuilder();
        sb.append(movieBaseURL).
                append("movie/").
                append(id).
                append("/credits").
                append("?api_key=").
                append(movieDBApiKey);
        ObjectMapper mapper = new ObjectMapper();
        try {
            CastRoot castRoot = mapper.readValue(new URL(sb.toString()), CastRoot.class);
            return castRoot.getCast();
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }

        return new ArrayList<TMDBCast>();

    }
}
