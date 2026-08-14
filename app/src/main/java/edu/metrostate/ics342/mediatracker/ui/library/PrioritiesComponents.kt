package edu.metrostate.ics342.mediatracker.ui.library

import android.content.ClipData
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DragHandle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import edu.metrostate.ics342.mediatracker.R
import edu.metrostate.ics342.mediatracker.data.model.Media


@Composable
    fun PriorityFilterChips(
        modifier: Modifier = Modifier,
        selectedType: String = "",
        onTypeSelect: (String) -> Unit = {}
    ) {
        val types = listOf(
            "" to R.string.priority_chip_all,
            "high" to R.string.priority_chip_high,
            "medium" to R.string.priority_chip_medium,
            "low" to R.string.priority_chip_low,
        )

        Row(
            modifier = modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            types.forEach { (type, labelRes) ->
                FilterChip(
                    selected = selectedType == type,
                    onClick = { onTypeSelect(type) },
                    label = { Text(stringResource(labelRes)) }
                )
            }
        }
    }

@Composable
fun PriorityItemCard(
    item: Media,
    modifier: Modifier = Modifier,
    dragHandle: Modifier = Modifier
) {
    Card(
        modifier  = modifier.fillMaxWidth(),
        shape     = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Drag handle
            Icon(
                imageVector = Icons.Default.DragHandle,
                contentDescription = "Drag to reorder",
                modifier = Modifier.dragAndDropSource{ _ ->
                    DragAndDropTransferData(
                        ClipData.newPlainText(item.id.toString(), item.id.toString())
                    )
                }
            )

            // Cover image
            Box(
                modifier = Modifier
                    .size(64.dp, 90.dp)
                    .clip(RoundedCornerShape(6.dp)),
                contentAlignment = Alignment.Center
            ) {
                if (item.coverUrl != null) {
                    AsyncImage(
                        model             = item.coverUrl,
                        contentDescription = item.title,
                        contentScale      = ContentScale.Crop,
                        modifier          = Modifier.fillMaxSize()
                    )
                } else {
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                when (item.mediaType) {
                                    "book" -> "📖"
                                    "movie" -> "🎬"
                                    "show" -> "📺"
                                    else -> "?"
                                },
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    item.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2
                )
                Spacer(Modifier.height(2.dp))
                //priority pill
                Pill("High Priority")
                //notes
                Spacer(Modifier.height(6.dp))
            }
        }
    }
}

