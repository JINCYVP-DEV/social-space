package com.nj.space.ui.home

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.nj.social.space.R
import androidx.hilt.navigation.compose.hiltViewModel
import com.nj.space.core.TopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen( viewModel: HomeViewModel = hiltViewModel(),
                onNavigation:(HomeViewModel.HomeScreenNavigation)-> Unit) {
    Scaffold(topBar = {
        TopBar(
            title = stringResource(id = R.string.app_name),
            showLeftIcon = false,
            showRightIcon = true,
            leftOnClick = {

            },
            rightOnClick = {

            }
        )
    }){
        Screen()
    }


}
@Composable
fun Screen()
{

}