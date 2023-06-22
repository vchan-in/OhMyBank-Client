package com.vc.ohmybank.api

import com.vc.ohmybank.domain.User
import retrofit2.http.GET

interface UserListService {

    @GET("users")
    suspend fun getUsersList(): List<User>
}