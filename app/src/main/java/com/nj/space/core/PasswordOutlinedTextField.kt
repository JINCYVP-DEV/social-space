package com.nj.space.core

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.nj.social.space.R
import com.nj.space.ui.theme.ColorBackground
import com.nj.space.ui.theme.ColorBorder
import com.nj.space.ui.theme.ColorSurfaceVariant
import com.nj.space.ui.theme.Focused
import com.nj.space.ui.theme.Medium14
import com.nj.space.ui.theme.TextWhite

@Composable
fun PasswordOutlinedTextField(state:MutableState<String>, passwordVisible :MutableState<Boolean>)
{
    OutlinedTextField(
        value = state.value,
        onValueChange = { state.value = it },
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
                    tint = Focused,
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
}