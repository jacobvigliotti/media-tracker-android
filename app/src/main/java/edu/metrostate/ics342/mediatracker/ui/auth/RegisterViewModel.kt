package edu.metrostate.ics342.mediatracker.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.metrostate.ics342.mediatracker.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import edu.metrostate.ics342.mediatracker.data.UserRepository

class RegisterViewModel(
    ) : ViewModel() {

    private val userRepository: UserRepository = UserRepository()

    // ── Login ─────────────────────────────────────────────────────────────

    private val _displayName    = MutableStateFlow("")
    val displayName: StateFlow<String> = _displayName.asStateFlow()

    private val _username    = MutableStateFlow("")
    val username: StateFlow<String> = _username.asStateFlow()
    private val _email    = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword.asStateFlow()


    fun onDisplayNameChange(value: String)    { _displayName.value    = value }
    fun onUsernameChange(value: String)    { _username.value    = value }
    fun onEmailChange(value: String)    { _email.value    = value }
    fun onPasswordChange(value: String) { _password.value = value }
    fun onConfirmPasswordChange(value: String) { _confirmPassword.value = value }



    fun onSignUpClicked() {
        viewModelScope.launch {
                userRepository.createAccount(displayName.value, username.value, email.value, password.value)
            }
        }
    }

