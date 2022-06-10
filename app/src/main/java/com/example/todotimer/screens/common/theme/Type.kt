package com.example.todotimer.screens.common.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.todotimer.R

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.overpass_bold, FontWeight.Normal)
        ),
        fontWeight = FontWeight.Normal,
    ),
    button = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.overpass_bold, FontWeight.Normal)
        ),
        fontWeight = FontWeight.Normal,
    ),
    defaultFontFamily = FontFamily(
        Font(R.font.overpass_bold, FontWeight.Normal)
    ),
)
