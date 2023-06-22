package com.vc.ohmybank.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.vc.ohmybank.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val SFProDisplay = FontFamily(
    Font(R.font.sfpro_display_regular, FontWeight.Normal),
    Font(R.font.sfpro_display_bold, FontWeight.Bold)
)

val SFProText = FontFamily(
    Font(R.font.sfpro_text_regular, FontWeight.Normal),
    Font(R.font.sfpro_text_semibold, FontWeight.SemiBold),
    Font(R.font.sfpro_text_bold, FontWeight.Bold)
)