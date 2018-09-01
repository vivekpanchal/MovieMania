package com.panchal.vivek.moviemania;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.like.LikeButton;
import com.panchal.vivek.moviemania.Database.FavModel;
import com.panchal.vivek.moviemania.Database.MovieDatabase;
import com.panchal.vivek.moviemania.utils.AppExecutors;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouriteActivity extends AppCompatActivity {
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

    private FavModel favModel;
    private MovieDatabase movieDatabase;
    private static List<FavModel> moviesInDatabaseList = new ArrayList<>();
    boolean isFavourite=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        ButterKnife.bind(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        movieDatabase = MovieDatabase.getDatabase(getApplicationContext());


        Intent intent = getIntent();
        String movietitle = intent.getStringExtra("title");
        String backdropImage = intent.getStringExtra("backdrop");
        String ovewviewMovie = intent.getStringExtra("overview");
        String releasemvieDate = intent.getStringExtra("releasedate");
        String voterating = intent.getStringExtra("vote");
        String movieposter = intent.getStringExtra("movie_poster");
        boolean fav = intent.getBooleanExtra("liked", true);


        String backdrop_url = getResources().getString(R.string.backdrop_url) + backdropImage;
        String poster_url = getResources().getString(R.string.poster_url) + movieposter;
        //setting the views
        Picasso.get().load(backdrop_url).into(movie_bckgrund);
        Picasso.get().load(poster_url).into(movie_poster);

        movie_title.setText(movietitle);
        movie_releaseDate.setText(releasemvieDate);
        movie_rating.setText(String.valueOf(voterating));
        overView_text.setText(ovewviewMovie);
        likeButton.setLiked(true);

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isFavourite) {
                    isFavourite = false;
                    likeButton.setLiked(true);

                } else {
                    isFavourite = true;
                    deleteFromDb();
                    likeButton.setLiked(false);
                    finish();
                }


            }
        });


    }

    private void deleteFromDb() {
        Intent intent = getIntent();

        final String movieId = intent.getStringExtra("movieid");
        AppExecutors.getInstance().diskIO().execute(new Runnable() {

            @Override
            public void run() {
                movieDatabase.moviesDao().deleteFavMovie(movieId);


            }
        });
    }
}
