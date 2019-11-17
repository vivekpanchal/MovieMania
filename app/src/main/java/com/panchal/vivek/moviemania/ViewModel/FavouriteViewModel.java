package com.panchal.vivek.moviemania.ViewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;
import android.util.Log;

import com.panchal.vivek.moviemania.Database.FavModel;
import com.panchal.vivek.moviemania.Database.MovieDatabase;

import java.util.List;

public class FavouriteViewModel extends AndroidViewModel {

    private LiveData<List<FavModel>> favourites;
    private MovieDatabase appDatabase;

    public FavouriteViewModel(@NonNull Application application) {
        super(application);
        appDatabase = MovieDatabase.getDatabase(application);
        Log.d("viewmodel test", "retreiving data from database");
        favourites = appDatabase.moviesDao().getAllMovies();
        //and now our viewmodel is ready
    }

    public LiveData<List<FavModel>> getFavourites() {

        return favourites;
    }





}
