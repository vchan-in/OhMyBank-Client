package com.vc.ohmybank.ui.viewmodel

import com.vc.ohmybank.domain.Activity

sealed interface UserActivityScreenState {
    object Loading : UserActivityScreenState
    data class Success(val activityList: List<Activity>) : UserActivityScreenState
}