package edu.metrostate.ics342.mediatracker.data.model

import android.content.Context
import edu.metrostate.ics342.mediatracker.R
import kotlinx.serialization.Serializable

@Serializable
data class Media(
    val id: Int,
    val mediaType: String,
    val title: String,
    val publishedYear: Int,
    val averageRating: Float,
    val ratingCount: Int,
    val genres: List<String>,
    val coverUrl: String? = null,
    val description: String? = null,
    val reviewCount: Int? = null,
    val author: String? = null,          // Books only
    val director: String? = null,        // Movies only
    val creator: String? = null,         // Shows only
    val network: String? = null,         // Shows only
    val pageCount: Int? = null,          // Books only
    val runtimeMinutes: Int? = null,     // Movies only
    val seasonCount: Int? = null,        // Shows only
    val episodeCount: Int? = null,       // Shows only
    val isbn: String? = null,           // Books only
)

/** Returns a human-readable credit line appropriate for the media type. */
fun Media.creatorCredit(context: Context): String = when (mediaType) {
    "book"  -> author   ?: context.getString(R.string.media_unknown_author)
    "movie" -> director ?: context.getString(R.string.media_unknown_director)
    "show"  -> creator  ?: context.getString(R.string.media_unknown_creator)
    else    -> ""
}