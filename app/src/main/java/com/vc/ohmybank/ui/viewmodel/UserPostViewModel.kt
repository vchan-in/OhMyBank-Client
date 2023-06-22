package com.vc.ohmybank.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vc.ohmybank.repository.UserActivitiesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UserPostViewModel @Inject constructor(
    private val _userActivitiesRepository: UserActivitiesRepository,
) : ViewModel() {

    private var _postListState: StateFlow<UserActivityScreenState> =
        MutableStateFlow(UserActivityScreenState.Loading)
    val postListState
        get() = _postListState

    fun updateUserPosts(userId: Long) {
        _postListState = getUserPosts(userId).stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            UserActivityScreenState.Success(emptyList())
        )
    }

    fun clearUserPostsFlow() {
        _postListState = MutableStateFlow(UserActivityScreenState.Loading)
    }

    private fun getUserPosts(userId: Long) =
        _userActivitiesRepository.getUserPosts(userId).map { UserActivityScreenState.Success(it) }
}