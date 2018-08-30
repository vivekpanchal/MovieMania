package com.panchal.vivek.moviemania.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.panchal.vivek.moviemania.Database.MovieDatabase;
import com.panchal.vivek.moviemania.Database.MoviesDao;
import com.panchal.vivek.moviemania.Model.Movie;
import com.panchal.vivek.moviemania.utils.AppExecutors;

import java.util.List;

public class MainViewModel  extends ViewModel{

    private LiveData<List<Movie>>moviesEntity;
    private AppExecutors executors=AppExecutors.getInstance();
    private MoviesDao moviesDao;


    public MainViewModel(@NonNull MovieDatabase movieDatabase , String id) {
    //   moviesEntity = movieDatabase.moviesDao().getAllMovies(id);
    }

    public LiveData<List<Movie>> getMoviesResults() {
        return moviesEntity;
    }


}
