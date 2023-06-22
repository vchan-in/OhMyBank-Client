package com.vc.ohmybank.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vc.ohmybank.ui.screens.ContactListPage
import com.vc.ohmybank.ui.screens.UserPostPage
import com.vc.ohmybank.ui.viewmodel.ContactListViewModel
import com.vc.ohmybank.ui.viewmodel.UserPostViewModel

@Composable
fun UserPostApp(
    contactListViewModel: ContactListViewModel,
    userPostViewModel: UserPostViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "contact-list") {
        composable(route = "contact-list") {
            ContactListPage.Screen(navController, contactListViewModel)

            userPostViewModel.clearUserPostsFlow()
        }

        composable(
            route = "user-post/{userId}/{userAvatarString}/{userName}/{userEmail}",
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType },
                navArgument("userAvatarString") { type = NavType.StringType },
                navArgument("userName") { type = NavType.StringType },
                navArgument("userEmail") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val userId by remember {
                mutableStateOf( backStackEntry.arguments?.getInt("userId")?.toLong() ?: 0)
            }

            LaunchedEffect(userId) {
                    userPostViewModel.updateUserPosts(userId)
            }

            UserPostPage.Screen(
                navController,
                backStackEntry.arguments?.getString("userAvatarString")!!,
                backStackEntry.arguments?.getString("userName")!!,
                backStackEntry.arguments?.getString("userEmail")!!,
                userPostViewModel
            )
        }
    }
}