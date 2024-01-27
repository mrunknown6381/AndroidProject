package com.example.travelbuddy.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.travelbuddy.data.Page
import com.example.travelbuddy.data.pages
import com.example.travelbuddy.data.dimens.MP
import com.example.travelbuddy.data.dimens.MP1
import com.example.travelbuddy.ui.theme.TravelBuddyTheme

@Composable
fun onboardingpage(modifier: Modifier, page: Page) {
    Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
        Image(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f),painter = painterResource(id = page.img), contentDescription = null, contentScale = ContentScale.Crop, alignment = Alignment.Center)
        Spacer(modifier = Modifier.height(MP))
        Text(text = page.title, modifier = Modifier.padding(horizontal = MP1), style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(25.dp))
        Text(text = page.desc, modifier = Modifier.padding(horizontal = MP1), style = MaterialTheme.typography.displaySmall.copy(fontStyle = FontStyle.Italic),
            color = MaterialTheme.colorScheme.primary)
    }
}
@Preview(showBackground = true)

@Composable
fun obp() {
    TravelBuddyTheme {
        onboardingpage(modifier = Modifier, page = pages[0])
    }
}