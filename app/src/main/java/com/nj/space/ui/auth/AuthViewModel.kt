package com.nj.space.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nj.space.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
)  : ViewModel(){

    // Single-event channel
    private val _authEvents = MutableSharedFlow<AuthEvent>()
    val authEvents = _authEvents.asSharedFlow()


    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()
    fun login(email: String, password: String)
    {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
           val  result= authRepository.login(email = email, password = password)
            _uiState.value= if(result.isSuccess)
            {
                AuthUiState.Success
            }
            else
                AuthUiState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
        }

    }
    fun signUp(email: String, password: String, username: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            val result = authRepository.signUp(email=email, password = password, userName =  username)
            _uiState.value = if (result.isSuccess) {
                AuthUiState.Success
            } else {
                AuthUiState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }
    fun validateEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    }
    fun logout() = viewModelScope.launch {
        authRepository.logout()
        _authEvents.emit(AuthEvent.LoggedOut)
    }
    sealed class AuthEvent {
        object LoggedOut : AuthEvent()
    }
}