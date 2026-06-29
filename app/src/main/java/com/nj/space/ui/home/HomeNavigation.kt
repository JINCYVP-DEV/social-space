package com.nj.space.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nj.space.ui.MessageScreen
import com.nj.space.ui.ProfileScreen
import com.nj.space.ui.ReelsScreen
import com.nj.space.ui.SearchScreen

sealed class BottomNavItem(val route: String,val icon: ImageVector,val label: String)
{
    object Home: BottomNavItem("home",Icons.Default.Home,"Home")
    object Search : BottomNavItem("search", Icons.Default.Search, "Search")
    object Reels : BottomNavItem("reels", Icons.Default.VideoLibrary, "Reels")
    object Message : BottomNavItem("message", Icons.Default.NearMe, "Message")
    object Profile : BottomNavItem("profile", Icons.Default.Person, "Profile")
}

@Composable
fun HomeNavigation()
{
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination= navBackStackEntry?.destination
    val shouldShowBottomBar = navBackStackEntry?.destination?.hierarchy?.none{
        it.route=="post_graph"
    }==true
val items =listOf(BottomNavItem.Home,
    BottomNavItem.Search,
    BottomNavItem.Reels,
    BottomNavItem.Message,
    BottomNavItem.Profile
)
    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar)
            {
                NavigationBar {
                    items.forEach { item ->
                        val isSelected = currentDestination?.hierarchy?.any {
                            it.route == item.route
                        } == true

                        NavigationBarItem(
                            selected = isSelected,
                            label = { Text(item.label) },
                            icon = { Icon(item.icon, item.label) },
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id)
                                    {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { padding ->
        Box {
            NavHost(navController = navController,
                startDestination = BottomNavItem.Home.route,
                )
            {
                composable(BottomNavItem.Home.route)
                {
                    HomeScreen()
                }
                composable(BottomNavItem.Search.route)
                {
                    SearchScreen()
                }
                composable(BottomNavItem.Reels.route)
                {
                    ReelsScreen()
                }
                composable(BottomNavItem.Message.route)
                {
                    MessageScreen()
                }
                composable(BottomNavItem.Profile.route)
                {
                    ProfileScreen()
                }
            }
        }

    }
}