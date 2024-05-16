package com.jgomez.cabifytest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.core.common.navigation_constants.OnboardingFeature

@Composable
fun AppNavGraph(navController: NavHostController, navigationProvider: NavigationProvider) {

    NavHost(navController = navController, startDestination = OnboardingFeature.nestedRoute) {
        navigationProvider.productNavigation.registerGraph(
            navController, this
        )
        navigationProvider.onboardingNavigation.registerGraph(
            navController, this
        )
    }

}