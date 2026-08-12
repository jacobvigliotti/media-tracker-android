package edu.metrostate.ics342.mediatracker.ui.library

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import edu.metrostate.ics342.mediatracker.data.model.LibraryItem
import edu.metrostate.ics342.mediatracker.data.model.LibraryStatus
import edu.metrostate.ics342.mediatracker.data.model.creatorCredit
import edu.metrostate.ics342.mediatracker.R.string
import edu.metrostate.ics342.mediatracker.theme.OnPrimaryContainer
import edu.metrostate.ics342.mediatracker.theme.PrimaryContainer
import edu.metrostate.ics342.mediatracker.theme.Surface
import edu.metrostate.ics342.mediatracker.theme.SurfaceVariant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(
    onMediaClick: (Int) -> Unit,
    onPrioritiesClick: () -> Unit,
    viewModel: LibraryViewModel = viewModel()
) {
    val items     by viewModel.libraryItems.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val selectedStatus by viewModel.filterState.collectAsState()

    var expanded by remember { mutableStateOf(false) }



    //var selectedStatus by rememberSaveable() { mutableStateOf(LibraryStatus.WANT_TO) }
    var selectedType   by rememberSaveable() { mutableStateOf("all") }

    Column(modifier = Modifier.fillMaxSize().background(SurfaceVariant)) {
        TopAppBar(
            title = { Text(stringResource(string.library_title)) },
            actions = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu"
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(stringResource(string.priorities)) },
                        onClick = { onPrioritiesClick() }
                    )
                    DropdownMenuItem(
                        text = { Text("Option 2") },
                        onClick = { /* Do something... */ }
                    )
                }
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .horizontalScroll(state = rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf(
                "all"   to edu.metrostate.ics342.mediatracker.R.string.filter_all,
                "book"  to edu.metrostate.ics342.mediatracker.R.string.filter_books,
                "movie" to edu.metrostate.ics342.mediatracker.R.string.filter_movies,
                "show"  to edu.metrostate.ics342.mediatracker.R.string.filter_shows
            )
                .forEach { (key, labelRes) ->
                    FilterChip(
                        selected = selectedType == key,
                        onClick  = { selectedType = key },
                        label    = { Text(stringResource(labelRes)) }
                    )
                }
        }

        SingleChoiceSegmentedButtonRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            LibraryStatus.values().forEachIndexed { index, status ->
                SegmentedButton(
                    shape    = SegmentedButtonDefaults.itemShape(
                        index = index, count = LibraryStatus.values().size),
                    selected = selectedStatus == status,
                    onClick  = { viewModel.updateFilter(status) },
                    label    = {
                        Text(
                            text = stringResource(status.labelRes),
                            fontWeight = FontWeight.Bold
                        )
                    },
                    colors  = SegmentedButtonDefaults.colors(
                        activeContainerColor = PrimaryContainer,
                        activeContentColor = OnPrimaryContainer
                    )
                )
            }
        }

        HorizontalDivider(modifier = Modifier.padding(top = 8.dp))

        if (isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            return@Column
        }

        val filteredItems = items
            .filter { it.status == selectedStatus }
            .filter { selectedType == "all" || it.media.mediaType == selectedType }

        if (filteredItems.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    stringResource(edu.metrostate.ics342.mediatracker.R.string.library_empty),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
            return@Column
        }

        Text(
            if (filteredItems.size == 1) stringResource(edu.metrostate.ics342.mediatracker.R.string.library_item_count, filteredItems.size)
            else stringResource(edu.metrostate.ics342.mediatracker.R.string.library_items_count, filteredItems.size),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            style    = MaterialTheme.typography.labelMedium,
            color    = MaterialTheme.colorScheme.onSurfaceVariant
        )

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filteredItems, key = { it.mediaId }) { item ->
                LibraryItemCard(
                    item           = item,
                    onClick        = { onMediaClick(item.mediaId) },
                    onRemove       = { viewModel.removeItem(item.mediaId) },
                    onPrioritize = { viewModel.prioritizeItem(item.mediaId)},
                    onStatusChange = { newStatus -> viewModel.updateStatus(item.mediaId, newStatus)
                    }
                )
            }
        }
    }
}


