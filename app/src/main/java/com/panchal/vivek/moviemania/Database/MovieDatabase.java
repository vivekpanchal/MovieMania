package com.panchal.vivek.moviemania.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;


@Database(entities = {FavModel.class}, version = 1 ,exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    private static String TAG=MovieDatabase.class.getSimpleName();

    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "favDb";
    public abstract MoviesDao moviesDao();
    private static MovieDatabase INSTANCE;

    public static MovieDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    //create Database
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MovieDatabase.class, DATABASE_NAME)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        Log.d(TAG, "getDatabase instance  ");
        return INSTANCE;
    }


}
