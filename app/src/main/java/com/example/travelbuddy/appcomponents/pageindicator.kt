package com.example.travelbuddy.appcomponents

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

//function that holds horizontal pager indicator are used in onboarding screen
@Composable
fun Pageindicator(modifier: Modifier,
                  pageSize: Int,
                  selectedPage: Int,
                  selectedColor: Color = MaterialTheme.colorScheme.surfaceTint,
                  unselectedColor: Color = Color.LightGray
                  ) {
    
    Row(modifier = Modifier.width(60.dp), horizontalArrangement = Arrangement.SpaceAround) {
        repeat(pageSize){page ->
            val size = if (page == selectedPage) 10.dp else 10.dp
            val Rcs = if(page == selectedPage) 30.dp else 50.dp
            val Wi = animateDpAsState(targetValue = if (page == selectedPage) 20.dp else 10.dp)
            Box(modifier = Modifier.width(Wi.value)
                .size(size)
                .clip(RoundedCornerShape(Rcs))
                .background(color = if (page == selectedPage) selectedColor else unselectedColor))

        }
    }
}
