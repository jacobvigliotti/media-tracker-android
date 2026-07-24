package edu.metrostate.ics342.mediatracker.data.network

import edu.metrostate.ics342.mediatracker.data.model.Media
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MediaApiService {
    @GET("media")
    suspend fun searchMedia(
        @Query("query") query: String? = null,
        @Query("type") type: String? = null,
        @Query("limit") limit: Int = 20,
        @Query("after") after: String? = null
    ): Response<List<Media>>

    @GET("media/{id}")
    suspend fun getMedia(
        @Path("id") id: Int
    ): Response<Media>


    @GET("library/{id}")
    suspend fun getLibraryMedia(
        @Path("id") id: Int
    ): Response<LibraryResponse>

    @POST("library")
    suspend fun addToLibrary(
        @Body body: LibraryRequest): Response<LibraryResponse>

    @GET("favorites/{id}")
    suspend fun getFavoriteMedia(
        @Path("id") id: Int
    ): Response<FavoriteResponse>

    @POST("favorites")
    suspend fun addToFavorites(
        @Body body: FavoriteRequest): Response<FavoriteResponse>




}