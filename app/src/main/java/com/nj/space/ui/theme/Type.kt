package com.nj.space.ui.theme


import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nj.space.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)


val Typography.primaryDefault: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = BackgroundSoftBlue
        )
    }

val Typography.Bold20: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            lineHeight = 24.sp,
        )
    }
val Typography.Bold16: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 24.sp,
        )
    }

val Typography.Bold18: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            lineHeight = 24.sp,
        )
    }

val Typography.Bold14: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 24.sp,
        )
    }

val Typography.Bold12: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 24.sp,
        )
    }


val Typography.SemiBold16: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            lineHeight = 24.sp,
        )
    }

val Typography.SemiBold18: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            lineHeight = 24.sp,
        )
    }


val Typography.SemiBold14: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            lineHeight = 24.sp,
        )
    }

val Typography.SemiBold12: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            lineHeight = 24.sp,
        )
    }

val Typography.SemiBold10: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            fontWeight = FontWeight.SemiBold,
            fontSize = 10.sp,
            lineHeight = 24.sp,
        )
    }

val Typography.SemiBold24: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            lineHeight = 24.sp,
        )
    }


val Typography.Medium10: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp,
            lineHeight = 24.sp,
        )
    }


val Typography.Medium12: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 24.sp,
        )
    }


val Typography.Medium14: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
    }

val Typography.Medium16: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        )
    }

val Typography.Medium18: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        )
    }

val Typography.Medium20: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            lineHeight = 24.sp,
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        )
    }

val Typography.Medium24: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            lineHeight = 24.sp,
        )
    }
val Typography.Medium32: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            fontWeight = FontWeight.Medium,
            fontSize = 32.sp,
            lineHeight = 24.sp,
        )
    }


val Typography.Normal10: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            lineHeight = 24.sp,
        )
    }

val Typography.Normal8: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontWeight = FontWeight.Normal,
            fontSize = 8.sp,
            lineHeight = 24.sp,
        )
    }


val Typography.Normal12: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 24.sp,
        )
    }

val Typography.Normal14: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 24.sp,
        )
    }

val Typography.Normal16: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
        )
    }


val Typography.Bold24: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeight = 36.sp,
        )
    }

val Typography.Bold36: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            lineHeight = 24.sp,
        )
    }
val Typography.Bold40: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            lineHeight = 24.sp,
        )
    }




