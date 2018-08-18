package com.panchal.vivek.moviemania.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.panchal.vivek.moviemania.Model.TrailerResult;
import com.panchal.vivek.moviemania.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {
    private Context context;
    private List<TrailerResult> trailers;

    public TrailerAdapter(Context context, List<TrailerResult> trailers) {
        this.context = context;
        this.trailers = trailers;
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.trailer_items, parent, false);
        return new TrailerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
        final String key = trailers.get(position).getKey();
        String title = trailers.get(position).getName();
        String trailer_img="http://img.youtube.com/vi/"+key+"/2.jpg";
        Picasso.get().load(trailer_img).into(holder.mTrailer);

        holder.mTrailerTitle.setText(title);
        holder.mTrailerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + key)));

            }
        });
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.trailer_img)
        ImageView mTrailer;

        @BindView(R.id.trailer_Card)
        CardView mTrailerCard;
        @BindView(R.id.movie_trailer_title)
        TextView mTrailerTitle;

        public TrailerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
