package com.nj.space.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nj.space.data.local.PostEntity
import com.nj.space.data.preferences.SessionManager
import com.nj.space.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val  postRepository: PostRepository,
    sessionManager: SessionManager): ViewModel() {
    private var _feedPost: MutableStateFlow<List<PostEntity>> = MutableStateFlow(emptyList())
    val feedPost : StateFlow<List<PostEntity>> =_feedPost
    val currentUserId =sessionManager.userIdFlow

    init {
        loadInitialList()
    }

    fun loadInitialList(){
        viewModelScope.launch {
           val post= postRepository.loadFeedPosts(refresh = true)
            _feedPost.value=post
        }

    }
    fun likePost(postId: String,userId: String)
    {
        viewModelScope.launch {
            postRepository.likePost(postId =postId, userId = userId )
        }
    }
    fun unlikePost(postId: String,userId: String)
    {
        viewModelScope.launch {
            postRepository.unlikePost(postId = postId, userId = userId)
        }
    }
    sealed class HomeScreenNavigation
    {
        data class NavigatePost(val postId: String): HomeScreenNavigation()
        data class NavigatePostProfile(val userId: String): HomeScreenNavigation()
    }

}