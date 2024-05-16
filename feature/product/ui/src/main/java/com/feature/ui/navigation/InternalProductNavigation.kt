package com.feature.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.common.navigation_constants.ProductFeature
import com.core.navigation.NavigationApi
import com.feature.ui.HomeScreen

object InternalProductNavigation : NavigationApi {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(startDestination = ProductFeature.nestedScreenRoute, route = ProductFeature.nestedRoute){
           composable(ProductFeature.nestedScreenRoute){
               HomeScreen()
           }
        }
    }
}