package com.panchal.vivek.moviemania.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.panchal.vivek.moviemania.Database.MovieDatabase;
import com.panchal.vivek.moviemania.Model.Movie;

import java.util.List;

public class MainViewModel  extends ViewModel{

    private LiveData<List<Movie>> movieLiveData;


   public MainViewModel(MovieDatabase movieDatabase){

       movieLiveData=movieDatabase.moviesDao().getAllMovies();
   }



    public LiveData<List<Movie>> getMoviesResults() {
        return movieLiveData;
    }
}
