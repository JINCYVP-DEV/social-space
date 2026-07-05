package com.nj.space.ui.add

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nj.space.core.UploadState
import com.nj.space.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReelsViewModel @Inject constructor(val postRepository: PostRepository)  : ViewModel() {

    val uiState : StateFlow<UploadState> = MutableStateFlow(UploadState.Idle)
        get() = field

    fun uploadPhoto(uri: Uri, userId: String) {
        viewModelScope.launch {
            updateUiState( UploadState.Loading(0))
            postRepository.uploadPost(uri=uri, userId =  userId, caption = "test") { progress ->
                updateUiState( UploadState.Loading(progress))
            }.onSuccess { url ->
                updateUiState( UploadState.Success(url))
            }.onFailure { error ->
                updateUiState(UploadState.Error(error.message ?: "Upload failed"))
            }
        }
    }

    private fun updateUiState(state: UploadState)
    {
        (uiState as MutableStateFlow).value= state
    }
}
sealed class AddPostNavigationEvent{
    object NavigateBack:AddPostNavigationEvent()
}