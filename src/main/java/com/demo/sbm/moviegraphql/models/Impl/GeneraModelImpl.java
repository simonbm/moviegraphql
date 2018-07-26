package com.demo.sbm.moviegraphql.models.Impl;

import com.demo.sbm.moviegraphql.models.GeneraModel;
import com.demo.sbm.moviegraphql.models.pojo.GeneraRoot;
import com.demo.sbm.moviegraphql.models.pojo.TMDBGenre;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;


@Component
public class GeneraModelImpl  implements GeneraModel {

    private static final Logger log = LoggerFactory.getLogger(GeneraModelImpl.class);


    private List<TMDBGenre> genres;

    @Value("${moviedb.url}") String movieBaseURL;
    @Value("${moviedb.apiKey}") String movieDBApiKey;


    private void initObject() {
        if (genres == null ) {
            StringBuilder sb = new StringBuilder();
            sb.append(movieBaseURL).
                    append("genre/movie/list").
                    append("?api_key=").
                    append(movieDBApiKey);
            ObjectMapper mapper = new ObjectMapper();
            try {
                GeneraRoot generaRoot = mapper.readValue(new URL(sb.toString()), GeneraRoot.class);
                genres = generaRoot.getGenres();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    @Override
    public String getGenera(Integer i) {
        initObject();
        Optional<TMDBGenre> result = genres.stream().filter(g -> g.getId().equals(i)).findFirst();
          if (result.isPresent()) {
              return result.get().getName();
          }

          return null;
    }
}
