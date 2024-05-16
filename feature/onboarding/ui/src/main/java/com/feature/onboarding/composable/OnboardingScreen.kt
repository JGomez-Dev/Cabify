package com.feature.onboarding.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.core.common.components.button.CabifyButton
import com.core.common.theme.Cabify
import com.core.common.theme.CabifyTheme
import com.feature.onboarding.R

@Composable
fun OnboardingScreen(
    theme: CabifyTheme = Cabify.theme,
    goToHome: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(theme.semanticColor.background),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier.padding(theme.padding.padding32),
            verticalArrangement = Arrangement.spacedBy(theme.padding.padding32),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.cabify_logo),
                contentDescription = ""
            )
            Text(
                modifier = Modifier.padding(theme.padding.padding16),
                text = stringResource(R.string.fall_in_love_label),
                textAlign = TextAlign.Center,
                style = theme.semanticTextStyle.cabifyHeading0(false),
                color = theme.semanticColor.accent
            )
            Text(
                stringResource(R.string.welcome_label), textAlign = TextAlign.Center,
                style = theme.semanticTextStyle.cabifyHeading6(false),
                color = theme.semanticColor.accentLight
            )
            CabifyButton(
                text = stringResource(R.string.label_start),
                onClick = { goToHome() }
            )
        }
    }
}

