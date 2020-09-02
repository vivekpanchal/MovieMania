//package com.panchal.vivek.moviemania.Repository
////package com.panchal.vivek.moviemania.Repository;
//import android.app.Application
//import android.arch.lifecycle.LiveData
//import android.os.AsyncTask
//import androidx.lifecycle.LiveData
//import com.panchal.vivek.moviemania.Database.MovieDatabase
//import com.panchal.vivek.moviemania.Database.MoviesDao
//import com.panchal.vivek.moviemania.Model.Movie
//
//class MovieRepository(application: Application?) {
//    private val mMoviesDao: MoviesDao
//    private val mAllMoviesList: LiveData<List<Movie>>? = null
//    fun getmAllMoviesList(): LiveData<List<Movie>>? {
//        return mAllMoviesList
//    }
//
//    fun insert(movie: Movie?) {
//        insertAsyncTask(mMoviesDao).execute(movie)
//    }
//
//    fun update(movie: Movie?) {
//        updateAsyncTask(mMoviesDao).execute(movie)
//    }
//
//    fun delete(movie: Movie?) {
//        deleteAsyncTask(mMoviesDao).execute(movie)
//    }
//
//    //inserting task using AsyncTask
//    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: MoviesDao) : AsyncTask<Movie?, Void?, Void?>() {
//        protected override fun doInBackground(vararg params: Movie): Void? {
//            mAsyncTaskDao.insertMovie(params[0])
//            return null
//        }
//    }
//
//    //for updating the MovieDao for the latest movie
//    private class updateAsyncTask internal constructor(private val mAsyncTaskDao: MoviesDao) : AsyncTask<Movie?, Void?, Void?>() {
//        protected override fun doInBackground(vararg params: Movie): Void? {
//            mAsyncTaskDao.updateMovie(params[0])
//            return null
//        }
//    }
//
//    //for deleting the movie object
//    private class deleteAsyncTask internal constructor(private val mAsyncTaskDao: MoviesDao) : AsyncTask<Movie?, Void?, Void?>() {
//        protected override fun doInBackground(vararg params: Movie): Void? {
//            mAsyncTaskDao.updateMovie(params[0])
//            return null
//        }
//    }
//
//    init {
//        val db = MovieDatabase.getDatabase(application)
//        mMoviesDao = db.moviesDao()
//        //        mAllMoviesList = db.moviesDao().getAllMovies();
//    }
//}