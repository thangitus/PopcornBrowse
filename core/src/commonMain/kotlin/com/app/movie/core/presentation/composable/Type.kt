package com.app.movie.core.presentation.composable


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview



@Immutable
object TextStyles {

    val Heading1 = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 34.sp,
        lineHeight = 41.sp,
    )
    val Heading1Bold = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp,
        lineHeight = 41.sp,
    )

    // heading2: Bold, size=28, lineHeight=34
    val Heading2 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 34.sp,
    )

    // heading3: size=22, lineHeight=28 => 3 variants: Regular, Medium, Semibold
    val Heading3Regular = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
    )
    val Heading3Medium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 28.sp,
    )
    val Heading3Semibold = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
    )

    // heading4: Medium, size=15, lineHeight=20
    val Heading4 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        lineHeight = 20.sp,
    )

    // body: size=17, lineHeight=22 => Regular & Semibold
    val BodyRegular = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        lineHeight = 22.sp,
    )

    val BodySemibold = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        lineHeight = 22.sp,
    )
    val BodyBold = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
        lineHeight = 22.sp,
    )

    // body1: size=15, lineHeight=20 => Regular, Medium, Semibold
    val Body1Regular = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 20.sp,
    )
    val Body1Medium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        lineHeight = 20.sp,
    )
    val Body1Semibold = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        lineHeight = 20.sp,
    )

    // subtext: size=13, lineHeight=18 => Regular & Semibold
    val SubtextRegular = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 18.sp,
    )
    val SubtextSemibold = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        lineHeight = 18.sp,
    )
}


/**
 * You can create a single Typography object to map
 * to Material3's typical text style placeholders if desired.
 * This is optional and up to your design system usage.
 */
val Typography = Typography(
    displayLarge = TextStyles.Heading1,   // example mapping
    displayMedium = TextStyles.Heading2,
    displaySmall = TextStyles.Heading3Semibold,
    headlineLarge = TextStyles.Heading3Medium,
    headlineMedium = TextStyles.Heading3Regular,
    headlineSmall = TextStyles.Heading4,

    bodyLarge = TextStyles.BodyRegular,
    bodyMedium = TextStyles.Body1Regular,
    bodySmall = TextStyles.SubtextRegular,

    // etc. Add or replace as needed
)

@Preview
@Composable
fun TextStylesPreview() {
    // Use a scrollable Column in case content exceeds the screen height
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Heading1: Semibold, size=34, lineHeight=41",
            style = TextStyles.Heading1.copy(color = Color.White),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Heading2: Bold, size=28, lineHeight=34",
            style = TextStyles.Heading2.copy(color = Color.White)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Heading3 Regular: Normal, size=22, lineHeight=28",
            style = TextStyles.Heading3Regular.copy(color = Color.White)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Heading3 Medium: Medium, size=22, lineHeight=28",
            style = TextStyles.Heading3Medium.copy(color = Color.White)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Heading3 Semibold: Semibold, size=22, lineHeight=28",
            style = TextStyles.Heading3Semibold.copy(color = Color.White)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Heading4: Medium, size=15, lineHeight=20",
            style = TextStyles.Heading4.copy(color = Color.White)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Body Regular: Normal, size=17, lineHeight=22",
            style = TextStyles.BodyRegular.copy(color = Color.White)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Body Semibold: Semibold, size=17, lineHeight=22",
            style = TextStyles.BodySemibold.copy(color = Color.White)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Body1 Regular: Normal, size=15, lineHeight=20",
            style = TextStyles.Body1Regular.copy(color = Color.White)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Body1 Medium: Medium, size=15, lineHeight=20",
            style = TextStyles.Body1Medium.copy(color = Color.White)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Body1 Semibold: Semibold, size=15, lineHeight=20",
            style = TextStyles.Body1Semibold.copy(color = Color.White)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Subtext Regular: Normal, size=13, lineHeight=18",
            style = TextStyles.SubtextRegular.copy(color = Color.White)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Subtext Semibold: Semibold, size=13, lineHeight=18",
            style = TextStyles.SubtextSemibold.copy(color = Color.White)
        )
    }
}
