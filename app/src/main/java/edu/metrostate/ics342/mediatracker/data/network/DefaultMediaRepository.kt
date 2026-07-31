package edu.metrostate.ics342.mediatracker.data.network

import android.media.browse.MediaBrowser
import edu.metrostate.ics342.mediatracker.data.Exceptions
import edu.metrostate.ics342.mediatracker.data.FakeMediaRepository
import edu.metrostate.ics342.mediatracker.data.MediaResult
import edu.metrostate.ics342.mediatracker.data.SessionRepository
import edu.metrostate.ics342.mediatracker.data.model.LibraryItem
import edu.metrostate.ics342.mediatracker.data.model.Media
import edu.metrostate.ics342.mediatracker.data.model.PriorityItem
import kotlin.collections.emptyList

data class MediaPage(
    val items: List<Media>,
    val nextCursor: String?,
    val hasMore: Boolean
)

class DefaultMediaRepository(sessionRepository: SessionRepository) {

    private val api = RetrofitInstance.mediaApiService(sessionRepository)

    suspend fun search(query: String, type: String?, after: String?): MediaPage {
        val response = api.searchMedia(
            query = query.ifBlank { null },
            type = type?.ifBlank { null },
            after = after
        )
        val items = response.body() ?: emptyList()
        val nextCursor = response.headers()["X-Next-Cursor"]
        val hasMore = response.headers()["X-Has-More"] == "true"
        return MediaPage(items, nextCursor, hasMore)
    }

    suspend fun get(id: Int): MediaResult {
        val response = api.getMedia(id)
        when (response.code()) {
            200 -> {
                val body = response.body()!!
                return MediaResult.Success(
                    media = Media(
                        id = body.id,
                        mediaType = body.mediaType,
                        title = body.title,
                        author = body.author,
                        director = body.director,
                        creator = body.creator,
                        network = body.network,
                        coverUrl = body.coverUrl,
                        publishedYear = body.publishedYear,
                        averageRating = body.averageRating,
                        ratingCount = body.ratingCount,
                        genres = body.genres,
                        description = body.description,
                        pageCount = body.pageCount,
                        runtimeMinutes = body.runtimeMinutes,
                        seasonCount = body.seasonCount,
                        episodeCount = body.episodeCount,
                        isbn = body.isbn,
                        reviewCount = body.reviewCount
                    )
                )
            }
            else -> return MediaResult.Error
        }
    }

    suspend fun getLibrary(status: String): List<LibraryItem> {
        val response = api.getLibrary(status)
        return if (response.isSuccessful) {
            response.body() ?: FakeMediaRepository.libraryItems
        } else {
            FakeMediaRepository.libraryItems
        }
    }
    suspend fun addToLibrary(id: Int): LibraryMediaResponse {

        val response = api.addToLibrary(
            LibraryMediaRequest(
                mediaId = id,
                status = "want_to"
            )
        )
        return when (response.code()) {
            201 -> {
                // Created — success
                response.body() ?: throw IllegalStateException("Empty 201 body")
            }
            409 -> {
                throw Exceptions.AlreadyInLibraryException()
            }
            else -> {
                throw IllegalStateException("Unexpected status: ${response.code()}")
            }
        }
    }

    suspend fun isInLibrary(id: Int): Boolean {
        val response = api.getLibraryMedia(id)
        return when (response.code()) {
            200 -> true
            409 -> false
            else -> {
                throw IllegalStateException("Unexpected status: ${response.code()}")
            }
        }
    }

    suspend fun addToFavorites(id: Int): FavoriteResponse? {
        val response = api.addToFavorites(FavoriteRequest(id))
        return when (response.code()){
            201 -> response.body()
            409 -> throw Exceptions.AlreadyInFavoritesException()
            else -> throw IllegalStateException("Unexpected status: ${response.code()}")
        }
    }

    suspend fun getFavorite(id: Int): FavoriteResponse? {
        val response = api.getFavoriteMedia(id)
        return when (response.code()){
            200 -> response.body()
            404 -> throw Exceptions.ItemNotExist()
            else -> throw IllegalStateException("Unexpected status: ${response.code()}")
        }
    }

    suspend fun getPriorities(): List<PriorityItem> {
        val response = api.getPriorities()
        return if (response.isSuccessful) {
            val dto = response.body() ?: emptyList()
            PriorityResponse(dto).priorities
        } else {
            emptyList() // or FakePriorityRepository.priorities
        }
    }

    suspend fun updatePriorityOrder(body: UpdatePriorityOrderRequest): UpdatePriorityOrderResponse? {
        val response = api.updatePriorityOrder(body)
        return when (response.code()){
            200 -> response.body()
            else -> throw IllegalStateException(response.message())
        }
    }
}