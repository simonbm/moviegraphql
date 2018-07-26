package com.demo.sbm.moviegraphql.models;

import com.demo.sbm.moviegraphql.models.pojo.TMDBCast;

import java.util.List;

public interface CastModel {

    List<TMDBCast> getCastForMovie(String id);

}
