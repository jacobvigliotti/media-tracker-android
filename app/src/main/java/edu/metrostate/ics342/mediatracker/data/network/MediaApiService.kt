package edu.metrostate.ics342.mediatracker.data.network

import edu.metrostate.ics342.mediatracker.data.model.LibraryItem
import edu.metrostate.ics342.mediatracker.data.model.Media
import edu.metrostate.ics342.mediatracker.data.model.PriorityItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
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


    @GET("library")
    suspend fun getLibrary(
        @Query("status") status: String,
        @Query("limit") limit: Int? = null,
        @Query("after") after: String? = null
    ): Response<List<LibraryItem>>

    @GET("library/{id}")
    suspend fun getLibraryMedia(
        @Path("id") id: Int
    ): Response<LibraryMediaResponse>

    @POST("library")
    suspend fun addToLibrary(
        @Body body: LibraryMediaRequest): Response<LibraryMediaResponse>

    @GET("favorites/{id}")
    suspend fun getFavoriteMedia(
        @Path("id") id: Int
    ): Response<FavoriteResponse>

    @POST("favorites")
    suspend fun addToFavorites(
        @Body body: FavoriteRequest): Response<FavoriteResponse>

    @GET("priorities")
    suspend fun getPriorities(): Response<List<PriorityItem>>

    @PUT("priorities")
    suspend fun updatePriorityOrder(
        @Body body: UpdatePriorityOrderRequest): Response<UpdatePriorityOrderResponse>





}