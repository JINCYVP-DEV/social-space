package com.nj.space.core

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nj.space.ui.theme.ColorBackground
import com.nj.space.ui.theme.ColorBorder
import com.nj.space.ui.theme.ColorSurfaceVariant
import com.nj.space.ui.theme.Medium14

@Composable
fun SocialSpaceOutlinedTextField(state:MutableState<String>, label:String)
{
    OutlinedTextField(
        value = state.value,
        onValueChange = { state.value = it },
        label = {
            Text(
                text = label,
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
}