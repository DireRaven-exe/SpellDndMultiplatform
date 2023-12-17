package theme

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

private val darkColors = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_background,
    secondary = md_theme_dark_secondary,
    surface = md_theme_dark_surface,
    onError = md_theme_dark_onError,
)

val CustomDarkColors = CustomColorsPalette(
    primaryBackground = Color(0xFF000000),
    primaryText = Color(0xFFF2F4F5),

    secondaryBackground = Color(0xFF111418),
    secondaryText = Color(0xCC7A8A99),

    tintColor = Color(0xFF485866),

    primaryIcon = Color(0xFFFFFFFF),
    secondaryIcon = Color(0xFF6D6D6D),

    primaryButtonTextColor = Color(0xFF5EB4F6),
    secondaryButtonTextColor = Color(0xFF7993AA),

    buttonBackgroundColor = Color(0xFF03D9C5),
    buttonContentColor = Color(0xFF000000),

    cardInfoBackground = Color(0xffd8d3d3),
    cardInfoTextPrimary = Color(0xff111111)
)


private val lightColors = lightColorScheme(
    primary = md_theme_light_primary,
    secondary = md_theme_light_secondary,
    background = md_theme_light_background,
    surface = md_theme_light_surface,
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

data class CustomColorsPalette (
    val primaryBackground: Color = Color.Unspecified,
    val primaryText: Color = Color.Unspecified,

    val secondaryBackground: Color = Color.Unspecified,
    val secondaryText: Color = Color.Unspecified,

    val tintColor: Color = Color.Unspecified,

    val primaryIcon: Color = Color.Unspecified,
    val secondaryIcon: Color = Color.Unspecified,

    val primaryButtonTextColor: Color = Color.Unspecified,
    val secondaryButtonTextColor: Color = Color.Unspecified,

    val buttonBackgroundColor: Color = Color.Unspecified,
    val buttonContentColor: Color = Color.Unspecified,

    val cardInfoBackground: Color = Color.Unspecified,
    val cardInfoTextPrimary: Color = Color.Unspecified
)

val LocalCustomColorsPalette = staticCompositionLocalOf { CustomColorsPalette() }


@Composable
fun SpellDndMultiPlatformTheme(
    useDarkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = if (!useDarkTheme) {
        lightColors
    } else {
        darkColors
    }

    val customColorsPalette = if (!useDarkTheme) {
        CustomDarkColors
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
        LocalCustomColorsPalette provides customColorsPalette
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