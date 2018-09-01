package com.panchal.vivek.moviemania;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.panchal.vivek.moviemania.Adapter.FavAdapter;
import com.panchal.vivek.moviemania.Adapter.MovieAdapter;
import com.panchal.vivek.moviemania.Database.FavModel;
import com.panchal.vivek.moviemania.Model.Movie;
import com.panchal.vivek.moviemania.Model.MovieResponse;
import com.panchal.vivek.moviemania.Networking.ApiClient;
import com.panchal.vivek.moviemania.Networking.ApiInterface;
import com.panchal.vivek.moviemania.ViewModel.FavouriteViewModel;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recylerView)
    RecyclerView recyclerView;
    FavAdapter favAdapter;
    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private final static String API_KEY = BuildConfig.API_KEY;
    private static final String TAG = MainActivity.class.getSimpleName();
    public final static String LIST_STATE_KEY = "recycler_list_state";
    Parcelable listState;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intView();
        setupLayoutManager();
        loadPopularMovies();


    }

    private void intView() {
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_sort);
        toolbar.setOverflowIcon(drawable);
        ButterKnife.bind(this);

    }

    private void setupLayoutManager() {


        final int spanCount = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 4 : 2;
        layoutManager = new GridLayoutManager(this, spanCount);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);


    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save list state
        listState = layoutManager.onSaveInstanceState();
        outState.putParcelable(LIST_STATE_KEY, listState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Retrieve list state and list/item positions
        if (savedInstanceState != null) {
            listState = savedInstanceState.getParcelable(LIST_STATE_KEY);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (listState != null) {
            layoutManager.onRestoreInstanceState(listState);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        int orientation = newConfig.orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

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


    public void loadFavMovie() {
        final int spanCount = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 4 : 2;
        layoutManager = new GridLayoutManager(this, spanCount);
        recyclerView.setLayoutManager(layoutManager);
        favAdapter = new FavAdapter(this);
        recyclerView.setAdapter(favAdapter);
        setUpViewModel();
    }

    private void setUpViewModel() {
        FavouriteViewModel viewmodel = ViewModelProviders.of(this).get(FavouriteViewModel.class);
        viewmodel.getFavourites().observe(this, new Observer<List<FavModel>>() {
            @Override
            public void onChanged(@Nullable List<FavModel> favouritesModals) {
                Log.d("Livedata test", "Update list of tasks from livedata i viewmodel");
                if (favouritesModals != null && favouritesModals.size() > 0) {

                    recyclerView.setVisibility(View.VISIBLE);
                    favAdapter.setFavouriteMovies(favouritesModals);

                } else {
                    favAdapter.setFavouriteMovies(null);
                    recyclerView.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Nothing in the Favourites", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


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
                loadFavMovie();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
