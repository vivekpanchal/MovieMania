package com.panchal.vivek.moviemania.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.panchal.vivek.moviemania.Model.ReviewResult;
import com.panchal.vivek.moviemania.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    Context context;
    List<ReviewResult> reviewResultList;

    public ReviewAdapter(Context context, List<ReviewResult> reviewResultList) {
        this.context = context;
        this.reviewResultList = reviewResultList;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.review_item_list, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        holder.mAuthorText.setText("--"+(reviewResultList.get(position).getAuthor()));
        holder.mReviewContent.setText(reviewResultList.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return reviewResultList.size();
    }


    class ReviewViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.author)
        TextView mAuthorText;
        @BindView(R.id.content_Review)
        TextView mReviewContent;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
