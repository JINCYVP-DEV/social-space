package com.nj.instagram.ui.auth

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.nj.instagram.R
import com.nj.instagram.ui.theme.ColorBackground
import com.nj.instagram.ui.theme.ColorBorder
import com.nj.instagram.ui.theme.ColorDivider
import com.nj.instagram.ui.theme.ColorError
import com.nj.instagram.ui.theme.ColorLink
import com.nj.instagram.ui.theme.ColorOnBackground
import com.nj.instagram.ui.theme.ColorPrimary
import com.nj.instagram.ui.theme.ColorPrimaryDisabled
import com.nj.instagram.ui.theme.ColorSurfaceVariant
import com.nj.instagram.ui.theme.ColorTextSecondary
import com.nj.instagram.ui.theme.Medium14
import com.nj.instagram.ui.theme.Normal12
import com.nj.instagram.ui.theme.SemiBold12
import com.nj.instagram.ui.theme.SemiBold14
import com.nj.instagram.ui.theme.White

sealed class SignUpNavigationEvent {
    object LoggedOut : SignUpNavigationEvent()

    object OnNavigateToLogin : SignUpNavigationEvent()

    object OnSignUpSuccess : SignUpNavigationEvent()
}
@Composable
fun SignUp(viewModel: AuthViewModel = hiltViewModel(),onNavigation:(SignUpNavigationEvent)->Unit)
{
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var showEmailError by remember { mutableStateOf(false) }
    val uiState by  viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackground)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Light,
            fontSize = 48.sp,
            letterSpacing = 2.sp,
            color = ColorOnBackground,
            modifier = Modifier.padding(bottom = 40.dp)
        )

        OutlinedTextField(
            value = username,
            onValueChange = {
                username=it
            },
            label = {Text(stringResource(R.string.text_user_name),
            style = MaterialTheme.typography.Medium14)},
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

        // Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(R.string.text_email),
            style = MaterialTheme.typography.Medium14) },
            isError = showEmailError,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = MaterialTheme.typography.Medium14,
            shape = RoundedCornerShape(4.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = if (showEmailError) ColorError else ColorBorder,
                focusedBorderColor = if (showEmailError) ColorError else ColorBorder,
                unfocusedContainerColor = ColorSurfaceVariant,
                focusedContainerColor = White
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.text_password), style = MaterialTheme.typography.Medium14) },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(
                    onClick = { passwordVisible = !passwordVisible },
                    modifier = Modifier.size(20.dp)
                ) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = if (passwordVisible) stringResource(R.string.text_hide_password) else stringResource(R.string.text_show_password),
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

        // Sign Up Button
        Button(
            onClick = {
                // Validate email on submit
                if (!viewModel.validateEmail(email.trim())) {
                    showEmailError = true
                    return@Button
                } else {
                    showEmailError = false
                    viewModel.signUp(email=email.trim(), password =  password.trim(), username =  username.trim())
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && uiState !is AuthUiState.Loading,
            colors = ButtonDefaults.buttonColors(
                containerColor = ColorPrimary,
                disabledContainerColor = ColorPrimaryDisabled
            ),
            shape = RoundedCornerShape(4.dp)
        ) {

            if (uiState is AuthUiState.Loading) {
                CircularProgressIndicator(
                    color = White,
                    modifier = Modifier.size(16.dp),
                    strokeWidth = 2.dp
                )
            } else {
                Text(stringResource(R.string.text_sign_up), style = MaterialTheme.typography.SemiBold14, color = White)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Divider
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(ColorDivider)
        )

        Spacer(modifier = Modifier.height(20.dp))


        // Navigate to Login
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(R.string.text_already_have_account),
                style = MaterialTheme.typography.Normal12,
                color = ColorTextSecondary
            )
            TextButton(onClick = { onNavigation(SignUpNavigationEvent.OnNavigateToLogin) }) {
                Text(
                    stringResource(R.string.text_login),
                    style = MaterialTheme.typography.SemiBold12,
                    color = ColorLink
                )
            }
        }
// Error Message
        if (uiState is AuthUiState.Error) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                (uiState as AuthUiState.Error).message,
                color = ColorError,
                style = MaterialTheme.typography.Normal12,
                modifier = Modifier.padding(16.dp)
            )
        }

        // Success Navigation
        if (uiState is AuthUiState.Success) {
            LaunchedEffect(Unit) { onNavigation(SignUpNavigationEvent.OnSignUpSuccess) }
        }

    }

}