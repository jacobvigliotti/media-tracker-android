package edu.metrostate.ics342.mediatracker.data

import edu.metrostate.ics342.mediatracker.data.model.Media
import edu.metrostate.ics342.mediatracker.data.model.UserProfile

interface MediaRepository {
    suspend fun getMedia(id: Int): MediaResult
}

sealed interface MediaResult {
    data class Success(
        val media: Media
    ) : MediaResult
    data object Error : MediaResult
}

