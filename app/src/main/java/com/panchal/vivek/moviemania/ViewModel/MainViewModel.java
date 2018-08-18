package com.panchal.vivek.moviemania.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.panchal.vivek.moviemania.Database.MovieDatabase;
import com.panchal.vivek.moviemania.Model.Movie;

import java.util.List;

public class MainViewModel  extends ViewModel{

    private LiveData<Movie> moviesEntity;

    public MainViewModel(@NonNull MovieDatabase movieDatabase , String id) {
//        moviesEntity = movieDatabase.moviesDao().getMovies(id);
    }

    public LiveData<Movie> getMoviesResults() {
        return moviesEntity;
    }


}
