package com.vc.ohmybank.repository

import com.vc.ohmybank.domain.Activity
import com.vc.ohmybank.domain.User

object DummyData {
    val userList: List<User> = listOf(
        User(
            id = 0,
            name = "Darius Gheorghe",
            email = "darghe98@gmail.com",
            status = "active"
        ),
        User(
            id = 1,
            name = "Marius Gheorghe",
            email = "email0@gmail.com",
            status = "inactive"
        ),
        User(
            id = 2,
            name = "Flavius Gheorghe",
            email = "email1@gmail.com",
            status = "inactive"
        ),
        User(
            id = 3,
            name = "Marian Anton",
            email = "email2@gmail.com",
            status = "active"
        ),
        User(
            id = 4,
            name = "Diana Ion",
            email = "email3@gmail.com",
            status = "active"
        ),
        User(
            id = 5,
            name = "Elisabeta Atodiresei",
            email = "email4@gmail.com",
            status = "active"
        ),
        User(
            id = 7,
            name = "Bogdan Berescu",
            email = "email5@gmail.com",
            status = "active"
        ),
        User(
            id = 8,
            name = "Nicoleta Onofrei",
            email = "email6@gmail.com",
            status = "active"
        ),
        User(
            id = 10,
            name = "Nicoleta Onofrei",
            email = "email6@gmail.com",
            status = "active"
        ),
        User(
            id = 12,
            name = "Nicoleta Onofrei",
            email = "email6@gmail.com",
            status = "active"
        ),
        User(
            id = 13,
            name = "Nicoleta Onofrei",
            email = "email6@gmail.com",
            status = "active"
        ),
        User(
            id = 14,
            name = "Nicoleta Onofrei",
            email = "email6@gmail.com",
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