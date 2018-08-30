package com.panchal.vivek.moviemania.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "favMovie")
public class FavModel {


    public FavModel(int id, String movieid, String posterPath, String originalTitle, String backdropPath, String overview, String releaseDate, boolean favourite) {
        this.id = id;
        this.movieid = movieid;
        this.posterPath = posterPath;
        this.originalTitle = originalTitle;
        this.backdropPath = backdropPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.favourite = favourite;

    }

    @Ignore
    public FavModel(String movieid, String voteAverage, String originalTitle, String posterPath, String backdropPath, String overview, String releaseDate, boolean favourite) {
        this.movieid = movieid;
        this.voteAverage = voteAverage;

        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.favourite = favourite;

    }

    @PrimaryKey(autoGenerate = true)
    private int id;


    @ColumnInfo(name = "movie_id")
    private String movieid;


    @ColumnInfo(name = "vote")
    private String voteAverage;

    @ColumnInfo(name = "poster_path")
    private String posterPath;

    @ColumnInfo(name = "original_title")
    private String originalTitle;

    @ColumnInfo(name = "backdrop_path")
    private String backdropPath;

    private String overview;

    private String releaseDate;
    private boolean favourite;

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMovieid(String movieid) {
        this.movieid = movieid;
    }


    public String getMovieid() {
        return movieid;
    }


    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }


    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }


    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }


    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }


    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
