package com.nj.space.core

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nj.social.space.R
import com.nj.space.ui.auth.AuthUiState
import com.nj.space.ui.theme.Focused
import com.nj.space.ui.theme.SemiBold14
import com.nj.space.ui.theme.TextWhite
import com.nj.space.ui.theme.Unfocused

@Composable
fun AuthButton(onClick:()-> Unit, enabled: Boolean=false,uiState: AuthUiState)
{
    Button(
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth(),
        enabled =enabled && uiState !is AuthUiState.Loading,
        colors = ButtonDefaults.buttonColors(
            containerColor = Focused,
            disabledContainerColor =Unfocused
        ),
        shape = RoundedCornerShape(4.dp)
    ) {
        if (uiState is AuthUiState.Loading) {
            CircularProgressIndicator(
                color = Focused,
                modifier = Modifier.size(16.dp),
                strokeWidth = 2.dp
            )
        } else {
            Text(text = stringResource(R.string.text_login),
                style = MaterialTheme.typography.SemiBold14,
                color = TextWhite
            )
        }
    }
}