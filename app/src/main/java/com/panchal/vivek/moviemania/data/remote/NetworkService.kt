package com.panchal.vivek.moviemania.data.remote

import com.panchal.vivek.moviemania.data.remote.request.DummyRequest
import com.panchal.vivek.moviemania.data.remote.request.LoginRequest
import com.mindorks.bootcamp.demo.data.remote.response.DummyResponse
import com.mindorks.bootcamp.demo.data.remote.response.GeneralResponse
import com.mindorks.bootcamp.demo.data.remote.response.LoginResponse
import com.mindorks.bootcamp.demo.data.remote.response.PostListResponse
import com.panchal.vivek.moviemania.data.remote.Endpoints
import com.panchal.vivek.moviemania.data.remote.Networking
import io.reactivex.Single
import retrofit2.http.*


interface NetworkService {


    @POST(Endpoints.DUMMY)
    fun doDummyCall(
            @Body request: DummyRequest,
            @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY // default value set when Networking create is called
    ): Single<DummyResponse>

    @GET(Endpoints.DUMMY)
    fun doDummyGetCall(
            @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY // default value set when Networking create is called
    ): Single<DummyResponse>


    @GET(Endpoints.HOME_POSTS_LIST)
    fun doHomePostListCall(
            @Query("firstPostId") firstPostId: String?,
            @Query("lastPostId") lastPostId: String?,
            @Header(Networking.HEADER_USER_ID) userId: String,
            @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String,
            @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
    ): Single<PostListResponse>

    @POST(Endpoints.LOGIN)
    fun doLoginCall(
            @Body request: LoginRequest,
            @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
    ): Single<LoginResponse>


    @DELETE(Endpoints.POST_DELETE)
    fun doPostDeleteCall(
            @Path("postId") postId: String,
            @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
    ): Single<GeneralResponse>

}
