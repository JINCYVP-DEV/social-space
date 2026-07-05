package com.nj.space.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun UploadUiLoader(uiState: UploadState, content: @Composable ()-> Unit)
{
    when(uiState)
    {
        is UploadState.Idle->{

        }
        is UploadState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = (uiState as UiState.Error).message,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center
                )
            }
        }
       is  UploadState.Loading -> {
//           LinearProgressIndicator(
//               progress = { uiState.progress / 100f },
//               modifier = Modifier.fillMaxWidth()
//           )
//           Text("${uiState.progress}%", fontSize = 12.sp,
//               color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        is UploadState.Success -> {
            content()
            Text("✓ Uploaded", color = MaterialTheme.colorScheme.primary)
            Text(uiState.url, fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }

}