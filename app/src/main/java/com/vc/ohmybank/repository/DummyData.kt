package com.vc.ohmybank.repository

import com.vc.ohmybank.domain.Activity
import com.vc.ohmybank.domain.User

object DummyData {
    val userList: List<User> = listOf(
        User(
            id = 0,
            name = "Data Error",
            email = "error@error.error",
            status = "active"
        )
    )

    private val mockedUserActivity1 = Activity(
        id = 15,
        title = "Data Error",
        description = "Unable to connect to the API server."
    )


    fun getDummyPostList() = listOf(
        mockedUserActivity1,
    )
}