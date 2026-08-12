package edu.metrostate.ics342.mediatracker.ui.library

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.metrostate.ics342.mediatracker.R.string
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.lazy.items



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrioritiesScreen(
    onMediaClick: (Int) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: PrioritiesViewModel = viewModel()
) {
    val priorities = viewModel.priorityItems.collectAsState().value

    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = {
                Text(stringResource(string.priorities))
            },
            navigationIcon = {
                IconButton(onClick = { onNavigateBack }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            actions = {
                IconButton(onClick = { /* open settings */ }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Settings"
                    )
                }
            }
        )


        PriorityFilterChips(
            selectedType = "all",
            onTypeSelect = viewModel::onTypeSelect,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(priorities, key = { it.mediaId }) { priority ->
                PriorityItemCard(
                    item = priority.media,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    dragHandle = Modifier   // unused for now
                )
            }
        }

    }

}