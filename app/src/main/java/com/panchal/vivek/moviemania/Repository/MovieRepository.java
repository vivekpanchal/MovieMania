package com.panchal.vivek.moviemania.Repository;//package com.panchal.vivek.moviemania.Repository;
//
//import android.app.Application;
//import android.arch.lifecycle.LiveData;
//import android.os.AsyncTask;
//
//import com.panchal.vivek.moviemania.Database.MovieDatabase;
//import com.panchal.vivek.moviemania.Database.MoviesDao;
//import com.panchal.vivek.moviemania.Model.Movie;
//
//import java.util.List;
//
//public class MovieRepository {
//
//    private MoviesDao mMoviesDao;
//    private LiveData<List<Movie>> mAllMoviesList;
//
//
//    public MovieRepository(Application application) {
//        MovieDatabase db=MovieDatabase.getDatabase(application);
//        mMoviesDao = db.moviesDao();
////        mAllMoviesList = db.moviesDao().getAllMovies();
//    }
//
//    LiveData<List<Movie>> getmAllMoviesList() {
//
//        return mAllMoviesList;
//    }
//
//    public void insert(Movie movie){
//        new insertAsyncTask(mMoviesDao).execute(movie);
//    }
//
//
//    public void  update(Movie movie){
//        new updateAsyncTask(mMoviesDao).execute(movie);
//    }
//
//
//    public void delete(Movie movie){
//        new deleteAsyncTask(mMoviesDao).execute(movie);
//    }
//
//
//
////inserting task using AsyncTask
//    private static class insertAsyncTask extends AsyncTask<Movie, Void, Void> {
//
//        private MoviesDao mAsyncTaskDao;
//
//        insertAsyncTask(MoviesDao dao) {
//            mAsyncTaskDao = dao;
//        }
//
//        @Override
//        protected Void doInBackground(final Movie... params) {
//            mAsyncTaskDao.insertMovie(params[0]);
//            return null;
//        }
//    }
//
////for updating the MovieDao for the latest movie
//    private static class updateAsyncTask extends AsyncTask<Movie, Void, Void> {
//
//        private MoviesDao mAsyncTaskDao;
//
//        updateAsyncTask(MoviesDao dao) {
//            mAsyncTaskDao = dao;
//        }
//
//        @Override
//        protected Void doInBackground(final Movie... params) {
//            mAsyncTaskDao.updateMovie(params[0]);
//            return null;
//        }
//    }
//
////for deleting the movie object
//    private static class deleteAsyncTask extends AsyncTask<Movie, Void, Void> {
//
//        private MoviesDao mAsyncTaskDao;
//
//        deleteAsyncTask(MoviesDao dao) {
//            mAsyncTaskDao = dao;
//        }
//
//        @Override
//        protected Void doInBackground(final Movie... params) {
//            mAsyncTaskDao.updateMovie(params[0]);
//            return null;
//        }
//    }
//
//
//
//
//}
