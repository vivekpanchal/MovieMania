package com.panchal.vivek.moviemania.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MoviesDao {

    @Query("SELECT * FROM favMovie")
    List<FavModel> getAllMovies();

    @Query("SELECT * FROM favMovie WHERE id = :id")
    List<FavModel> getMovies(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(FavModel... favModels);

    @Query("SELECT * FROM favMovie WHERE movie_id = :movieid")
    FavModel checkifExists(String movieid);

    @Update
    void updateMovie(FavModel favModel);

    @Query("DELETE FROM favMovie WHERE movie_id = :movie_id")
    void deleteFavMovie(String movie_id);


}
