//package com.nj.instagram.nav
//
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Modifier
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.currentBackStackEntryAsState
//import androidx.navigation.compose.navigation
//import androidx.navigation.compose.rememberNavController
//
//@Composable
//fun AppFullNavigation() {
//    val navController = rememberNavController()
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination
//
//    val bottomBarScreens = listOf(Screen.Home.route, Screen.Search.route, Screen.Profile.route)
//
//    val showBottomBar = currentDestination?.route in bottomBarScreens
//
//    Scaffold(
//        bottomBar = {
//            if (showBottomBar) {
//                BottomNavigationBar(navController)
//            }
//        }
//    ) { innerPadding ->
//        Box(modifier = Modifier.padding(innerPadding)) {
//            NavHost(
//                navController = navController,
//                startDestination = Screen.AuthGraph.route
//            ) {
//                // 1. Auth Section
//                navigation(route = Screen.AuthGraph.route, startDestination = "login") {
//                    composable("login") {
//                        LoginScreen(onLoginSuccess = {
//                            navController.navigate(Screen.MainGraph.route) {
//                                popUpTo(Screen.AuthGraph.route) { inclusive = true }
//                            }
//                        })
//                    }
//                }
//
//                // 2. Main App Section (Bottom Bar Visible)
//                navigation(route = Screen.MainGraph.route, startDestination = Screen.Home.route) {
//                    composable(Screen.Home.route) {
//                        HomeScreen(onAddPostClick = {
//                            navController.navigate(Screen.PostGraph.route)
//                        })
//                    }
//                    composable(Screen.Search.route) { SearchScreen() }
//                    composable(Screen.Profile.route) { ProfileScreen() }
//                }
//
//                // 3. Post Creation Section (Bottom Bar Hidden)
//                navigation(route = Screen.PostGraph.route, startDestination = "camera") {
//                    composable("camera") { CameraScreen() }
//                    composable("edit_post") { EditPostScreen() }
//                }
//            }
//        }
//    }
//}