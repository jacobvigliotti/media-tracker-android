package edu.metrostate.ics342.mediatracker.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PriorityItem(
    val id: Int,
    val priority: Int,
    val orderIndex: Int,
    val estimatedTimeHours: Int?,
    val notes: String?,
    val media: Media
)