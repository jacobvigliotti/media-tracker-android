package edu.metrostate.ics342.mediatracker.data.network

import edu.metrostate.ics342.mediatracker.data.LoginResult
import edu.metrostate.ics342.mediatracker.data.MediaResult
import edu.metrostate.ics342.mediatracker.data.SessionRepository
import edu.metrostate.ics342.mediatracker.data.model.Media
import java.io.IOException

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
}