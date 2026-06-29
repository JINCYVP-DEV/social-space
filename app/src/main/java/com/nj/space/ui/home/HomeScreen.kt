package com.nj.space.ui.home

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen( viewModel: HomeViewModel = hiltViewModel(),
                onNavigation:(HomeViewModel.HomeScreenNavigation)-> Unit) {
    Scaffold(topBar = {
        TopBar()

    }){
        Screen()
    }


}
@Composable
fun Screen()
{

}