package com.demo.sbm.moviegraphql;


import com.demo.sbm.moviegraphql.models.CastModel;
import com.demo.sbm.moviegraphql.models.GeneraModel;
import com.demo.sbm.moviegraphql.models.Impl.CastModelImpl;
import com.demo.sbm.moviegraphql.models.Impl.GeneraModelImpl;
import com.demo.sbm.moviegraphql.models.Impl.MovieModelImpl;
import com.demo.sbm.moviegraphql.models.MovieModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieGraphQLConfiguration {

    @Bean
    public MovieModel movieModel() {
        return new MovieModelImpl();
    }

    @Bean
    public CastModel castModel() {
        return new CastModelImpl();
    }

    @Bean
    public GeneraModel generaModel() {
        return new GeneraModelImpl();
    }
}
