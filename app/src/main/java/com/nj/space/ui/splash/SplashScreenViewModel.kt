package com.nj.space.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nj.space.data.preferences.SessionManager
import com.nj.space.ui.auth.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    sessionManager: SessionManager
)
    : ViewModel() {
    val authState: StateFlow<AuthState> = sessionManager.isLoggedInFlow
        .map { if (it) AuthState.LOGGED_IN else AuthState.LOGGED_OUT }
        .stateIn(viewModelScope, SharingStarted.Eagerly, AuthState.LOADING)
}