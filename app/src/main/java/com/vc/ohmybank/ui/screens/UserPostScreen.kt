package com.vc.ohmybank.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.vc.ohmybank.R
import com.vc.ohmybank.domain.Activity
import com.vc.ohmybank.ui.theme.StyledColors
import com.vc.ohmybank.ui.theme.StyledText
import com.vc.ohmybank.ui.viewmodel.UserActivityScreenState
import com.vc.ohmybank.ui.viewmodel.UserPostViewModel

object UserPostPage {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Screen(
        navController: NavController,
        contactAvatarString: String,
        contactName: String,
        contactEmail: String,
        viewModel : UserPostViewModel = viewModel()
    ) {
        val postListState by viewModel.postListState.collectAsState()

        Scaffold(topBar = { UserPostTopBar(navController) }) {
            when (postListState) {
                is UserActivityScreenState.Loading -> LoadingState()
                else -> with((postListState as UserActivityScreenState.Success).activityList) {
                    SuccessState(
                        it,
                        contactAvatarString,
                        contactName,
                        contactEmail,
                        activities = this
                    )
                }
            }
        }
    }

    @Composable
    private fun SuccessState(
        paddingValues: PaddingValues,
        contactAvatarString: String,
        contactName: String,
        contactEmail: String,
        activities: List<Activity>
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(46.dp)
            ) {
                UserAvatar.GetAvatarForUser(avatarString = contactAvatarString)
            }
            ContactName(contactName = contactName)
            ContactEmail(contactEmail = contactEmail)

            LazyColumn(modifier = Modifier.height(480.dp)) {
                itemsIndexed(activities) { _, posting ->
                    UserPostCard(posting = posting)
                }
            }
        }
    }

    @Composable
    private fun LoadingState() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = StyledColors.DARK_GRAY, strokeWidth = 2.dp)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun UserPostTopBar(navController: NavController) {
        TopAppBar(
            title = { TopBarTitle() },
            navigationIcon = { TopBarIcon(navController) },
        )
    }

    @Composable
    private fun TopBarTitle() = Text(
        text = stringResource(id = R.string.user_post_appbar_title),
        style = StyledText.displayBold
    )

    @Composable
    private fun TopBarIcon(navController: NavController) = IconButton(
        onClick = { navigateToContactList(navController) }
    ) {
        Image(
            painter = painterResource(R.drawable.ic_navigation_back),
            contentDescription = stringResource(id = R.string.navigation_back_description)
        )
    }

    @Composable
    private fun ContactName(contactName: String) {
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = contactName,
            style = StyledText.textBoldBlack,
        )
    }

    @Composable
    private fun ContactEmail(contactEmail: String) {
        Text(
            modifier = Modifier.padding(top = 10.dp, bottom = 34.dp),
            text = contactEmail,
            style = StyledText.displayRegular,
        )
    }

    @Composable
    private fun UserPostCard(posting: Activity) {
        Card(
            modifier = Modifier.padding(1.dp),
            shape = RoundedCornerShape(0.dp),
            colors = CardDefaults.cardColors(containerColor = StyledColors.GRAY_INFO)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                ActivityTitle(title = posting.title)
                ActivityContent(content = posting.description)
            }
        }
    }

    @Composable
    fun ActivityTitle(title: String) {
        Text(
            text = title,
            style = StyledText.textBoldBlack,
            modifier = Modifier.padding(bottom = 10.dp),
            maxLines = 1
        )
    }

    @Composable
    fun ActivityContent(content: String) {
        var postingBody by remember { mutableStateOf(content) }

        Text(
            text = postingBody,
            style = StyledText.textRegularGray,
            maxLines = 2,
            onTextLayout = { textLayoutResult ->
                if (textLayoutResult.hasVisualOverflow) {
                    val lastCharacterRendered = textLayoutResult.getLineEnd(
                        lineIndex = 1,
                        visibleEnd = true
                    )

                    postingBody = postingBody
                        .substring(startIndex = 0, endIndex = lastCharacterRendered) + "..."
                }
            }
        )
    }

    private fun navigateToContactList(navController: NavController) =
        navController.navigate("contact-list") {
            launchSingleTop = true
        }
}