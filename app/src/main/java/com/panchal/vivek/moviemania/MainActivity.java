package com.panchal.vivek.moviemania;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.panchal.vivek.moviemania.Adapter.MovieAdapter;
import com.panchal.vivek.moviemania.Database.FavModel;
import com.panchal.vivek.moviemania.Database.MovieDatabase;
import com.panchal.vivek.moviemania.Model.Movie;
import com.panchal.vivek.moviemania.Model.MovieResponse;
import com.panchal.vivek.moviemania.Networking.ApiClient;
import com.panchal.vivek.moviemania.Networking.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recylerView)
    RecyclerView recyclerView;
    MovieAdapter adapter;
    private List<FavModel> favMovieList;
    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private final static String API_KEY = BuildConfig.API_KEY;
    private MovieDatabase movieDatabase;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intView();
        loadPopularMovies();
        favMovieList = new ArrayList<>();
    }

    private void intView() {
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        movieDatabase = MovieDatabase.getDatabase(getApplicationContext());
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_sort);
        toolbar.setOverflowIcon(drawable);
        ButterKnife.bind(this);

        final int spanCount = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 4 : 2;
        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
        recyclerView.setLayoutManager(layoutManager);

    }


    private void fetchData(Call<MovieResponse> call) {
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.body() != null) {
                    try {
                        assert response.body() != null;
                        List<Movie> movies = response.body().getResults();
                        recyclerView.setAdapter(new MovieAdapter(MainActivity.this, movies));
                    } catch (NullPointerException e) {
                        Log.d(TAG, "onResponse: null pointer aarha h ");
                    }
                }

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Oops No connection", Toast.LENGTH_SHORT).show();
                Log.e(TAG, t.toString());

            }
        });
    }

    public void loadPopularMovies() {
        Call<MovieResponse> call = apiInterface.getPopularMovies(API_KEY);
        fetchData(call);
    }

    public void loadTopRatedMovies() {
        Call<MovieResponse> call = apiInterface.getTopRatedMovies(API_KEY);
        fetchData(call);
    }


//    public void loadFavMovie() {
//
//        if (favMovieList != null) {
//            favMovieList.clear();
//        }
//
//        if (movieDatabase.moviesDao().getAllMovies().size() == 0) {
//
//            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
//        } else {
//
//            for (int i = 0; i < movieDatabase.moviesDao().getAllMovies().size(); i++) {
//                FavModel result = new Movie(movieDatabase.moviesDao().getAllMovies().get(i).getId(),
//                        movieDatabase.moviesDao().getAllMovies().get(i).getOriginalTitle(),
//                        movieDatabase.moviesDao().getAllMovies().get(i).getOverview(),
//                        movieDatabase.moviesDao().getAllMovies().get(i).getPosterPath(),
//                        movieDatabase.moviesDao().getAllMovies().get(i).getOverview(),
//                        movieDatabase.moviesDao().getAllMovies().get(i).getReleaseDate(),
//                        movieDatabase.moviesDao().getAllMovies().get(i).getBackdropPath(),
//
//                favMovieList.add(result);
//            }
//        }
//
//        adapter = new MovieAdapter(this, favMovieList);
//        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.top_rated:
                loadTopRatedMovies();
                return true;
            case R.id.popular:
                loadPopularMovies();
                return true;

            case R.id.favourite:
//                loadFavMovie();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
