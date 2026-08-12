package edu.metrostate.ics342.mediatracker.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import edu.metrostate.ics342.mediatracker.data.Exceptions
import edu.metrostate.ics342.mediatracker.data.MediaResult
import edu.metrostate.ics342.mediatracker.data.datastore.DefaultSessionRepository
import edu.metrostate.ics342.mediatracker.data.model.Media
import edu.metrostate.ics342.mediatracker.data.network.DefaultMediaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MediaDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val mediaRepository =
        DefaultMediaRepository(DefaultSessionRepository(application))

    private val _mediaId = MutableStateFlow(-1)
    val mediaId: StateFlow<Int> = _mediaId.asStateFlow()

    private val _media = MutableStateFlow<Media?>(null)
    val media: StateFlow<Media?> = _media.asStateFlow()

    private val _uiMessage = MutableStateFlow<String?>(null)
    val uiMessage = _uiMessage

    private val _isInLibrary = MutableStateFlow<Boolean>(false)
    val isInLibrary = _isInLibrary


    fun setMediaId(id: Int) {
        _mediaId.value = id
        loadMedia(id)
        isInLibrary(id)

    }

    private fun loadMedia(id: Int) {
        viewModelScope.launch {
            when (val result = mediaRepository.get(id)) {
                is MediaResult.Success -> _media.value = result.media
                is MediaResult.Error   -> _media.value = null
            }
        }
    }

    fun addToLibrary(id: Int) {
        viewModelScope.launch {
            try {
                mediaRepository.addToLibrary(id)
            } catch (e: Exceptions.AlreadyInLibraryException) {
                _uiMessage.value = "Media Already in Library"
            } catch (e: Exception) {
                _uiMessage.value = "Something went wrong"
            }
        }
        isInLibrary.value = true
    }

    fun isInLibrary(id: Int){
        viewModelScope.launch {
            try {
                isInLibrary.value = mediaRepository.isInLibrary(id)
            }
            catch (e : Exception){
                isInLibrary.value = false;
            }
        }
    }

}

