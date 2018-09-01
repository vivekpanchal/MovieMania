package com.panchal.vivek.moviemania.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.panchal.vivek.moviemania.Database.FavModel;
import com.panchal.vivek.moviemania.Database.MovieDatabase;
import com.panchal.vivek.moviemania.Database.MoviesDao;
import com.panchal.vivek.moviemania.Model.Movie;
import com.panchal.vivek.moviemania.utils.AppExecutors;

import java.util.List;

public class FavouriteViewModel extends AndroidViewModel {

    private LiveData<List<FavModel>> favourites;

    public FavouriteViewModel(@NonNull Application application) {
        super(application);
        MovieDatabase appDatabase = MovieDatabase.getDatabase(application);
        Log.d("viewmodel test", "retreiving data from database");
        favourites = appDatabase.moviesDao().getAllMovies();
        //and now our viewmodel is ready
    }

    public LiveData<List<FavModel>> getFavourites() {

        return favourites;
    }

}
