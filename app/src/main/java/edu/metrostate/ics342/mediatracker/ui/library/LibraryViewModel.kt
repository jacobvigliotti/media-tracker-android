package edu.metrostate.ics342.mediatracker.ui.library

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.metrostate.ics342.mediatracker.data.FakeMediaRepository
import edu.metrostate.ics342.mediatracker.data.datastore.DefaultSessionRepository
import edu.metrostate.ics342.mediatracker.data.model.LibraryItem
import edu.metrostate.ics342.mediatracker.data.model.LibraryStatus
import edu.metrostate.ics342.mediatracker.data.network.DefaultMediaRepository
import edu.metrostate.ics342.mediatracker.data.network.UpdatePriorityOrderRequest
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class LibraryViewModel(application: Application) : AndroidViewModel(application) {

    private val mediaRepository = DefaultMediaRepository(DefaultSessionRepository(application))


    private val _libraryItems = MutableStateFlow<List<LibraryItem>>(emptyList())
    val libraryItems: StateFlow<List<LibraryItem>> = _libraryItems.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _filterState = MutableStateFlow(LibraryStatus.WANT_TO)
    val filterState: StateFlow<LibraryStatus> = _filterState.asStateFlow()



    init {
        loadLibrary()
    }

    fun loadLibrary() {
        viewModelScope.launch {
            _isLoading.value = true
            _libraryItems.value = mediaRepository.getLibrary("want_to")
            _isLoading.value = false
        }
    }

    fun removeItem(mediaId: Int) {
        _libraryItems.value = _libraryItems.value.filter { it.mediaId != mediaId }
    }

    fun updateStatus(mediaId: Int, newStatus: LibraryStatus) {
        _libraryItems.value = _libraryItems.value.map { item ->
            if (item.mediaId == mediaId) item.copy(status = newStatus) else item
        }
    }

    fun updateFilter(status: LibraryStatus) {
        _filterState.value = status
    }

    fun prioritizeItem(mediaId: Int){
        viewModelScope.launch {
            _isLoading.value = true
            mediaRepository.updatePriorityOrder(UpdatePriorityOrderRequest(mediaId))
            _isLoading.value = false
        }
    }
}
