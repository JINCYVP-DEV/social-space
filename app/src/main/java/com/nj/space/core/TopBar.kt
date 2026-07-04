package com.nj.space.core

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.nj.social.space.R

@Composable
fun TopBar(title:String,showLeftIcon: Boolean=true,showRightIcon: Boolean=false,
           rightIcon: ImageVector=Icons.Default.Notifications,leftOnClick:()-> Unit,rightOnClick:()-> Unit)
{
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(dimensionResource( R.dimen.margin_medium)),
      ) {

        if (showLeftIcon){
        Icon(
            imageVector = Icons.Default.ArrowBackIosNew,
            contentDescription ="left",
            modifier = Modifier.size(dimensionResource(R.dimen.icon_size_medium))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        leftOnClick()
                    }
            )
        ) }
        Text(text = title,  modifier = Modifier.weight(1f)
            .padding(start = dimensionResource(R.dimen.margin_regular)),style = MaterialTheme.typography.titleLarge)
        if (showRightIcon){
        Icon(
            imageVector = rightIcon,
            contentDescription ="right",
            modifier =  Modifier.size(dimensionResource(R.dimen.icon_size_medium))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        rightOnClick()
                    }
            )
        )}
    }
}