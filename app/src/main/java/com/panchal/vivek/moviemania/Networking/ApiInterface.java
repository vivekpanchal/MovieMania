package com.panchal.vivek.moviemania.Networking;


import com.panchal.vivek.moviemania.Model.MovieResponse;
import com.panchal.vivek.moviemania.Model.Reviews;
import com.panchal.vivek.moviemania.Model.Trailer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {

    //getting the Popular Movie
    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String api_key);

    //getting the Top rated Movies
    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String api_key);

    @GET("movie/{id}")
    Call<MovieResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/{id}/reviews")
    Call<Reviews> getMovieReviews(@Path(value = "id", encoded = true) String id , @Query("api_key")String api_key);

    @GET("movie/{id}/videos")
    Call<Trailer> getMovieTrailers(@Path(value = "id", encoded = true) String id , @Query("api_key")String api_key);

}
