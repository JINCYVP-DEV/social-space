package com.nj.space.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nj.space.R

sealed class SettingsScreenNavigationEvents{
    object NavigateToLogin: SettingsScreenNavigationEvents()
    object OnBack: SettingsScreenNavigationEvents()
}
data class SettingOption(
    val title: String,
    val onClick: () -> Unit
)
@Composable
fun SettingsScreen(authViewModel: AuthViewModel= hiltViewModel(),onNavigation:(SettingsScreenNavigationEvents)->Unit)
{
    var showLogoutDialog by remember { mutableStateOf(false) }

    //observe authViewmodel state
    LaunchedEffect(authViewModel.authEvents) {
        authViewModel.authEvents.collect { event ->
            if (event is AuthViewModel.AuthEvent.LoggedOut) {
                onNavigation(SettingsScreenNavigationEvents.NavigateToLogin)
            }
        }
    }

        // ✅ Dynamic Settings List
        val settingsOptionsList = listOf(
            SettingOption(stringResource(R.string.text_logout), {
                showLogoutDialog = true
            }),
            SettingOption(stringResource(R.string.text_edit_profile)) { /* TODO: Navigate to Edit Profile */ },
            SettingOption(stringResource(R.string.text_notifications)) { /* TODO: Open Notification Settings */ },
            SettingOption(stringResource(R.string.text_privacy)) { /* TODO: Open Privacy Screen */ },
            SettingOption(stringResource(R.string.text_help_support)) { /* TODO: Open Help Screen */ }
        )

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                //  Top App Bar
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    IconButton(onClick = { onNavigation(SettingsScreenNavigationEvents.OnBack) }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = stringResource(R.string.text_back)
                        )
                    }
                    Text(
                        text = stringResource(R.string.text_settings),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                
                settingsOptionsList.forEachIndexed { index, item ->
                    SettingsItem(title = item.title, onClick = item.onClick)
                    if (index != settingsOptionsList.lastIndex) {
                        HorizontalDivider(
                            modifier = Modifier,
                            thickness = DividerDefaults.Thickness,
                            color = DividerDefaults.color
                        )
                    }
                }

            }

            if (showLogoutDialog) {
                LogoutDialog(
                    onConfirm = {
                        showLogoutDialog = false
                        authViewModel.logout()
                    },
                    onDismiss = { showLogoutDialog = false }
                )
            }
        }
    }

    @Composable
    fun SettingsItem(title: String, onClick: () -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(vertical = 14.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
