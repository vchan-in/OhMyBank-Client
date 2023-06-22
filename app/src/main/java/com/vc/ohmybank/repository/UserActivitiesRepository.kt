package com.vc.ohmybank.repository

import com.vc.ohmybank.domain.Activity
import kotlinx.coroutines.flow.Flow

interface UserActivitiesRepository {
    fun getUserPosts(userId: Long): Flow<List<Activity>>
}