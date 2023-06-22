package com.vc.ohmybank.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vc.ohmybank.domain.User
import com.vc.ohmybank.repository.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ContactListViewModel @Inject constructor(
    private val userListRepository: UsersRepository
) : ViewModel() {

    private var _uiState: StateFlow<ContactListScreenState>
    val uiState
        get() = _uiState

    init {
        _uiState = getUsersFromRepository().stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = ContactListScreenState.Loading
        )
    }

    private fun getUsersFromRepository(): Flow<ContactListScreenState> =
        userListRepository.observeUserList
            .map {
                val activeUsers = it.filter { user -> user.status == "active" }
                val avatarStringsList = getAvatarStringForUsers(activeUsers)

                ContactListScreenState.Success(activeUsers, avatarStringsList)
            }

    private fun getAvatarStringForUsers(users: List<User>): List<String> = users.map {
        if ((it.id).toInt() % 12 == 0)
            it.name.getInitials()
        else {
            Random.nextInt(from = 0, until = 1084).toString()
        }
    }

    private fun String.getInitials(): String = this.split(" ").let {
        "${it[0][0]}${(it[1][0])}"
    }
}

sealed interface ContactListScreenState {
    object Loading : ContactListScreenState
    data class Success(
        val usersList: List<User>,
        val avatarStringsList: List<String>
    ) : ContactListScreenState
}