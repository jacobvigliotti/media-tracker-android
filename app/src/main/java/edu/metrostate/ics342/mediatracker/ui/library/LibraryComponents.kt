package edu.metrostate.ics342.mediatracker.ui.library

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DragHandle
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import edu.metrostate.ics342.mediatracker.data.model.LibraryItem
import edu.metrostate.ics342.mediatracker.data.model.LibraryStatus
import edu.metrostate.ics342.mediatracker.data.model.creatorCredit
import edu.metrostate.ics342.mediatracker.R
import edu.metrostate.ics342.mediatracker.data.model.Media
import edu.metrostate.ics342.mediatracker.theme.MediaTrackerTheme
import edu.metrostate.ics342.mediatracker.theme.OnSurfaceVariant
import edu.metrostate.ics342.mediatracker.theme.Surface
import edu.metrostate.ics342.mediatracker.theme.SurfaceVariant

@Composable
    fun LibraryItemCard(
        item: LibraryItem,
        onClick: () -> Unit,
        onRemove: () -> Unit,
        onPrioritize: () -> Unit,
        onStatusChange: (LibraryStatus) -> Unit
    ) {
        var menuExpanded by remember { mutableStateOf(false) }
        var statusDialogVisible by remember { mutableStateOf(false) }
        var showPriorityDialog by remember { mutableStateOf(false) }

        if (statusDialogVisible) {
            AlertDialog(
                onDismissRequest = { statusDialogVisible = false },
                title = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.action_change_status)) },
                text = {
                    Column {
                        LibraryStatus.values().forEach { s ->
                            TextButton(
                                onClick  = { onStatusChange(s); statusDialogVisible = false },
                                modifier = Modifier.fillMaxWidth()
                            ) { Text(stringResource(s.labelRes)) }
                        }
                    }
                },
                confirmButton = {},
                dismissButton = {
                    TextButton(onClick = { statusDialogVisible = false }) { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.settings_cancel_button)) }
                }
            )
        }
    if (showPriorityDialog) {
        AddPriorityDialog(
            onDismiss = {
                showPriorityDialog = false
            },
            onConfirm = { priority, estimatedTimeHours, notes ->
                showPriorityDialog = false
            }
        )
    }

        Card(
            modifier  = Modifier
                .fillMaxWidth()
                .clickable { onClick() },
            shape     = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            colors = CardDefaults.cardColors(
                containerColor = Surface
            )
        ) {
            Row(modifier = Modifier
                .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(64.dp, 90.dp)
                        .clip(RoundedCornerShape(6.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    if (item.media.coverUrl != null) {
                        AsyncImage(
                            model             = item.media.coverUrl,
                            contentDescription = item.media.title,
                            contentScale      = ContentScale.Crop,
                            modifier          = Modifier.fillMaxSize()
                        )
                    } else {
                        Surface(color = MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier.fillMaxSize()) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(when (item.media.mediaType) {
                                    "book" -> "📖"; "movie" -> "🎬"; "show" -> "📺"
                                    else -> "?"
                                }, style = MaterialTheme.typography.titleLarge)
                            }
                        }
                    }
                }

                Spacer(Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(item.media.title, style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold, maxLines = 2)
                    Spacer(Modifier.height(2.dp))
                    Text(item.media.creatorCredit(LocalContext.current),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(Modifier.height(6.dp))
                    SuggestionChip(
                        onClick = { statusDialogVisible = true },
                        label   = { Text(stringResource(item.status.labelRes),
                            style = MaterialTheme.typography.labelSmall) }
                    )
                }

                Box {
                    IconButton(onClick = { menuExpanded = true }) {
                        Icon(Icons.Outlined.MoreVert, stringResource(edu.metrostate.ics342.mediatracker.R.string.action_more_options))
                    }
                    DropdownMenu(
                        expanded         = menuExpanded,
                        onDismissRequest = { menuExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text    = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.action_add_to_priorities)) },
                            onClick = { menuExpanded = false; showPriorityDialog = true/*onPrioritize()*/ }
                        )
                        DropdownMenuItem(
                            text    = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.action_change_status)) },
                            onClick = { menuExpanded = false; statusDialogVisible = true }

                        )
                        DropdownMenuItem(
                            text    = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.action_remove_from_library),
                                color = MaterialTheme.colorScheme.error) },
                            onClick = { menuExpanded = false; onRemove() }
                        )
                    }
                }
            }
        }
    }



@Composable
fun Pill(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = SurfaceVariant,
    contentColor: Color = OnSurfaceVariant
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        shape = RoundedCornerShape(50),
        modifier = modifier
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
fun AddPriorityDialog(
    onDismiss: () -> Unit,
    onConfirm: (
        priority: Int?,
        estimatedTimeHours: Int?,
        notes: String?
    ) -> Unit
) {
    var priority by remember { mutableStateOf("") }
    var estimatedTimeHours by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Add to Priorities")
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                var selectedType by remember { mutableStateOf("")}
                PriorityFilterChips(
                    selectedType = selectedType,
                    onTypeSelect = { newType ->
                        selectedType = newType
                    }
                )

                Column {

                    var estimatedTimeHours by remember { mutableFloatStateOf(0f) }
                    Column {
                        Slider(
                            value = estimatedTimeHours,
                            onValueChange = { estimatedTimeHours = it },
                            valueRange = 0.5f..8.0f,
                            steps = 14
                        )
                        Text(text = "Estimated Time: %.1f".format(estimatedTimeHours) + " hours")
                    }

                }

                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Notes") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm(
                        priority.toIntOrNull(),
                        estimatedTimeHours.toIntOrNull(),
                        notes.ifBlank { null }
                    )
                }
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("Cancel")
            }
        }
    )
}
