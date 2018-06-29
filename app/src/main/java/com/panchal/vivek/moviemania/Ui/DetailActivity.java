package com.panchal.vivek.moviemania.Ui;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.panchal.vivek.moviemania.Model.Movie;
import com.panchal.vivek.moviemania.R;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.movie_backgrund)ImageView movie_bckgrund;
//    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.movie_overview)TextView overView_text;
    @BindView(R.id.movie_poster)ImageView movie_poster;
    @BindView(R.id.movie_title)TextView movie_title;
    @BindView(R.id.release_Date)TextView movie_releaseDate;
    @BindView(R.id.movie_rating)TextView movie_rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
//        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        Movie movie=intent.getParcelableExtra("MovieList");
        String movie_backdrop=movie.getBackdropPath();
        String title=movie.getOriginalTitle();
        String movieposter=movie.getPosterPath();
        Double rating=movie.getVoteAverage();
        String release_Date=movie.getReleaseDate();
        String plotsynopsis=movie.getOverview();

        //setting the views
        Picasso.get().load(movie_backdrop).into(movie_bckgrund);
        Picasso.get().load(movieposter).into(movie_poster);

        movie_title.setText(title);
        movie_releaseDate.setText(release_Date);
        movie_rating.setText(String.valueOf(rating));
        overView_text.setText(plotsynopsis);

    }
}
