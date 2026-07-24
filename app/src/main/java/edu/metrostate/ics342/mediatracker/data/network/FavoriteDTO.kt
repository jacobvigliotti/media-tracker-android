package edu.metrostate.ics342.mediatracker.data.network

import edu.metrostate.ics342.mediatracker.data.model.Media
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class FavoriteRequest(
    val mediaId: Int
)

@Serializable
data class FavoriteResponse(
    val userId: String,
    val mediaType: Int,
    val createdAt: String,
    val media: Media
)