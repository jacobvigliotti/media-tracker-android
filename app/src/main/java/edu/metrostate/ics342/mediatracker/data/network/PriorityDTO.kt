package edu.metrostate.ics342.mediatracker.data.network

import edu.metrostate.ics342.mediatracker.data.model.Media
import edu.metrostate.ics342.mediatracker.data.model.PriorityItem
import kotlinx.serialization.Serializable
import kotlin.Int
import kotlin.String

@Serializable
data class PriorityResponse(
    val priorities: List<PriorityItem>
)

@Serializable
data class UpdatePriorityOrderRequest(
    val mediaId: Int?,
    val priority: Int? = null,
    val orderIndex: Int? = null,
    val estimatedTimeHours: Int? = null,
    val notes: String? = null
)

@Serializable
data class UpdatePriorityOrderResponse(
    val mediaId: Int,
    val priority: Int,
    val orderIndex: Int,
    val estimatedTimeHours: Int?,
    val notes: String?,
    val media: Media
)