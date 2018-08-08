package com.panchal.vivek.moviemania.ViewModel;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.panchal.vivek.moviemania.Database.MovieDatabase;

public class ViewModelFactory extends ViewModelProvider.AndroidViewModelFactory {

    private final MovieDatabase movieDatabase;
    private final String movieId;


    public ViewModelFactory(@NonNull Application application, MovieDatabase movieDatabase, String movieId) {
        super(application);
        this.movieDatabase = movieDatabase;
        this.movieId = movieId;
    }


    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(movieDatabase);
    }

}
