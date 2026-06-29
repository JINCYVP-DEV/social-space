package com.nj.space.ui.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.nj.space.ui.home.MainScreen

sealed class AuthScreen(val route:String)
{
    //this is a group of auth screens
    object AuthGraph: AuthScreen("auth_graph")
    object Login: AuthScreen("login")
    object SignUp: AuthScreen("sign_up")
}
fun NavGraphBuilder.authNavigationGraph(navHostController: NavHostController)
{
    //we can arrange the related screens to a group
    navigation(route = AuthScreen.AuthGraph.route,
        startDestination = AuthScreen.Login.route)
    {
        composable(AuthScreen.Login.route)
        {
            Login(
                onNavigation ={event ->
                    when (event)
                    {
                        LoginNavigationEvent.OnLoginSuccess -> {
                            navHostController.navigate(MainScreen.Home.route)
                        }
                        LoginNavigationEvent.OnNavigateToSignUp -> {
                            navHostController.navigate(AuthScreen.SignUp.route)
                        }
                    }}
            )
        }
        composable(AuthScreen.SignUp.route)
        {
            SignUp(
                onNavigation = { event ->
                    when (event) {
                        SignUpNavigationEvent.LoggedOut -> {
                            navHostController.popBackStack()
                        }

                        SignUpNavigationEvent.OnNavigateToLogin -> {
                            navHostController.popBackStack()
                        }

                        SignUpNavigationEvent.OnSignUpSuccess -> {
                            navHostController.navigate(MainScreen.Home.route) {
                                popUpTo(AuthScreen.Login.route) { inclusive = true }
                            }
                        }
                    }
                })
        }
    }
}