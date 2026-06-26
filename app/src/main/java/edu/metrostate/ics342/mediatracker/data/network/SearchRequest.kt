package edu.metrostate.ics342.mediatracker.data.network

import kotlinx.serialization.Serializable

@Serializable
data class SearchRequest (
    val query: String,
    val type: String,
    val genre: String?,
    val limit: Int?,
    val after: String?
)
