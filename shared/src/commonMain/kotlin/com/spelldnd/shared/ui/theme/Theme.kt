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

//private val darkColors = darkColorScheme(
//    primary = md_theme_dark_primary,
//    onPrimary = md_theme_dark_onPrimary,
//    primaryContainer = md_theme_dark_background,
//    secondary = md_theme_dark_secondary,
//    surface = md_theme_dark_surface,
//    onError = md_theme_dark_onError,
//)

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
    primaryBackground = Color(0xff050505),
    primaryText = Color(0xFFF2F4F5),

    containerSecondary = Color(0xff090909),
    secondaryText = Color(0xCC7A8A99),

    tintColor = Color(0xFF485866),

    primaryIcon = Color(0xFFFFFFFF),
    secondaryIcon = Color(0xfff6f6f6),

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
)

val CustomLightColors = CustomColorsPalette(
    primaryBackground = Color(0xFFF2F4F5),
    primaryText = Color(0xff050505),

    containerSecondary = Color(0xffe0e0e0),
    secondaryText = Color(0xff090909),

    tintColor = Color(0xFF485866),

    primaryIcon = Color(0xff000000),
    secondaryIcon = Color(0xfff6f6f6),

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
)


private val lightColors = lightColorScheme(
    primary = md_theme_light_primary,
    secondary = md_theme_light_secondary,
    background = md_theme_light_background,
    surface = contentColor,
    onError = md_theme_light_onError,
)



//val CustomLightColors = CustomColorsPalette(
//    elevationBackground = md_theme_light_elevationBackground,
//    mountainBackground = md_theme_light_mountainBackground,
//    busyStatus = md_theme_light_busyStatus,
//    freeStatus = md_theme_light_freeStatus,
//    onSuccess = md_theme_light_onSuccess,
//    secondaryButton = md_theme_light_secondaryButton,
//    primaryTextAndIcon = md_theme_light_primaryTextAndIcon,
//    secondaryTextAndIcon = md_theme_light_secondaryTextAndIcon,
//    tertiaryTextAndIcon = md_theme_light_tertiaryTextAndIcon,
//    pressedPrimaryButton = md_theme_light_pressedPrimaryButton,
//    disabledPrimaryButton = md_theme_light_disabledPrimaryButton
//)

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

    val tintColor: Color = Color.Unspecified,

    val primaryIcon: Color = Color.Unspecified,
    val secondaryIcon: Color = Color.Unspecified,

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
)

val LocalCustomColorsPalette = staticCompositionLocalOf { CustomColorsPalette() }

val LocalSpellCardPalette = staticCompositionLocalOf { SpellCardPallete() }

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
        LocalSpellCardPalette provides spellCardPallete
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