package com.nj.space.ui.search

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
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel(),onNavigation:(SearchNavigationEvent)-> Unit)
{

    val  uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        TopBar(
            title = stringResource(id = R.string.text_search),
            showLeftIcon = true,
            leftOnClick = {
                onNavigation(SearchNavigationEvent.NavigateBack)
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