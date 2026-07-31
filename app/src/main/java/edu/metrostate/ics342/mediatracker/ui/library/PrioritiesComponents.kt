package edu.metrostate.ics342.mediatracker.ui.library

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import edu.metrostate.ics342.mediatracker.R



    @Composable
    fun PriorityFilterChips(
        selectedType: String,
        onTypeSelect: (String) -> Unit,
        modifier: Modifier = Modifier
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


