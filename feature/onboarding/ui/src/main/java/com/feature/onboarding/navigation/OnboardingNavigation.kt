package com.feature.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.core.navigation.NavigationApi

interface OnboardingNavigation : NavigationApi

class OnboardingNavigationImpl: OnboardingNavigation {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        InternalOnboardingNavigation.registerGraph(
            navController, navGraphBuilder
        )
    }
}