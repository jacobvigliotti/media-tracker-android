package edu.metrostate.ics342.mediatracker.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.google.android.material.loadingindicator.LoadingIndicator
import edu.metrostate.ics342.mediatracker.data.FakeMediaRepository.activityFeed
import edu.metrostate.ics342.mediatracker.data.FakeMediaRepository.mediaList
import edu.metrostate.ics342.mediatracker.data.FakeMediaRepository.reviewList
import edu.metrostate.ics342.mediatracker.data.model.Media
import edu.metrostate.ics342.mediatracker.data.model.Review
import edu.metrostate.ics342.mediatracker.theme.Primary
import edu.metrostate.ics342.mediatracker.theme.PrimaryContainer
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import edu.metrostate.ics342.mediatracker.R as R

// ── STUB — Students build this in Week 7 ─────────────────────────────────────
//
// Week 7 task: Build the Media Detail screen.
//   1. Receive mediaId from the navigation argument (typed Int — see NavGraph).
//   2. Call GET /media/{mediaId} to load full details.
//   3. Display: cover image, title, creator credit, metadata grid, genre chips,
//      average rating, description, and a library status control.
//   4. Display the reviews list from GET /reviews?mediaId={id}.
//   5. Handle loading and error states (full-screen — no half-built screens).
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaDetailScreen(
    mediaId: Int,
    onNavigateBack: () -> Unit,
    onWriteReview: (Int) -> Unit,
    viewModel: MediaDetailViewModel = viewModel()
) {

    LaunchedEffect(mediaId) {
        viewModel.setMediaId(mediaId)
    }
    val media = viewModel.media.collectAsState().value

    if (media == null) {
        Text("No Media")
        return
    }
    val reviews = reviewList




    TopAppBar(
        modifier = Modifier.fillMaxWidth(), // Forces full horizontal width
        navigationIcon = {
            IconButton(onClick = { onNavigateBack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = "Options"
                )
            }
        },
        title = { Text("") }
    )

    // Page Column
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        //Cover Art
        Box(
            modifier = Modifier.size(width = 110.dp, height = 160.dp)
                .clip(RoundedCornerShape(6.dp)),
            contentAlignment = Alignment.Center
        ) {
            if (media.coverUrl != null) {
                AsyncImage(
                    model = media.coverUrl,
                    contentDescription = media.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            when (media.mediaType) {
                                "book" -> "📖"; "movie" -> "🎬"; "show" -> "📺"
                                else -> "?"
                            }, style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            }
        }
        Spacer(Modifier.height(8.dp))


        Text(
            media.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(8.dp))

        val creatorName = when (media.mediaType) {
            "book"  -> media.author
            "movie" -> media.director
            "show"  -> media.creator
            else    -> null
        } ?: "?"

        Text(
            creatorName,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(8.dp))

        // Star Rating Row
        Row {
            Spacer(Modifier.height(4.dp))
            Text(
                "★".repeat(media.averageRating.toInt()) + "☆".repeat(5 - media.averageRating.toInt()),
                color = MaterialTheme.colorScheme.secondary
            )
        }

        Spacer(Modifier.height(8.dp))


        // Want To and Save Button Row
        Row() {
            Button(
                onClick = { /* do something */ },
                enabled = true,
                modifier = Modifier.weight(1f).height(48.dp)
            ) {
                Text(stringResource(R.string.media_detail_want_to))
            }
            Spacer(Modifier.width(8.dp))
            Button(
                onClick = { /* do something */ },
                enabled = true,
                modifier = Modifier.weight(1f).height(48.dp)
            ) {
                Text(stringResource(R.string.media_detail_save))
            }
        }
        Spacer(Modifier.height(20.dp))

        //About column
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(R.string.media_detail_about).uppercase(),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(8.dp)) // Note: Use height here instead of width
            media.description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(Modifier.height(20.dp))

            //About Row - Year, Count, Genre
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.0.dp),
                horizontalArrangement = Arrangement.SpaceEvenly // Spaces the boxes evenly
            ) {
                // Box 1
                InfoBox(data = media.publishedYear.toString(), title = "Year", modifier = Modifier.weight(1f))

                Spacer(Modifier.width(8.dp)) // Small gap between boxes

                // Box 2
                InfoBox(
                    data = when (media.mediaType) {
                        "book" -> media.pageCount?.toString() ?: "—"
                        "show" -> media.episodeCount?.toString() ?: "—"
                        "movie" -> media.runtimeMinutes?.toString() ?: "—"
                        else -> {"-"}
                    },
                    title = when (media.mediaType) {
                        "book" -> "Pages"
                        "show" -> "Episodes"
                        "movie" -> "Runtime"
                        else -> {"-"}
                    },
                    modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.width(8.dp)) // Small gap between boxes

                // Box 3
                InfoBox(
                    data = media.genres.firstOrNull() ?: "—",
                    title = "Genre",
                    modifier = Modifier.weight(1f)
                )            }
            // End About Row/Boxes

            // Start Review Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween, // Pushes one to the left, one to the right
                verticalAlignment = Alignment.CenterVertically // Aligns them perfectly on the same baseline
            ) {
                // Left side: Standard header text
                Text(
                    text = stringResource(R.string.media_detail_reviews),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )

                // Right side: Clickable action text
                Text(
                    text = stringResource(R.string.media_detail_write_review),
                    color = MaterialTheme.colorScheme.primary, // Makes it look like a link
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable { /*TODO*/ }
                )
            }
            Column(modifier = Modifier.fillMaxWidth()) {
                reviews.forEach { review ->
                    ReviewCard(
                        review = review,
                        modifier = Modifier.padding(vertical = 6.dp)
                    )
                }
            }

        }
        }
        }

