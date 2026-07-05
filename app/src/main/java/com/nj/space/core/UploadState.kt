package com.nj.space.core

sealed class UploadState {
    object Idle : UploadState()
    data class Loading(val progress: Int) : UploadState()
    data class Success(val url: String) : UploadState()
    data class Error(val message: String) : UploadState()
}