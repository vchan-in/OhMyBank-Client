package com.vc.ohmybank.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import coil.compose.AsyncImage
import com.vc.ohmybank.R
import com.vc.ohmybank.ui.theme.StyledColors
import com.vc.ohmybank.ui.theme.StyledText

object UserAvatar {
    @Composable
    fun GetAvatarForUser(avatarString: String) = if (avatarString.isDigitsOnly())
        ContactImageAvatar(imageId = avatarString)
    else
        ContactInitialsAvatar(contactInitials = avatarString)

    @Composable
    private fun ContactImageAvatar(imageId: String) {
        AsyncImage(
            model = "https://picsum.photos/id/${imageId}/200/200",
            contentDescription = stringResource(R.string.user_avatar_description),
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(50.dp))
        )
    }

    @Composable
    private fun ContactInitialsAvatar(contactInitials: String) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(SolidColor(StyledColors.LIGHT_GRAY_INACTIVE))
        }
        Text(text = contactInitials, style = StyledText.textBoldWhite)
    }
}