@Composable
fun InfoBox(data: String, title: String, modifier: Modifier = Modifier) {
    // Explicit use of the Box composable
    Box(
        modifier = modifier
            .height(70.dp) // Giving the box a fixed height to show off vertical centering
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFF2F2F7)) // Grey background
            .padding(8.dp),
        contentAlignment = Alignment.Center // Centers everything inside the Box perfectly
    ) {
        // Stacked column inside the Box to keep text from overlapping
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = data,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )

        }
    }
}

@Composable
fun ReviewCard(
    review: Review,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier  = modifier.fillMaxWidth().clickable { onClick() },
        shape     = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {

        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.Top
        ) {

            // ───────────────────────────────
            // Column 1: Profile Image / Initial
            // ───────────────────────────────
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                val initial = review.userId.firstOrNull()?.uppercase() ?: "?"
                Text(
                    text = initial,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // ───────────────────────────────
            // Column 2: Username + Stars + Review Text
            // ───────────────────────────────
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Username / handle
                Text(
                    text = "@${review.userId}",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Star rating
                Row(verticalAlignment = Alignment.CenterVertically) {
                    repeat(review.rating) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color(0xFFFFD700),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    repeat(5 - review.rating) {
                        Icon(
                            imageVector = Icons.Default.StarBorder,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Review text
                review.reviewText?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            // ───────────────────────────────
            // Column 3: "xd ago"
            // ───────────────────────────────
            Text(
                text = timeAgo(review.createdAt),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

fun timeAgo(timestamp: String): String {
    val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val created = OffsetDateTime.parse(timestamp, formatter)
    val now = OffsetDateTime.now()

    val days = ChronoUnit.DAYS.between(created, now)
    val hours = ChronoUnit.HOURS.between(created, now)
    val minutes = ChronoUnit.MINUTES.between(created, now)

    return when {
        days > 0 -> "${days}d ago"
        hours > 0 -> "${hours}h ago"
        minutes > 0 -> "${minutes}m ago"
        else -> "just now"
    }
}





