package com.nj.space.ui.add

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.nj.social.space.R
import com.nj.space.core.TopBar
import com.nj.space.core.UploadState
import com.nj.space.core.UploadUiLoader


@Composable
fun AddPostScreen(viewModel: ReelsViewModel= hiltViewModel(),onNavigation:(AddPostNavigationEvent)-> Unit)
{
    var hasPermission  by rememberSaveable { mutableStateOf(false) }
    val  uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var selectedUri by remember { mutableStateOf<Uri?>(null) }


    val permission =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            Manifest.permission.READ_MEDIA_IMAGES
        else
            Manifest.permission.READ_EXTERNAL_STORAGE

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {granted->
        hasPermission = granted
    }
    val launcherImg = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri -> selectedUri = uri }

    LaunchedEffect(Unit) {
        launcher.launch(permission)
    }
    LaunchedEffect(hasPermission) {
        if (hasPermission)
        {
            launcherImg.launch("image/*")
        }
    }

    if (!hasPermission) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(stringResource(R.string.text_give_permission))
        }
        return
    }
    Scaffold(topBar = {
        TopBar(
            title = stringResource(id = R.string.text_add_photo),
            showLeftIcon = true,
            leftOnClick = {
                onNavigation(AddPostNavigationEvent.NavigateBack)
            },
            rightOnClick = {

            }
        )
    }, content = {innerPadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            selectedUri?.let {
                AsyncImage(
                    model = it, contentDescription = null,
                    modifier = Modifier.fillMaxWidth().height(200.dp).clickable(onClick = {
                        launcherImg.launch("image/*")
                    })
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            } ?: Box(
                modifier = Modifier.fillMaxWidth().height(200.dp)
                    .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(12.dp))
                    .clickable { launcher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                Text("Tap to pick a photo", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            Spacer(modifier = Modifier.height(16.dp))

            UploadUiLoader(uiState)
            {
            }

            Button(
                onClick = { selectedUri?.let { viewModel.uploadPhoto(it, "userId") } },
                enabled = selectedUri != null && uiState !is UploadState.Loading,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Share")
            }
        }
    })


}
