package com.nj.space.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.nj.space.ui.auth.authNavigationGraph
import com.nj.space.ui.home.dashboardScreenGraph
import com.nj.space.ui.splash.SplashNavigation
import com.nj.space.ui.splash.splashGraph

@Composable
fun InstaNavigationGraph()
{
    val navController= rememberNavController()
    Scaffold(modifier = Modifier.fillMaxSize()) {innerPadding->
        NavHost(navController = navController, startDestination = SplashNavigation.route, modifier = Modifier.padding(innerPadding))
        {
            splashGraph(navController)
            authNavigationGraph(navController)
            dashboardScreenGraph(navController )
        }
    }
}