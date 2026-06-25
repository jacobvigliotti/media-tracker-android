package edu.metrostate.ics342.mediatracker.ui.search

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.metrostate.ics342.mediatracker.ui.library.LibraryViewModel

// ── STUB — Students build this in Week 5 ─────────────────────────────────────
//
// Week 5 task: Build the Search screen.
//   1. Add a search bar (SearchBar or OutlinedTextField) at the top.
//   2. Add FilterChips for All / Books / Movies / Shows in a horizontally scrollable Row.
//   3. Display results in a LazyColumn (you'll learn why Column won't work here).
//   4. Wire to GET /media?query=...&type=...
//   5. Handle loading, empty, and error states.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onMediaClick: (Int) -> Unit,
    viewModel: SearchViewModel = viewModel()
) {
    var expanded = false;
    var placeholder = "Search here";
    val query by viewModel.query.collectAsState()
    var selectedType by rememberSaveable() { mutableStateOf("all") }


    Column(modifier = Modifier.fillMaxSize()) {
        //Title Bar - Search
        TopAppBar(title = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.search_title)) })

        //Search Bar
        SearchBar(
            inputField = {
                TextField(
                    value = query,
                    onValueChange = {
                        query
                    },
                    placeholder = { Text(placeholder, color = MaterialTheme.colorScheme.onSurface) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon"
                        )
                    },
                    trailingIcon = {
                        if (query.isNotEmpty()) {
                            IconButton(
                                onClick = {
                                    expanded = false
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear search"
                                )
                            }
                        }
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it },
            windowInsets = SearchBarDefaults.windowInsets,
            modifier = Modifier.fillMaxWidth(),
            content = {
                LazyColumn() { }
            }
        )

        //Filters - all, books, movies, shows
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .horizontalScroll(state = rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf(
                "all" to edu.metrostate.ics342.mediatracker.R.string.filter_all,
                "book" to edu.metrostate.ics342.mediatracker.R.string.filter_books,
                "movie" to edu.metrostate.ics342.mediatracker.R.string.filter_movies,
                "show" to edu.metrostate.ics342.mediatracker.R.string.filter_shows
            )
                .forEach { (key, labelRes) ->
                    FilterChip(
                        selected = selectedType == key,
                        onClick = { selectedType = key },
                        label = { Text(stringResource(labelRes)) }
                    )
                }
        }
    }
}



