package com.nj.space.ui.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nj.social.space.R
import com.nj.space.core.TopBar
import com.nj.space.core.UiLoader

@Composable
fun ProfileScreen(viewModel: ProfileViewModel= hiltViewModel(),onNavigation:(ProfileNavigationEvent)-> Unit)
{

    val  uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        TopBar(
            title = stringResource(id = R.string.text_profile),
            showLeftIcon = true,
            leftOnClick = {
                onNavigation(ProfileNavigationEvent.NavigateBack)
            },
            rightOnClick = {

            }
        )
    }, content = {innerPadding->
        UiLoader(uiState)
        {
            Screen(Modifier.padding(innerPadding))
        }

    })

}
@Composable
fun Screen(modifier: Modifier)
{

}