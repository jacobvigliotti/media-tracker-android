package edu.metrostate.ics342.mediatracker.data

interface SearchRepository {
    suspend fun search(
        query: String,
        type: String
    ): SearchResult
}

sealed interface SearchResult {
    data object Success : SearchResult
    data object Conflict : SearchResult
    data object NetworkError : SearchResult
    data object UnknownError : SearchResult
}