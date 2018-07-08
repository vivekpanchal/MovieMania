package com.panchal.vivek.moviemania.Ui;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.panchal.vivek.moviemania.Database.MovieDatabase;
import com.panchal.vivek.moviemania.Model.Movie;
import com.panchal.vivek.moviemania.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.movie_backgrund)
    ImageView movie_bckgrund;
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
    ImageButton favbtn;

    private Movie movie_obj;
    private MovieDatabase movieDatabase;
    private static List<Movie> moviesInDatabaseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
//        setSupportActionBar(toolbar);


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

        //setting the views
        Picasso.get().load(movie_backdrop).into(movie_bckgrund);
        Picasso.get().load(movieposter).into(movie_poster);

        movie_title.setText(title);
        movie_releaseDate.setText(release_Date);
        movie_rating.setText(String.valueOf(rating));
        overView_text.setText(plotsynopsis);

        favbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 0;
                do {
                    if (moviesInDatabaseList.size() == 0) {
                        movieDatabase.moviesDao().insertMovie(movie);
                        movie.setFavourite(true);
                        Toast.makeText(DetailActivity.this, "ADDED to Favourite", Toast.LENGTH_SHORT).show();
                        movieDatabase.moviesDao().updateMovie(movie);
                        break;
                    }
                    if (moviesInDatabaseList.get(i).getId().equals(movie.getId())) {
                        movieDatabase.moviesDao().deleteMovies(movie);
                        movie.setFavourite(false);
                        Toast.makeText(DetailActivity.this, "REMOVED from Favourite", Toast.LENGTH_SHORT).show();
                        movieDatabase.moviesDao().updateMovie(movie);
                        break;
                    }
                    if (i == moviesInDatabaseList.size() - 1) {
                        movieDatabase.moviesDao().insertMovie(movie);
                        movie.setFavourite(true);
                        Toast.makeText(DetailActivity.this, "ADDED to Favourite", Toast.LENGTH_SHORT).show();
                        movieDatabase.moviesDao().updateMovie(movie);
                        break;
                    }

                    i++;
                } while (i < moviesInDatabaseList.size());
            }
        });
    }
}
