package com.nj.space.nav

sealed class Screen(val route: String) {
    object AuthGraph : Screen("auth_graph")
    object MainGraph : Screen("main_graph")
    object PostGraph : Screen("post_graph")

    // Bottom Bar Items
    object Home : Screen("home")
    object Search : Screen("search")
    object Profile : Screen("profile")
}