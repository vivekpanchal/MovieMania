package com.panchal.vivek.moviemania;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.like.LikeButton;
import com.panchal.vivek.moviemania.Adapter.ReviewAdapter;
import com.panchal.vivek.moviemania.Adapter.TrailerAdapter;
import com.panchal.vivek.moviemania.Database.FavModel;
import com.panchal.vivek.moviemania.Database.MovieDatabase;
import com.panchal.vivek.moviemania.Model.Movie;
import com.panchal.vivek.moviemania.Model.ReviewResult;
import com.panchal.vivek.moviemania.Model.Reviews;
import com.panchal.vivek.moviemania.Model.Trailer;
import com.panchal.vivek.moviemania.Model.TrailerResult;
import com.panchal.vivek.moviemania.Networking.ApiClient;
import com.panchal.vivek.moviemania.Networking.ApiInterface;
import com.panchal.vivek.moviemania.ViewModel.FavouriteViewModel;
import com.panchal.vivek.moviemania.utils.AppExecutors;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.movie_backgrund)
    KenBurnsView movie_bckgrund;
    @BindView(R.id.toolbar_detail)
    Toolbar toolbar;
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
    LikeButton likeButton;

    @BindView(R.id.scroll_view)
    ScrollView view;

    private Movie movie;
    private FavModel favModel;
    private MovieDatabase movieDatabase;
    private static List<FavModel> moviesInDatabaseList = new ArrayList<>();
    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private final static String API_KEY = BuildConfig.API_KEY;
    private static final String TAG = DetailActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    @BindView(R.id.review_recycle)
    RecyclerView mReviewRecycler;

    private Boolean isFavourite = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.trailer_recyclerview);

        //instantiating database
        movieDatabase = MovieDatabase.getDatabase(getApplicationContext());


        isfavourite();

        Intent intent = getIntent();
        movie = intent.getParcelableExtra("MovieList");
        final int movie_id = movie.getId();
        final String movie_backdrop = movie.getBackdropPath();
        final String title = movie.getTitle();
        final String movieposter = movie.getPosterPath();
        final Double rating = movie.getRating();
        final String release_Date = movie.getReleaseDate();
        final String plotsynopsis = movie.getOverview();


        String backdrop_url = getResources().getString(R.string.backdrop_url) + movie_backdrop;
        String poster_url = getResources().getString(R.string.poster_url) + movieposter;

        //setting the views
        Picasso.get().load(backdrop_url).into(movie_bckgrund);
        Picasso.get().load(poster_url).into(movie_poster);

        movie_title.setText(title);
        movie_releaseDate.setText(release_Date);
        movie_rating.setText(String.valueOf(rating));
        overView_text.setText(plotsynopsis);

        //loading the trailers
        loadTrailer(String.valueOf(movie_id));
        loadReviews(String.valueOf(movie_id));


        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isFavourite) {
                    removeMovieFromList();
                    isFavourite = false;
                    likeButton.setLiked(false);
                    Snackbar snackbar = Snackbar.make(view, "removed to favourite", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                } else {
                    addToMovieList();
                    isFavourite = true;
                    likeButton.setLiked(true);
                    Snackbar snackbar = Snackbar.make(view, "added to favourite", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }


            }
        });


    }


    private void loadReviews(String id) {
        Call<Reviews> reviewsCall = apiInterface.getMovieReviews(id, API_KEY);
        reviewsCall.enqueue(new Callback<Reviews>() {
            @Override
            public void onResponse(Call<Reviews> call, Response<Reviews> response) {
                if (response.body() != null) {
                    try {
                        assert response.body() != null;
                        List<ReviewResult> reviewResults = response.body().getResults();
                        mReviewRecycler.setAdapter(new ReviewAdapter(DetailActivity.this, reviewResults));
                        mReviewRecycler.setLayoutManager(new LinearLayoutManager(DetailActivity.this));
                    } catch (NullPointerException e) {

                        Log.d(TAG, "onResponse: pointer Exception " + e);
                    }
                }

            }

            @Override
            public void onFailure(Call<Reviews> call, Throwable t) {

                Toast.makeText(DetailActivity.this, "Oops No internet Connection", Toast.LENGTH_SHORT).show();

            }
        });


    }


    private void loadTrailer(String id) {
        Call<Trailer> call = apiInterface.getMovieTrailers(id, API_KEY);
        call.enqueue(new Callback<Trailer>() {
            @Override
            public void onResponse(Call<Trailer> call, Response<Trailer> response) {
                if (response.body() != null) {
                    try {
                        assert response.body() != null;
                        List<TrailerResult> result = response.body().getResults();
                        recyclerView.setAdapter(new TrailerAdapter(DetailActivity.this, result));
                        recyclerView.setLayoutManager(new LinearLayoutManager(DetailActivity.this, LinearLayoutManager.HORIZONTAL, false));
                    } catch (NullPointerException e) {
                        Log.d(TAG, "onResponse:Exception " + e);
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

    private void removeMovieFromList() {

        final String id = movie.getId().toString();

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                movieDatabase.moviesDao().deleteFavMovie(id);

            }
        });
    }


    private void addToMovieList() {

        Intent intent = getIntent();
        movie = intent.getParcelableExtra("MovieList");
        final String id = movie.getId().toString();
        final String backdropPath = movie.getBackdropPath();
        final String favTitle = movie.getTitle();
        final String favPoster = movie.getPosterPath();
        final String favRating = movie.getRating().toString();
        final String favReleasedate = movie.getReleaseDate();
        final String fav_overview = movie.getOverview();

        final FavModel favModel = new FavModel(id, favRating, favTitle, favPoster, backdropPath, fav_overview, favReleasedate, true);

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (favModel.isFavourite()) {
                    movieDatabase.moviesDao().insertMovie(favModel);

                }
            }
        });
    }


    private void isfavourite() {

        Intent intent = getIntent();
        movie = intent.getParcelableExtra("MovieList");
        final String movieId = movie.getId().toString();

        final FavModel[] movieResponse = new FavModel[1];

        AppExecutors.getInstance().mainThread().execute(new Runnable() {
            @Override
            public void run() {
                movieResponse[0] = movieDatabase.moviesDao().checkifExists(movieId);
                //If the movie belongs to user favourites then it will be shown as liked
                if (movieResponse[0] != null) {
                    likeButton.setLiked(movieResponse[0].isFavourite());
                    isFavourite = true;

                } else {
                    likeButton.setLiked(false);
                    isFavourite = false;
                }
            }
        });
    }
}




