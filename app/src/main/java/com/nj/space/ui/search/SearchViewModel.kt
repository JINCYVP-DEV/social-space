package com.nj.space.ui.search

import androidx.lifecycle.ViewModel
import com.nj.space.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor()  : ViewModel() {

    val uiState : StateFlow<UiState> = MutableStateFlow(UiState.Nothing)
        get() = field

    private fun updateUiState(state: UiState)
    {
        (uiState as MutableStateFlow).value= state
    }

}
sealed class SearchNavigationEvent{
    object NavigateBack:SearchNavigationEvent()
}