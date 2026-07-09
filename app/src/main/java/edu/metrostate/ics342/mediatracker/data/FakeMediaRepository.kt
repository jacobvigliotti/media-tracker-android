package edu.metrostate.ics342.mediatracker.data

import edu.metrostate.ics342.mediatracker.data.model.*

/**
 * Hardcoded fake data used throughout the app while real API integration is built out.
 *
 * This object stays in the project all semester. If a student falls behind on API wiring,
 * their app still runs and shows real-looking content.
 */
object FakeMediaRepository {

    val currentUser = UserProfile(
        id           = "user-001",
        email        = "alex@example.com",
        username     = "alexreads",
        displayName  = "Alex Chen",
        bio          = "Avid reader and film buff. Always looking for the next great story.",
        avatarUrl    = null,
        followerCount  = 12,
        followingCount = 8,
        trackedCount   = 47,
        createdAt    = "2023-09-01T00:00:00Z"
    )

    val mediaList = listOf(
        Media(id = 1,  mediaType = "book",  title = "The Hitchhiker's Guide to the Galaxy", pageCount = 379,
            author = "Douglas Adams", publishedYear = 1979,
            averageRating = 4.7f, ratingCount = 312,
            genres = listOf("Science Fiction", "Comedy")),
        Media(id = 2,  mediaType = "book",  title = "Project Hail Mary",
            author = "Andy Weir", publishedYear = 2021,
            averageRating = 4.9f, ratingCount = 478,
            genres = listOf("Science Fiction", "Adventure")),
        Media(id = 3,  mediaType = "book",  title = "The Name of the Wind",
            author = "Patrick Rothfuss", publishedYear = 2007,
            averageRating = 4.6f, ratingCount = 291,
            genres = listOf("Fantasy", "Adventure")),
        Media(id = 4,  mediaType = "book",  title = "Dune",
            author = "Frank Herbert", publishedYear = 1965,
            averageRating = 4.8f, ratingCount = 521,
            genres = listOf("Science Fiction", "Epic")),
        Media(id = 5,  mediaType = "movie", title = "Arrival",
            director = "Denis Villeneuve", publishedYear = 2016,
            averageRating = 4.5f, ratingCount = 263,
            genres = listOf("Science Fiction", "Drama")),
        Media(id = 6,  mediaType = "movie", title = "Everything Everywhere All at Once",
            director = "Daniel Kwan, Daniel Scheinert", publishedYear = 2022,
            averageRating = 4.8f, ratingCount = 389,
            genres = listOf("Science Fiction", "Comedy", "Drama")),
        Media(id = 7,  mediaType = "movie", title = "Interstellar",
            director = "Christopher Nolan", publishedYear = 2014,
            averageRating = 4.6f, ratingCount = 441,
            genres = listOf("Science Fiction", "Adventure")),
        Media(id = 8,  mediaType = "show",  title = "Severance",
            creator = "Dan Erickson", network = "Apple TV+", publishedYear = 2022,
            averageRating = 4.9f, ratingCount = 317,
            genres = listOf("Thriller", "Science Fiction", "Drama")),
        Media(id = 9,  mediaType = "show",  title = "The Bear",
            creator = "Christopher Storer", network = "FX on Hulu", publishedYear = 2022,
            averageRating = 4.8f, ratingCount = 298,
            genres = listOf("Drama", "Comedy")),
        Media(id = 10, mediaType = "show",  title = "Andor",
            creator = "Tony Gilroy", network = "Disney+", publishedYear = 2022,
            averageRating = 4.7f, ratingCount = 276,
            genres = listOf("Science Fiction", "Drama", "Action")),
    )

    val libraryItems = listOf(
        LibraryItem("user-001", 1, LibraryStatus.FINISHED,
            "2024-01-10T10:00:00Z", "2024-01-15T10:00:00Z", mediaList[0]),
        LibraryItem("user-001", 5, LibraryStatus.IN_PROGRESS,
            "2024-01-18T10:00:00Z", "2024-01-18T10:00:00Z", mediaList[4]),
        LibraryItem("user-001", 8, LibraryStatus.WANT_TO,
            "2024-01-20T10:00:00Z", "2024-01-20T10:00:00Z", mediaList[7]),
    )

