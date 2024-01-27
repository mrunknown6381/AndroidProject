package com.example.travelbuddy.appcomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.travelbuddy.data.dimens.indicatorsize

@Composable
fun Pageindicator(modifier: Modifier,
                  pageSize: Int,
                  selectedPage: Int,
                  selectedColor: Color = MaterialTheme.colorScheme.surfaceTint,
                  unselectedColor: Color = Color.LightGray
                  ) {
    
    Row(modifier = Modifier.width(50.dp), horizontalArrangement = Arrangement.SpaceAround) {
        repeat(pageSize){page ->
            Box(modifier = Modifier
                .size(indicatorsize)
                .clip(CircleShape)
                .width(15.dp)
                .background(color = if (page == selectedPage) selectedColor else unselectedColor))

        }
    }
}
