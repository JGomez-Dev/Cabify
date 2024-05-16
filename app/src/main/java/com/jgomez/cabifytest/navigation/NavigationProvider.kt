package com.jgomez.cabifytest.navigation

import com.feature.onboarding.navigation.OnboardingNavigation
import com.feature.ui.navigation.ProductNavigation

data class NavigationProvider(
    val productNavigation: ProductNavigation,
    val onboardingNavigation: OnboardingNavigation
)
