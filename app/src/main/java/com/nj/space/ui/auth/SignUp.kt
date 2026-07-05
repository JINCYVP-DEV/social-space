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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nj.space.ui.theme.ColorError
import com.nj.space.ui.theme.ColorPrimary
import com.nj.space.ui.theme.Medium14
import com.nj.space.ui.theme.Normal12
import com.nj.space.ui.theme.SemiBold12
import com.nj.space.ui.theme.SemiBold14
import com.nj.social.space.R
import com.nj.space.core.AuthButton
import com.nj.space.core.PasswordOutlinedTextField
import com.nj.space.core.SocialSpaceOutlinedTextField
import com.nj.space.ui.theme.ColorDivider
import com.nj.space.ui.theme.Focused
import com.nj.space.ui.theme.Normal20
import com.nj.space.ui.theme.Orange0
import com.nj.space.ui.theme.Orange1
import com.nj.space.ui.theme.Orange2
import com.nj.space.ui.theme.TextWhite
import com.nj.space.ui.theme.TopWhite

sealed class SignUpNavigationEvent {
    object LoggedOut : SignUpNavigationEvent()

    object OnNavigateToLogin : SignUpNavigationEvent()

    object OnSignUpSuccess : SignUpNavigationEvent()
}
@Composable
fun SignUp(viewModel: AuthViewModel = hiltViewModel(),onNavigation:(SignUpNavigationEvent)->Unit)
{
    val username = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordVisible = remember { mutableStateOf(false) }
    var showEmailError by remember { mutableStateOf(false) }
    val uiState by  viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.app_description),
            style = MaterialTheme.typography.Normal20,
            fontWeight = FontWeight.Light,
            color = Color.Black,
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.margin_extra_large))
        )
        SocialSpaceOutlinedTextField(username,stringResource(R.string.text_user_name))

        Spacer(modifier = Modifier.height(10.dp))
        SocialSpaceOutlinedTextField(email,stringResource(R.string.text_email))
        Spacer(modifier = Modifier.height(20.dp))
        PasswordOutlinedTextField(password,passwordVisible)
        Spacer(modifier = Modifier.height(20.dp))

        // Sign Up Button

        AuthButton(
            onClick = {
                // Validate email on submit
                if (!viewModel.validateEmail(email.value.trim())) {
                    showEmailError = true
                } else {
                    showEmailError = false
                    viewModel.signUp(email=email.value.trim(), password =  password.value.trim(), username =  username.value.trim())
                }
            },
            enabled =  username.value.isNotEmpty() && email.value.isNotEmpty() && password.value.isNotEmpty(),
            uiState = uiState
        )

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
                color = Color.Black
            )
            TextButton(onClick = { onNavigation(SignUpNavigationEvent.OnNavigateToLogin) }) {
                Text(
                    stringResource(R.string.text_login),
                    style = MaterialTheme.typography.SemiBold12,
                    color = Focused
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