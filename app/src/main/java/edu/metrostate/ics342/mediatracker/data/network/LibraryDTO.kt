package edu.metrostate.ics342.mediatracker.data.network

import android.media.browse.MediaBrowser
import edu.metrostate.ics342.mediatracker.data.model.LibraryItem
import edu.metrostate.ics342.mediatracker.data.model.Media
import kotlinx.serialization.Serializable

@Serializable
data class LibraryResponse(
    val items: List<LibraryItem>
)

@Serializable
data class LibraryMediaRequest(
    val mediaId: Int,
    val status: String
)

@Serializable
data class LibraryMediaResponse(
    val userId: String,
    val mediaId: Int,
    val status: String,
    val addedAt: String,
    val updatedAt: String,
    val media: Media
)