    val reviewList = listOf(
        Review(
            userId = "user_001",
            mediaId = 1,
            rating = 5,
            reviewText = "An absolute classic. Funny, clever, and endlessly quotable.",
            createdAt = "2024-01-12T14:23:00Z"
        ),
        Review(
            userId = "user_002",
            mediaId = 1,
            rating = 4,
            reviewText = "Loved the humor, but some parts dragged a bit.",
            createdAt = "2024-02-03T09:11:00Z"
        ),
        Review(
            userId = "user_003",
            mediaId = 2,
            rating = 5,
            reviewText = "Project Hail Mary blew me away. Couldn’t put it down.",
            createdAt = "2024-03-18T18:45:00Z"
        ),
        Review(
            userId = "user_004",
            mediaId = 3,
            rating = 5,
            reviewText = "One of the best fantasy books ever written.",
            createdAt = "2024-04-01T12:00:00Z"
        ),
        Review(
            userId = "user_005",
            mediaId = 4,
            rating = 4,
            reviewText = "Epic worldbuilding. Slow start, incredible payoff.",
            createdAt = "2024-04-22T16:30:00Z"
        ),
        Review(
            userId = "user_006",
            mediaId = 5,
            rating = 5,
            reviewText = "Arrival is a masterpiece. Emotional and thought‑provoking.",
            createdAt = "2024-05-10T20:15:00Z"
        ),
        Review(
            userId = "user_007",
            mediaId = 6,
            rating = 5,
            reviewText = "Wild, chaotic, and brilliant. EEAAO deserves every award.",
            createdAt = "2024-06-02T11:05:00Z"
        ),
        Review(
            userId = "user_008",
            mediaId = 7,
            rating = 5,
            reviewText = "Interstellar is visually stunning and emotionally powerful.",
            createdAt = "2024-06-15T22:40:00Z"
        ),
        Review(
            userId = "user_009",
            mediaId = 8,
            rating = 5,
            reviewText = "Severance is one of the best shows in years. Perfect tone.",
            createdAt = "2024-07-01T08:55:00Z"
        ),
        Review(
            userId = "user_010",
            mediaId = 9,
            rating = 4,
            reviewText = "The Bear is intense and beautifully acted.",
            createdAt = "2024-07-12T13:20:00Z"
        ),
        Review(
            userId = "user_011",
            mediaId = 10,
            rating = 5,
            reviewText = "Andor is peak Star Wars. Mature, grounded, and gripping.",
            createdAt = "2024-07-20T17:10:00Z"
        )
    )


    private val userJordan = UserProfile("user-002", "j@example.com", "jsmith",   "Jordan Smith",  followerCount = 5,  followingCount = 10)
    private val userPriya  = UserProfile("user-003", "p@example.com", "priya_r", "Priya Patel",   followerCount = 23, followingCount = 15)
    private val userMarco  = UserProfile("user-004", "m@example.com", "mramos",  "Marco Ramos",   followerCount = 8,  followingCount = 4)
    private val userSarah  = UserProfile("user-005", "s@example.com", "sarahk",  "Sarah Kim",     followerCount = 31, followingCount = 22)

    val activityFeed = listOf(
        ActivityEvent(1, "user-002", "finished", 5, createdAt = "2024-01-22T14:30:00Z",
            user = userJordan, media = mediaList[4]),
        ActivityEvent(2, "user-003", "review",   8, rating = 5,
            reviewText = "Absolutely gripping from start to finish.",
            createdAt = "2024-01-22T11:15:00Z", user = userPriya, media = mediaList[7]),
        ActivityEvent(3, "user-004", "added",    10, createdAt = "2024-01-21T20:00:00Z",
            user = userMarco, media = mediaList[9]),
        ActivityEvent(4, "user-002", "started",  9, createdAt = "2024-01-21T18:45:00Z",
            user = userJordan, media = mediaList[8]),
        ActivityEvent(5, "user-003", "review",   1, rating = 4,
            createdAt = "2024-01-20T09:00:00Z", user = userPriya, media = mediaList[0]),
    )

    val followers = listOf(userJordan, userPriya)
    val following = listOf(userMarco, userSarah)
}
