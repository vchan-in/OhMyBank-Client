package com.vc.ohmybank.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.vc.ohmybank.R
import com.vc.ohmybank.domain.User
import com.vc.ohmybank.ui.theme.StyledColors
import com.vc.ohmybank.ui.theme.StyledText
import com.vc.ohmybank.ui.viewmodel.ContactListScreenState
import com.vc.ohmybank.ui.viewmodel.ContactListViewModel

object ContactListPage {
    @Composable
    fun Screen(
        navController: NavController,
        viewModel: ContactListViewModel = viewModel()
    ) {
        val uiState by viewModel.uiState.collectAsState()

        when (uiState) {
            is ContactListScreenState.Success -> with((uiState as ContactListScreenState.Success)) {
                SuccessState(
                    userList = this.usersList,
                    avatarStrings = this.avatarStringsList,
                    navController = navController
                )
            }

            is ContactListScreenState.Loading -> LoadingState()
        }
    }

    @Composable
    private fun LoadingState() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = StyledColors.WHITE, strokeWidth = 2.dp)
        }
    }

    @Composable
    private fun SuccessState(
        navController: NavController,
        userList: List<User>,
        avatarStrings: List<String>
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(1.dp),
        ) {
            ContactListTopBar()
            ContactListTitle()
            LazyColumn(
                modifier = Modifier.height(564.dp)
            ) {
                itemsIndexed(userList) { index, userDetails ->
                    ContactDetailsCard(
                        navController = navController,
                        userDetails,
                        avatarStrings[index]
                    )
                }
            }
        }
    }

    @Composable
    private fun ContactListTopBar() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(StyledColors.WHITE)
                .padding(
                    top = 48.dp,
                    start = 24.dp,
                    end = 24.dp,
                    bottom = 16.dp
                )
        ) {
            Text(
                text = stringResource(id = R.string.contact_list_top_bar),
                style = StyledText.displayBold,
            )
        }
    }

    @Composable
    private fun ContactListTitle() {
        Text(
            text = stringResource(id = R.string.contact_list_title),
            style = StyledText.textSemiBold,
            modifier = Modifier.padding(
                vertical = 12.dp,
                horizontal = 24.dp
            )
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun ContactDetailsCard(
        navController: NavController,
        userDetails: User,
        avatarString: String
    ) {
        Card(
            modifier = Modifier.padding(1.dp),
            shape = RoundedCornerShape(0.dp),
            colors = CardDefaults.cardColors(containerColor = StyledColors.WHITE),
            onClick = {
                navigateToUserPosts(
                    navController,
                    userId = userDetails.id.toInt(),
                    userAvatarString = avatarString,
                    userName = userDetails.name,
                    userEmail = userDetails.email
                )
            }
        ) {
            Row(
                modifier = Modifier
                    .padding(
                        start = 24.dp,
                        top = 24.dp,
                        bottom = 24.dp,
                    )
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(46.dp)
                ) {
                    UserAvatar.GetAvatarForUser(avatarString = avatarString)
                }
                ContactNameText(contactName = userDetails.name)
                RightChevron()
            }
        }
    }

    @Composable
    private fun ContactNameText(contactName: String) {
        Text(
            text = contactName,
            style = StyledText.textRegularBlack,
        )
    }

    @Composable
    private fun RightChevron() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_nav_icon),
                contentDescription = stringResource(id = R.string.navigation_description)
            )
        }
    }

    private fun navigateToUserPosts(
        navController: NavController,
        userId: Int,
        userAvatarString: String,
        userName: String,
        userEmail: String
    ) =
        navController.navigate("user-post/${userId}/${userAvatarString}/${userName}/${userEmail}") {
            launchSingleTop = true
        }
}