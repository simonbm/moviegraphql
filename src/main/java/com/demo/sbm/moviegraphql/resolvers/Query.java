package com.demo.sbm.moviegraphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.demo.sbm.moviegraphql.models.CastModel;
import com.demo.sbm.moviegraphql.models.GeneraModel;
import com.demo.sbm.moviegraphql.models.MovieModel;
import com.demo.sbm.moviegraphql.models.pojo.TMDBMovie;
import com.demo.sbm.moviegraphql.types.Cast;
import com.demo.sbm.moviegraphql.types.Movie;
import com.demo.sbm.moviegraphql.types.SortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;

@Component
public class Query implements GraphQLQueryResolver {

    private CastModel castModel;
    private MovieModel movieModel;
    private GeneraModel generaModel;

    public List<Movie> movies(SortType sortType, int page) {


        List<TMDBMovie> tmdbMovies = movieModel.getMovies(sortType.toTMDBSortType(),page);
        return tmdbMovies.stream().map(s -> {
            Movie m  = new Movie(s);
            List<String> allGeneraForMovie = s.getGenreIds().stream().map(mv -> this.generaModel.getGenera(mv)).collect(Collectors.toList());
            m.setGenres(allGeneraForMovie);
            List<Cast> castList = castModel.getCastForMovie(s.getId().toString()).stream().map(Cast::new).collect(Collectors.toList());
            m.setCast(castList);
            return m;
        }).collect(Collectors.toList());

    }


    @Autowired
    public void setCastModel(CastModel castModel) {
        this.castModel = castModel;
    }

    @Autowired
    public void setMovieModel(MovieModel movieModel) {
        this.movieModel = movieModel;
    }

    @Autowired
    public void setGeneraModel(GeneraModel generaModel) {
        this.generaModel = generaModel;
    }
}
