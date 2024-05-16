package com.jgomez.cabifytest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.core.common.theme.CabifyTheme
import com.jgomez.cabifytest.navigation.AppNavGraph
import com.jgomez.cabifytest.navigation.NavigationProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var navigationProvider: NavigationProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CabifyTheme {
                val navController = rememberNavController()
                App(navController, navigationProvider)
            }

        }
    }
}

@Composable
fun App(navHostController: NavHostController, navigationProvider: NavigationProvider) {
    AppNavGraph(navController = navHostController, navigationProvider = navigationProvider)
}

