package com.nj.space.ui.auth

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nj.social.space.R
import com.nj.space.core.AuthButton
import com.nj.space.core.PasswordOutlinedTextField
import com.nj.space.core.SocialSpaceOutlinedTextField
import com.nj.space.ui.theme.ColorDivider
import com.nj.space.ui.theme.ColorError
import com.nj.space.ui.theme.Normal12
import com.nj.space.ui.theme.Orange0
import com.nj.space.ui.theme.SemiBold12
import com.nj.space.ui.theme.SemiBold14
import com.nj.space.ui.theme.TextWhite
import com.nj.space.ui.theme.TopWhite
import com.nj.space.ui.theme.Focused
import com.nj.space.ui.theme.Orange1
import com.nj.space.ui.theme.Orange2
import com.nj.space.ui.theme.Unfocused

sealed class LoginNavigationEvent {
    object OnLoginSuccess : LoginNavigationEvent()
    object OnNavigateToSignUp : LoginNavigationEvent()
}

@Composable
fun Login(
    viewModel: AuthViewModel = hiltViewModel(),
    onNavigation: (LoginNavigationEvent) -> Unit
) {
    val emailState = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Screen(
        emailState = emailState,
        password = password,
        passwordVisible = passwordVisible,
        uiState = uiState,
        onLoginSuccess = {onNavigation(LoginNavigationEvent.OnLoginSuccess)},
        onNavigateToSignUp = {onNavigation(LoginNavigationEvent.OnNavigateToSignUp)},
        onLogin = {
            viewModel.login(emailState.value, password.value)
        }
    )
}

@Composable
fun Screen(
    emailState: MutableState<String>,
    password: MutableState<String>,
    passwordVisible: MutableState<Boolean>,
    uiState: AuthUiState,
    onLoginSuccess: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    onLogin: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // App Logo
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Light,
            fontSize = 48.sp,
            letterSpacing = 2.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 40.dp)
        )

        Spacer(Modifier.height(16.dp))

        // Email TextField
        SocialSpaceOutlinedTextField(emailState,stringResource(R.string.text_ph_username_email))

        Spacer(modifier = Modifier.height(10.dp))

        // Password TextField
        PasswordOutlinedTextField(password,passwordVisible)

        Spacer(modifier = Modifier.height(20.dp))

        // Login Button
        AuthButton(
            onClick = {
                onLogin()
                focusManager.clearFocus()
                keyboardController?.hide()
            },
            enabled =  emailState.value.isNotEmpty() && password.value.isNotEmpty(),
            uiState = uiState
        )

        Spacer(modifier = Modifier.height(16.dp))

        // OR Divider
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(ColorDivider)
            )
            Text(
                stringResource(R.string.text_or),
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.SemiBold12,
                color = Focused
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(ColorDivider)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Forgot Password Button
        TextButton(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.text_forgot_password),
                style = MaterialTheme.typography.Normal12,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Divider
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(ColorDivider)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Sign Up Section
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.text_dont_have_account),
                style = MaterialTheme.typography.Normal12,
                color = Color.Black
            )
            TextButton(
                onClick = { onNavigateToSignUp() },
                modifier = Modifier.padding(0.dp)
            ) {
                Text(
                    text = stringResource(R.string.text_sign_up),
                    style = MaterialTheme.typography.SemiBold12,
                    color = Focused
                )
            }
        }

        // Error Message
        if (uiState is AuthUiState.Error) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                uiState.message,
                color = ColorError,
                style = MaterialTheme.typography.Normal12,
                modifier = Modifier.padding(16.dp)
            )
        }

        // Success Navigation
        if (uiState is AuthUiState.Success) {
            LaunchedEffect(Unit) {
                onLoginSuccess()
            }
        }
    }
}