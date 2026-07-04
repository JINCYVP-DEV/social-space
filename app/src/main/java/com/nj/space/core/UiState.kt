package com.nj.space.core


sealed class UiState {
    object Nothing : UiState()
    object Loading : UiState()
    object Success : UiState()
    data class Error(val message: String) : UiState()
}