package edu.metrostate.ics342.mediatracker.ui.library

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.metrostate.ics342.mediatracker.data.datastore.DefaultSessionRepository
import edu.metrostate.ics342.mediatracker.data.model.LibraryItem
import edu.metrostate.ics342.mediatracker.data.model.PriorityItem
import edu.metrostate.ics342.mediatracker.data.network.DefaultMediaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.collections.emptyList
import org.burnoutcrew.reorderable.*

class PrioritiesViewModel(application: Application) : AndroidViewModel(application) {

    private val mediaRepository = DefaultMediaRepository(DefaultSessionRepository(application))

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    private val _selectedType = MutableStateFlow("")
    val selectedType: StateFlow<String> = _selectedType.asStateFlow()

    private val _priorityItems = MutableStateFlow<List<PriorityItem>>(emptyList())
    val priorityItems: StateFlow<List<PriorityItem>> = _priorityItems.asStateFlow()

    init {
        loadPriorities()
    }

    fun loadPriorities() {
        viewModelScope.launch {
            _isLoading.value = true
            _priorityItems.value = (mediaRepository.getPriorities())
            _isLoading.value = false
        }
    }


    fun onTypeSelect(type: String) { _selectedType.value = type }

}