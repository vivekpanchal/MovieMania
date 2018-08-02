package com.panchal.vivek.moviemania.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.panchal.vivek.moviemania.Adapter.TrailerAdapter;
import com.panchal.vivek.moviemania.BuildConfig;
import com.panchal.vivek.moviemania.Database.MovieDatabase;
import com.panchal.vivek.moviemania.Model.Movie;
import com.panchal.vivek.moviemania.Model.Trailer;
import com.panchal.vivek.moviemania.Model.TrailerResult;
import com.panchal.vivek.moviemania.Networking.ApiClient;
import com.panchal.vivek.moviemania.Networking.ApiInterface;
import com.panchal.vivek.moviemania.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.movie_backgrund)
    KenBurnsView movie_bckgrund;
    //    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.movie_overview)
    TextView overView_text;
    @BindView(R.id.movie_poster)
    ImageView movie_poster;
    @BindView(R.id.movie_title)
    TextView movie_title;
    @BindView(R.id.release_Date)
    TextView movie_releaseDate;
    @BindView(R.id.movie_rating)
    TextView movie_rating;
    @BindView(R.id.favbtn)
    LikeButton favbtn;

    @BindView(R.id.scroll_view)
    ScrollView view;


    private Movie movie_obj;
    private MovieDatabase movieDatabase;
    private static List<Movie> moviesInDatabaseList;
    private List<TrailerResult> results;
    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private final static String API_KEY = BuildConfig.API_KEY;
    private static final String TAG = DetailActivity.class.getSimpleName();

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
//        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.trailer_recyclerview);


        //instantiating database
        movieDatabase = MovieDatabase.getDatabase(getApplicationContext());
        moviesInDatabaseList = movieDatabase.moviesDao().getAllMovies();


        Intent intent = getIntent();
        final Movie movie = intent.getParcelableExtra("MovieList");
        final int movie_id = movie.getId();
        final String movie_backdrop = movie.getBackdropPath();
        final String title = movie.getTitle();
        final String movieposter = movie.getPosterPath();
        final Double rating = movie.getRating();
        final String release_Date = movie.getReleaseDate();
        String plotsynopsis = movie.getOverview();


        String backdrop_url = getResources().getString(R.string.backdrop_url) + movie_backdrop;
        String poster_url = getResources().getString(R.string.poster_url) + movieposter;

        //setting the views
        Picasso.get().load(backdrop_url).into(movie_bckgrund);
        Picasso.get().load(poster_url).into(movie_poster);

        movie_title.setText(title);
        movie_releaseDate.setText(release_Date);
        movie_rating.setText(String.valueOf(rating));
        overView_text.setText(plotsynopsis);


        loadTrailer(String.valueOf(movie_id));
        favbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 0;
                do {
                    if (moviesInDatabaseList.size() == 0) {
                        movieDatabase.moviesDao().insertMovie(movie);
                        movie.setFavourite(true);
                        Snackbar snackbar=Snackbar.make(view,"Added to favourite",Snackbar.LENGTH_SHORT);
                        snackbar.show();
                        favbtn.setLiked(true);
                        movieDatabase.moviesDao().updateMovie(movie);
                        break;
                    }if (movie.getId()!=null){
                        if (moviesInDatabaseList.get(i).getId().equals(movie.getId())) {
                            movieDatabase.moviesDao().deleteMovies(movie);
                            movie.setFavourite(false);
                            Snackbar snackbar=Snackbar.make(view,"Removed from favourite",Snackbar.LENGTH_SHORT);
                            snackbar.show();
                            favbtn.setLiked(false);
                            movieDatabase.moviesDao().updateMovie(movie);
                            break;
                        } else if (!moviesInDatabaseList.get(i).getId().equals(movie_id)) {
                            movieDatabase.moviesDao().insertMovie(movie);
                            movie.setFavourite(true);
                            Snackbar snackbar=Snackbar.make(view,"Added to favourite",Snackbar.LENGTH_SHORT);
                            snackbar.show();
                            favbtn.setLiked(true);
                            movieDatabase.moviesDao().updateMovie(movie);
                            break;
                        }
                    }

                    i++;
                } while (i < moviesInDatabaseList.size());
            }
        });


    }


    private void fetchTrailer(Call<Trailer> call) {
        call.enqueue(new Callback<Trailer>() {
            @Override
            public void onResponse(Call<Trailer> call, Response<Trailer> response) {
                if (response.body() != null) {
                    try {
                        assert response.body() != null;
                        List<TrailerResult> result = response.body().getResults();
                        recyclerView.setAdapter(new TrailerAdapter(DetailActivity.this, result));
                        recyclerView.setLayoutManager(new LinearLayoutManager(DetailActivity.this,LinearLayoutManager.HORIZONTAL,false));
                    } catch (NullPointerException e) {
                        Log.d(TAG, "onResponse: null pointer Exception ");
                    }
                }
            }

            @Override
            public void onFailure(Call<Trailer> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Oops No connection", Toast.LENGTH_SHORT).show();
                Log.e(TAG, t.toString());
            }
        });
    }

    private void loadTrailer(String id) {
        Call<Trailer> call = apiInterface.getMovieTrailers(id, API_KEY);
        fetchTrailer(call);
    }
}
