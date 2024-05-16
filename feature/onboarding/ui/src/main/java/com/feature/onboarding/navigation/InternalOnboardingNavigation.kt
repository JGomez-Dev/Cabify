package com.feature.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.common.navigation_constants.OnboardingFeature
import com.core.common.navigation_constants.ProductFeature
import com.core.navigation.NavigationApi
import com.feature.onboarding.composable.OnboardingScreen

object InternalOnboardingNavigation  : NavigationApi {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(startDestination = OnboardingFeature.nestedScreenRoute, route = OnboardingFeature.nestedRoute){
           composable(OnboardingFeature.nestedScreenRoute){
               OnboardingScreen(goToHome = {navController.navigate(ProductFeature.nestedScreenRoute)})
           }
        }
    }
}