package com.nj.space.ui.auth

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nj.space.R
import com.nj.space.ui.theme.ColorBackground
import com.nj.space.ui.theme.ColorBorder
import com.nj.space.ui.theme.ColorDivider
import com.nj.space.ui.theme.ColorError
import com.nj.space.ui.theme.ColorLink
import com.nj.space.ui.theme.ColorOnBackground
import com.nj.space.ui.theme.ColorPrimary
import com.nj.space.ui.theme.ColorPrimaryDisabled
import com.nj.space.ui.theme.ColorSurfaceVariant
import com.nj.space.ui.theme.ColorTextSecondary
import com.nj.space.ui.theme.Medium14
import com.nj.space.ui.theme.Normal12
import com.nj.space.ui.theme.SemiBold12
import com.nj.space.ui.theme.SemiBold14
import com.nj.space.ui.theme.White

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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackground)
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
            color = ColorOnBackground,
            modifier = Modifier.padding(bottom = 40.dp)
        )

        Spacer(Modifier.height(16.dp))

        // Email TextField
        OutlinedTextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = {
                Text(
                    text = stringResource(R.string.text_ph_username_email),
                    style = MaterialTheme.typography.Medium14
                )
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = MaterialTheme.typography.Medium14,
            shape = RoundedCornerShape(4.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = ColorBorder,
                focusedBorderColor = ColorBorder,
                unfocusedContainerColor = ColorSurfaceVariant,
                focusedContainerColor = ColorBackground
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Password TextField
        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text(stringResource(R.string.text_password), style = MaterialTheme.typography.Medium14) },
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(
                    onClick = { passwordVisible.value = !passwordVisible.value },
                    modifier = Modifier.size(20.dp)
                ) {
                    Icon(
                        imageVector = if (passwordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = if (passwordVisible.value) stringResource(R.string.text_hide_password) else stringResource(R.string.text_show_password),
                        tint = ColorOnBackground,
                        modifier = Modifier.size(18.dp)
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = MaterialTheme.typography.Medium14,
            shape = RoundedCornerShape(4.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = ColorBorder,
                focusedBorderColor = ColorBorder,
                unfocusedContainerColor = ColorSurfaceVariant,
                focusedContainerColor = ColorBackground
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Login Button
        Button(
            onClick = { onLogin() },
            modifier = Modifier.fillMaxWidth(),
            enabled = emailState.value.isNotEmpty() && password.value.isNotEmpty() && uiState !is AuthUiState.Loading,
            colors = ButtonDefaults.buttonColors(
                containerColor = ColorPrimary,
                disabledContainerColor = ColorPrimaryDisabled
            ),
            shape = RoundedCornerShape(4.dp)
        ) {
            if (uiState is AuthUiState.Loading) {
                CircularProgressIndicator(
                    color = ColorBackground,
                    modifier = Modifier.size(16.dp),
                    strokeWidth = 2.dp
                )
            } else {
                Text(text = stringResource(R.string.text_login),
                    style = MaterialTheme.typography.SemiBold14,
                    color = White
                )
            }
        }

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
                color = ColorTextSecondary
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
                color = ColorLink
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
                color = ColorTextSecondary
            )
            TextButton(
                onClick = { onNavigateToSignUp() },
                modifier = Modifier.padding(0.dp)
            ) {
                Text(
                    text = stringResource(R.string.text_sign_up),
                    style = MaterialTheme.typography.SemiBold12,
                    color = ColorLink
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