package com.panchal.vivek.moviemania.Database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
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
