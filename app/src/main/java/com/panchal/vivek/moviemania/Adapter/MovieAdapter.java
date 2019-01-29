package com.panchal.vivek.moviemania.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.panchal.vivek.moviemania.Database.FavModel;
import com.panchal.vivek.moviemania.MainActivity;
import com.panchal.vivek.moviemania.Model.Movie;
import com.panchal.vivek.moviemania.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private Context context;
    private List<Movie> movies;
    private MainActivity activity;
    private List<FavModel> favModels;
    private  MovieItemClickListener movieItemClickListener;

    public interface MovieItemClickListener{
     void onMovieItemClick(int adapterPosition, Movie movie, ImageView image_thumbnail);
    };

    public MovieAdapter(MovieItemClickListener movieItemClickListener, Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
        this.movieItemClickListener = movieItemClickListener;
    }

    public MovieAdapter(MainActivity context, List<FavModel> favMovieList) {
        this.context = context;
        this.favModels = favMovieList;

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movie_img)
        ImageView image_thumbnail;
        @BindView(R.id.image_item_card)
        CardView image_Card;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        String url = context.getResources().getString(R.string.poster_url) + movies.get(position).getPosterPath();
        holder.image_thumbnail.setTransitionName("movie_pic");
        Picasso.get().load(url).into(holder.image_thumbnail);
        holder.image_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                movieItemClickListener.onMovieItemClick(holder.getAdapterPosition(), movies.get(holder.getAdapterPosition()), holder.image_thumbnail);

//                callDetailsActivity(holder.image_thumbnail, movies.get(holder.getAdapterPosition()));
//
//                Intent intent = new Intent(this, DetailsActivity.class);
//// Pass data object in the bundle and populate details activity.
//
//
//                intent.putExtra(DetailsActivity.EXTRA_CONTACT, contact);
//                ActivityOptionsCompat options = ActivityOptionsCompat.
//                        makeSceneTransitionAnimation(this, (View)ivProfile, "profile");
//                startActivity(intent, options.toBundle());
            }
        });

    }



    @Override
    public int getItemCount() {
        return movies.size();
    }


}
