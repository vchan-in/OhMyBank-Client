package com.vc.ohmybank.repository

import android.util.Log
import com.vc.ohmybank.api.UserActivitiesService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class UserActivitiesRepositoryImpl(
    private val _userActivitiesService: UserActivitiesService
) : UserActivitiesRepository {

    private val _coroutineScope = CoroutineScope(Job() + Dispatchers.IO)

    private suspend fun observeUserPosts(userId: Long) =
        withContext(_coroutineScope.coroutineContext) {
            runCatching {
                _userActivitiesService.getUserPosts(userId)
            }.onFailure {
                Log.e("UserPostsRepository === getUserPosts() failure -: ", it.message!!)
                this.cancel()
            }
        }

    override fun getUserPosts(userId: Long) = flow {
        observeUserPosts(userId)
            .onSuccess {
                emit(it)
            }.onFailure {
                //Used dummy data as the api seems to be down from time to time
                emit(DummyData.getDummyPostList())
            }
    }
}