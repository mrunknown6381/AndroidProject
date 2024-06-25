package com.example.travelbuddy.mainscreens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.travelbuddy.data.Places
import com.example.travelbuddy.data.placeRepo
import com.example.travelbuddy.ui.theme.Cardcontainer

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun categoryScreen(navController: NavHostController, cat: String, onNavigateUp: () -> Unit,onPlaceClick:(id:Int) -> Unit) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .nestedScroll(scrollBehavior.nestedScrollConnection)
        , topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = cat)
            },colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ), navigationIcon = {
                IconButton(onClick = onNavigateUp) {

                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null, modifier = Modifier)
//                        .clip(
//                            CircleShape
//                        )
//                        .background(Color.Black)
//                        .size(100.dp))


                }
            }, scrollBehavior = scrollBehavior, actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.FilterList, contentDescription = null)
                }
            })
        }){
        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
//            .padding(horizontal = 16.dp)
        ) {
            item { 
                Spacer(modifier = Modifier.height(95.dp))
            }
            if(cat == "City"){
                items(items =  placeRepo.filter { it.category.contains("City") }, key = {
                    it.id
                }){ items ->
//                    placeCard(items , onClick = {onPlaceClick(items.title)})
                    placeCard2(items , onClick = {onPlaceClick(items.id)})
                }
            }else{
                items(items =  placeRepo.filter { !it.category.contains("City") && !it.category.contains("Hotel") && !it.category.contains("Restaurant") && !it.category.contains("Cuisine") && !it.category.contains("Dishes") && it.category.contains(cat) }, key = {
                    it.id
                }){ items ->
//                    placeCard(items , onClick = {onPlaceClick(items.title)})
                    placeCard2(items , onClick = {onPlaceClick(items.id)})
                }
            }


        }
    }
}



@Composable
fun placeCard2(items: Places, onClick:() -> Unit) {
    var selected by rememberSaveable {
        mutableStateOf(false)
    }
    Card (modifier = Modifier
//        .padding(8.dp)
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 10.dp)
        , shape = RoundedCornerShape(0.dp), colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),onClick = onClick
    ){
        Row(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier
                .padding(8.dp)
                .fillMaxHeight()
                .fillMaxWidth(0.3f)) {
                Image(modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxSize()
                    .background(Color.Gray)
                    .size(100.dp)
                    , contentScale = ContentScale.Crop,painter = rememberAsyncImagePainter(model = items.picture.get(0)), contentDescription = null)
            }
            Column(modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .align(Alignment.CenterVertically)) {
                Row {
                    Text(text = items.title, fontWeight = FontWeight.Bold, fontSize = 17.sp)
                }
                Spacer(modifier = Modifier.height(7.dp))
                Row (horizontalArrangement = Arrangement.spacedBy(2.dp)){
                    if(items.rating < 1){
                        Icon(Icons.AutoMirrored.Filled.StarHalf, contentDescription = null)
                        Icon(Icons.Filled.StarOutline, contentDescription = null)
                        Icon(Icons.Filled.StarOutline, contentDescription = null)
                        Icon(Icons.Filled.StarOutline, contentDescription = null)
                        Icon(Icons.Filled.StarOutline, contentDescription = null)
                    }
                    else if(items.rating >=1 && items.rating < 2){
                        Icon(Icons.Filled.Star, contentDescription = null)
                        Icon(Icons.Filled.StarOutline, contentDescription = null)
                        Icon(Icons.Filled.StarOutline, contentDescription = null)
                        Icon(Icons.Filled.StarOutline, contentDescription = null)
                        Icon(Icons.Filled.StarOutline, contentDescription = null)
                    }
                    else if(items.rating >=2 && items.rating < 2.5){
                        Icon(Icons.Filled.Star, contentDescription = null)
                        Icon(Icons.Filled.Star, contentDescription = null)
                        Icon(Icons.Filled.StarOutline, contentDescription = null)
                        Icon(Icons.Filled.StarOutline, contentDescription = null)
                        Icon(Icons.Filled.StarOutline, contentDescription = null)
                    }
                    else if(items.rating >=2.5 && items.rating < 3){
                        Icon(Icons.Filled.Star, contentDescription = null)
                        Icon(Icons.Filled.Star, contentDescription = null)
                        Icon(Icons.AutoMirrored.Filled.StarHalf, contentDescription = null)
                        Icon(Icons.Filled.StarOutline, contentDescription = null)
                        Icon(Icons.Filled.StarOutline, contentDescription = null)
                    }
                    else if(items.rating >=3 && items.rating < 3.5){
                        Icon(Icons.Filled.Star, contentDescription = null)
                        Icon(Icons.Filled.Star, contentDescription = null)
                        Icon(Icons.Filled.Star, contentDescription = null)
                        Icon(Icons.Filled.StarOutline, contentDescription = null)
                        Icon(Icons.Filled.StarOutline, contentDescription = null)
                    }
                    else if(items.rating >=3.5 && items.rating < 4){
                        Icon(Icons.Filled.Star, contentDescription = null)
                        Icon(Icons.Filled.Star, contentDescription = null)
                        Icon(Icons.Filled.Star, contentDescription = null)
                        Icon(Icons.AutoMirrored.Filled.StarHalf, contentDescription = null)
                        Icon(Icons.Filled.StarOutline, contentDescription = null)

                    }
                    else if(items.rating >=4 && items.rating < 4.5){
                        Icon(Icons.Filled.Star, contentDescription = null)
                        Icon(Icons.Filled.Star, contentDescription = null)
                        Icon(Icons.Filled.Star, contentDescription = null)
                        Icon(Icons.Filled.Star, contentDescription = null)
                        Icon(Icons.Filled.StarOutline, contentDescription = null)
                    }
                    else if(items.rating >=4.5 && items.rating < 5){
                        Icon(Icons.Filled.Star, contentDescription = null)
                        Icon(Icons.Filled.Star, contentDescription = null)
                        Icon(Icons.Filled.Star, contentDescription = null)
                        Icon(Icons.Filled.Star, contentDescription = null)
                        Icon(Icons.AutoMirrored.Filled.StarHalf, contentDescription = null)
                    }
                    else if( items.rating == 5.0){
                        Icon(Icons.Filled.Star, contentDescription = null)
                        Icon(Icons.Filled.Star, contentDescription = null)
                        Icon(Icons.Filled.Star, contentDescription = null)
                        Icon(Icons.Filled.Star, contentDescription = null)
                        Icon(Icons.Filled.Star, contentDescription = null)
                    }
                    Spacer(modifier = Modifier.width(3.dp))
                    Text("("+items.rating+")", fontWeight = FontWeight.Medium)

                }
                Spacer(modifier = Modifier.height(7.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Icon(Icons.Filled.LocationOn, contentDescription = null,modifier = Modifier.size(15.dp))
                    Spacer(modifier = Modifier.width(7.dp))
                    Text(text = items.city, fontWeight = FontWeight.Medium)
                }


            }
        }


    }
    HorizontalDivider(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp), thickness = 0.2.dp, color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray)

}