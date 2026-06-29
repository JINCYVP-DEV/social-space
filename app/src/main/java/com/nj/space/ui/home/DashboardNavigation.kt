package com.nj.space.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nj.space.nav.postNestedGraph
import com.nj.space.ui.auth.AuthScreen
import com.nj.space.ui.auth.SettingsScreen
import com.nj.space.ui.auth.SettingsScreenNavigationEvents

sealed class MainScreen(val route: String)
{
    object MainGraph: MainScreen("main_graph")
    object Home: MainScreen("home")
}

fun NavGraphBuilder.dashboardScreenGraph(navHostController: NavHostController)
{
    navigation(route = MainScreen.MainGraph.route, startDestination = MainScreen.Home.route)
    {
        composable(MainScreen.Home.route)
        {
            HomeNavigation()
        }
        postNestedGraph(navHostController)

        //if we need screens without bottombar ie settings
        composable("settings")
        {
            SettingsScreen()
            {
                when(it)
                {
                    SettingsScreenNavigationEvents.NavigateToLogin ->
                    {
                        navHostController.navigate(AuthScreen.Login.route)
                    }
                    SettingsScreenNavigationEvents.OnBack -> {
                        navHostController.popBackStack()
                    }
                }
            }
        }
    }
}