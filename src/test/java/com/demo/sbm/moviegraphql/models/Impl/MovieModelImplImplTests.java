package com.demo.sbm.moviegraphql.models.Impl;


import com.demo.sbm.moviegraphql.models.pojo.TMDBMovie;
import com.demo.sbm.moviegraphql.models.pojo.TMDBSortType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class MovieModelImplImplTests {

    @Autowired
    MovieModelImpl movieModel;

    @Test
    public void testMovieModel() {

        List<TMDBMovie> tmdbMovieList =  movieModel.getMovies(TMDBSortType.POPULARITY,1);

        assertThat(tmdbMovieList.size()).isGreaterThan(0);

    }
}
