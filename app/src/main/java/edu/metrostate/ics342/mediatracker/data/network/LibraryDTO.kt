package edu.metrostate.ics342.mediatracker.data.network

import edu.metrostate.ics342.mediatracker.data.model.Media
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class LibraryRequest(
    val mediaId: Int,
    val status: String
)

@Serializable
data class LibraryResponse(
    val userId: String,
    val mediaId: Int,
    val status: String,
    val addedAt: String,
    val updatedAt: String,
    val media: Media
)