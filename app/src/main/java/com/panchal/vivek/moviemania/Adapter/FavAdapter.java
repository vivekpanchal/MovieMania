package com.panchal.vivek.moviemania.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.panchal.vivek.moviemania.Database.FavModel;
import com.panchal.vivek.moviemania.DetailActivity;
import com.panchal.vivek.moviemania.FavouriteActivity;
import com.panchal.vivek.moviemania.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FavAdapter extends RecyclerView.Adapter<FavAdapter.FavouriteViewHolder> {

    private List<FavModel> mFavouriteList;
    private Context mContext;


    public FavAdapter(Context mContext) {
        this.mContext = mContext;
    }


    public class FavouriteViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movie_img)
        ImageView image_thumbnail;
        @BindView(R.id.image_item_card)
        CardView image_Card;

        public FavouriteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.movie_item, parent, false);
        return new FavouriteViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final FavouriteViewHolder holder, int position) {
        final FavModel favModel = mFavouriteList.get(position);
        String url = mContext.getResources().getString(R.string.poster_url) + favModel.getPosterPath();

        Picasso.get().load(url).into(holder.image_thumbnail);
        holder.image_Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, FavouriteActivity.class);
                intent.putExtra("title", favModel.getOriginalTitle());
                intent.putExtra("backdrop", favModel.getBackdropPath());
                intent.putExtra("overview", favModel.getOverview());
                intent.putExtra("releasedate", favModel.getReleaseDate());
                intent.putExtra("vote", favModel.getVoteAverage());
                intent.putExtra("liked", favModel.isFavourite());
                intent.putExtra("movieid", favModel.getMovieid());
                intent.putExtra("movie_poster", favModel.getPosterPath());
                mContext.startActivity(intent);
            }
        });


    }

    public void setFavouriteMovies(List<FavModel> favouriteMovies) {
        this.mFavouriteList = favouriteMovies;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (mFavouriteList == null) {
            return 0;
        }
        return mFavouriteList.size();
    }


}

