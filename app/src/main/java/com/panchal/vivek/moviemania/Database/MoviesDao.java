package com.panchal.vivek.moviemania.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MoviesDao {

    @Query("SELECT * FROM favMovie")
    LiveData<List<FavModel>> getAllMovies();

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
