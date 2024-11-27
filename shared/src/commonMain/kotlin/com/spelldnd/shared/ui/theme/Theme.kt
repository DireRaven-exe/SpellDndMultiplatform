package com.spelldnd.shared.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColorScheme(
    primary = darkPrimaryColor,
    secondary = darkPrimaryColor,
    surface = darkSurface,
    onSurface = darkTextPrimary,
    background = darkSurface
)

private val LightColorPalette = lightColorScheme(
    primary = darkPrimaryColor,
    secondary = darkPrimaryColor,
    surface = contentColor,
    onSurface = darkSurface,
    background = contentColor
)

val CustomDarkColors = CustomColorsPalette(
    primaryBackground = Color(0xFF141718),
    primaryText = Color(0xFFF2F4F5),

    containerSecondary = Color(0xff232627),
    secondaryText = Color(0xffabacb8),

    selectedText = Color(0xff141718),
    tintColor = Color(0xFF485866),


    primaryIcon = Color(0xffc0c0c0),
    secondaryIcon = Color(0xffabacb8),
    selectedIcon = Color(0xfffd6246),
    unselectedIcon = Color(0xff454545),

    primaryButtonTextColor = Color(0xFF5EB4F6),
    secondaryButtonTextColor = Color(0xFF7993AA),

    buttonContainer = Color(0xFF03D9C5),
    buttonContent = Color(0xFF000000),

    cardInfoBackground = Color(0xffd8d3d3),
    cardInfoTextPrimary = Color(0xff111111),

    checkBoxContainerNotSelected = Color(0xffeefdf7),
    checkBoxContainerSelected = Color(0xff252525),

    checkBoxContentNotSelected = Color(0xFF000000),
    checkBoxContentSelected = Color(0xffeefdf7),

    notificationBackground = Color(0xa6ff6347),
    notificationContent = Color(0xffeefdf7)
)

val CustomLightColors = CustomColorsPalette(
    primaryBackground = Color(0xfff5f6f8),
    primaryText = Color(0xff050505),

    containerSecondary = Color(0xfffafbfb),
    secondaryText = Color(0xffabacb8),

    selectedText = Color(0xfffafbfb),
    tintColor = Color(0xFF485866),

    primaryIcon = Color(0xff333333),
    secondaryIcon = Color(0xffc0c0c0),
    selectedIcon = Color(0xfffd6246),
    unselectedIcon = Color(0xffabacb8),

    primaryButtonTextColor = Color(0xFF5EB4F6),
    secondaryButtonTextColor = Color(0xFF7993AA),

    buttonContainer = Color(0xFF03D9C5),
    buttonContent = Color(0xFF000000),

    cardInfoBackground = Color(0xffd8d3d3),
    cardInfoTextPrimary = Color(0xff111111),

    checkBoxContainerNotSelected = Color(0xFF000000),
    checkBoxContainerSelected = Color(0xff484848),

    checkBoxContentNotSelected = Color(0xffeefdf7),
    checkBoxContentSelected = Color(0xffd8d3d3),

    notificationBackground = Color(0x9e333333),
    notificationContent = Color(0xffeefdf7)
)

data class TypoDndPalette(
    val darkBackground: Color = Color.Unspecified,
    val darkCard: Color = Color.Unspecified,
    val darkButton: Color = Color.Unspecified,
    val lightSeaGreen: Color = Color.Unspecified,
    val greyText: Color = Color.Unspecified,
    val davysGray: Color = Color.Unspecified,
    val lightGrayishBlue: Color = Color.Unspecified,
    val paleSilver: Color = Color.Unspecified,
    val green: Color = Color.Unspecified,
    val mainRed: Color = Color.Unspecified
)

val typoDndPalette = TypoDndPalette(
    darkBackground = Color(0xff141718),
    darkCard = Color(0xff232627),
    darkButton = Color(0xff454545),
    lightSeaGreen = Color(0xff333333),
    greyText = Color(0xffabacb8),
    davysGray = Color(0xffc0c0c0),
    lightGrayishBlue = Color(0xfff5f6f8),
    paleSilver = Color(0xfffafbfb),
    green = Color(0xff54c575),
    mainRed = Color(0xfffd6246)
)


data class SpellCardPallete(
    val containerColor: Color = Color.Unspecified,
    val textColor: Color = Color.Unspecified,
    val iconColor: Color = Color.Unspecified
)

val spellCardPallete = SpellCardPallete(
    containerColor = Color(0xff050505),
    textColor = contentColor,
    iconColor = contentColor
)

data class CustomColorsPalette (
    val primaryBackground: Color = Color.Unspecified,
    val primaryText: Color = Color.Unspecified,

    val containerSecondary: Color = Color.Unspecified,
    val secondaryText: Color = Color.Unspecified,

    val selectedText: Color = Color.Unspecified,
    val tintColor: Color = Color.Unspecified,

    val primaryIcon: Color = Color.Unspecified,
    val secondaryIcon: Color = Color.Unspecified,
    val selectedIcon: Color = Color.Unspecified,
    val unselectedIcon: Color = Color.Unspecified,

    val primaryButtonTextColor: Color = Color.Unspecified,
    val secondaryButtonTextColor: Color = Color.Unspecified,

    val buttonContainer: Color = Color.Unspecified,
    val buttonContent: Color = Color.Unspecified,

    val cardInfoBackground: Color = Color.Unspecified,
    val cardInfoTextPrimary: Color = Color.Unspecified,

    val checkBoxContainerNotSelected: Color = Color.Unspecified,
    val checkBoxContainerSelected: Color = Color.Unspecified,

    val checkBoxContentNotSelected: Color = Color.Unspecified,
    val checkBoxContentSelected: Color = Color.Unspecified,

    val notificationBackground: Color = Color.Unspecified,
    val notificationContent: Color = Color.Unspecified
)

val LocalCustomColorsPalette = staticCompositionLocalOf { CustomColorsPalette() }

val LocalSpellCardPalette = staticCompositionLocalOf { SpellCardPallete() }

val LocalTypoDndPalette = staticCompositionLocalOf { TypoDndPalette() }

@Composable
fun SpellDndMultiPlatformTheme(
    useDarkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = if (!useDarkTheme) {
        LightColorPalette
    } else {
        DarkColorPalette
    }

    val customColorsPalette = if (!useDarkTheme) {
        CustomLightColors
    } else {
        CustomDarkColors
    }

    val typography = Typography(
        displayLarge = header1,
        headlineLarge = header2,
        headlineMedium = header3,
        displayMedium = header4,
        headlineSmall = header5,
        displaySmall = header6
    )

    CompositionLocalProvider(
        LocalCustomColorsPalette provides customColorsPalette,
        LocalSpellCardPalette provides spellCardPallete,
        LocalTypoDndPalette provides typoDndPalette
    ) {
        MaterialTheme(
            colorScheme = colors,
            typography = typography,
            content = {
                Surface(modifier = Modifier, content = content)
            }
        )
    }
}