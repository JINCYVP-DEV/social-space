package com.nj.space.ui.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nj.space.ui.auth.AuthState

sealed class SplashScreenNavigation()
{
    object NavigateToHome:SplashScreenNavigation()
    object NavigateToLogin:SplashScreenNavigation()
}
@Composable
fun SplashScreen(viewModel: SplashScreenViewModel = hiltViewModel(),onNavigate:(SplashScreenNavigation)-> Unit)
{
    val authState by viewModel.authState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
    LaunchedEffect(authState) {
        when (authState) {
            AuthState.LOGGED_IN -> onNavigate(SplashScreenNavigation.NavigateToHome)
            AuthState.LOGGED_OUT -> onNavigate(SplashScreenNavigation.NavigateToLogin)
            AuthState.LOADING -> { /* stay here */
            }
        }
    }
}