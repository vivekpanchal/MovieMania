package com.panchal.vivek.moviemania.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.panchal.vivek.moviemania.Model.Movie;

import java.util.List;

@Dao
public interface MoviesDao{

    @Query("SELECT * FROM movies")
    List<Movie> getAllMovies();

    @Query("SELECT * FROM movies WHERE id = :id")
    LiveData<List<Movie>> getMovies(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(Movie... movie);

    @Update
    void updateMovie(Movie movie);

    @Delete
    void deleteMovies(Movie movie);


}
