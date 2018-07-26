package com.demo.sbm.moviegraphql.types;

import com.demo.sbm.moviegraphql.models.pojo.TMDBSortType;

public enum SortType {
    POPULARITY(TMDBSortType.POPULARITY),
    RELEASE_DATE(TMDBSortType.RELEASE_DATE);

    private final TMDBSortType tmdbSortType;

    private SortType(TMDBSortType tmdbSortType) {
        this.tmdbSortType = tmdbSortType;
    }

    public TMDBSortType toTMDBSortType() {
        return tmdbSortType;
    }


}
