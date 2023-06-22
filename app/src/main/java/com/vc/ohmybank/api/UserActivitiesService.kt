package com.vc.ohmybank.api

import com.vc.ohmybank.domain.Activity
import retrofit2.http.GET
import retrofit2.http.Path

interface UserActivitiesService {
    @GET("users/{userId}/activities")
    suspend fun getUserPosts(@Path("userId") userId: Long): List<Activity>
}