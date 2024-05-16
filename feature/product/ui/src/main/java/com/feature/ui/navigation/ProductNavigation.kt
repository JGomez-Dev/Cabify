package com.feature.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.core.navigation.NavigationApi

interface ProductNavigation : NavigationApi

class ProductNavigationImpl: ProductNavigation {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        InternalProductNavigation.registerGraph(
            navController, navGraphBuilder
        )
    }
}