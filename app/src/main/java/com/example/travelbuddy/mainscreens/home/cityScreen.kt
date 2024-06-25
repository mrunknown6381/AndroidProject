package com.example.travelbuddy.mainscreens.home

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.travelbuddy.R
import com.example.travelbuddy.data.placeRepo
import com.example.travelbuddy.mainscreens.wishlist.userFav
import com.example.travelbuddy.ui.theme.pure
import com.example.travelbuddy.ui.theme.text
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun detailsScreenCity(id: Int,onNavigateUp: () -> Unit,onRClick:(id:Int) -> Unit, onPlaceClick:(id:Int) -> Unit,onHClick:(id:Int) -> Unit) {
    val placeData = placeRepo.first { it.id == id }
    val link = LocalUriHandler.current
    val minimumLineLength = 3
    val userFav: userFav = viewModel()
    //Adding States
    var expandedState by remember { mutableStateOf(false) }
    var showReadMoreButtonState by remember { mutableStateOf(false) }
    val maxLines = if (expandedState) 200 else minimumLineLength
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .nestedScroll(scrollBehavior.nestedScrollConnection)
        , topBar = {
            CenterAlignedTopAppBar(scrollBehavior = scrollBehavior,title = {
                Text(text = "")
            },colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.text,
            ), navigationIcon = {
                IconButton(onClick = onNavigateUp) {

                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null, tint = MaterialTheme.colorScheme.pure, modifier = Modifier)
//                        .clip(
//                            CircleShape
//                        )
//                        .background(Color.Black)
//                        .size(100.dp))


                }
            }, actions = {
                //like,dislike
                IconButton(onClick = {link.openUri(placeData.location)}) {

                        Icon(Icons.Default.LocationOn, contentDescription = null, tint = MaterialTheme.colorScheme.pure
                        )

                }
                IconButton(onClick = {link.openUri(placeData.location)}) {

                    Icon(Icons.Default.Favorite, contentDescription = null, tint = if(placeData.fav.value == true) Color.Red else MaterialTheme.colorScheme.text,
                        modifier = Modifier.clickable {
                            placeData.fav.value = !placeData.fav.value
                        }
                    )

                }
            })
        }){
//        padding ->
        LazyColumn(modifier = Modifier
//            .padding(padding)
        ) {
            val imgOfPlace = listOf(
                placeData.picture[0],
                placeData.picture[1],
                placeData.picture[2]
            )
            val pname = placeData.title
            val city = placeData.city
//            Spacer(modifier = Modifier.height(32.dp))
//            41 for pixel
            item {
                imgsliderwithindicatorCity(img = imgOfPlace,pname,city)
                Column (modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)){
                    Spacer(modifier = Modifier.height(7.dp))
                    Column (modifier = Modifier
                        .fillMaxSize()
                        ){
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "About Destination", color = MaterialTheme.colorScheme.text, fontSize = 18.sp, fontWeight = FontWeight.Medium)
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                    Column(modifier = Modifier) {
                        Text(
                            text = placeData.disc,
                            fontSize = 15.sp,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = maxLines,
                            onTextLayout = { textLayoutResult: TextLayoutResult ->
                                if (textLayoutResult.lineCount > minimumLineLength-1) {
                                    if (textLayoutResult.isLineEllipsized(minimumLineLength-1)) showReadMoreButtonState = true
                                }
                            }
                        )
                        if (showReadMoreButtonState) {
                            Text(
                                text = if (expandedState) "Read Less" else "Read More",
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold,

                                modifier = Modifier.clickable {
                                    expandedState = !expandedState
                                },

                                style = MaterialTheme.typography.bodySmall

                            )
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)){
                    Text(text = "Best Place to Vist", color = MaterialTheme.colorScheme.text, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                }
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                var cat = arrayListOf("Beach","Hill Station","Heritage","Spiritual","Honeymoon","Zoo")
                var filter = placeRepo.filter { placeData.city == it.city }
                filter = filter.filter { it.category.contains("Beach") || it.category.contains("Hill Station") || it.category.contains("Heritage") || it.category.contains("Spiritual") || it.category.contains("Honeymoon") || it.category.contains("Zoo")}
                LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)){
                    item { Spacer(modifier = Modifier.width(0.01.dp)) }
                    items(filter){ items ->
//                    placeCard(items , onClick = {onPlaceClick(items.title)})
                        placeCard(items , onClick = {onPlaceClick(items.id)} , onFav = {})
                    }
                    item {
                        Spacer(modifier = Modifier.width(15.dp))
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)){
                    Text(text = "Hotels nearby", color = MaterialTheme.colorScheme.text, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                val filter = if(placeData.city.contains("Surat")){
                    placeRepo.filter { it.category.contains("Hotel") && it.city.contains("Surat") }
                }else if(placeData.city.contains("Ahmedabad")){
                    placeRepo.filter { it.category.contains("Hotel") && it.city.contains("Ahmedabad") }
                }else{
                    placeRepo.filter { it.category.contains("Hotel") }
                }
                LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)){
                    item { Spacer(modifier = Modifier.width(0.01.dp)) }
                    items(filter){ items ->
//                    placeCard(items , onClick = {onPlaceClick(items.title)})
                        placeCard(items , onClick = {onHClick(items.id)}, onFav = {})
                    }
                    item {
                        Spacer(modifier = Modifier.width(15.dp))
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)){
                    Text(text = "Restaurant nearby", color = MaterialTheme.colorScheme.text, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                val filter = if(placeData.city.contains("Surat")){
                    placeRepo.filter { it.category.contains("Restaurant") && it.city.contains("Surat") }
                }else if(placeData.city.contains("Ahmedabad")){
                    placeRepo.filter { it.category.contains("Restaurant") && it.city.contains("Ahmedabad") }
                }else{
                    placeRepo.filter { it.category.contains("Restaurant") }
                }
                LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)){
                    item { Spacer(modifier = Modifier.width(0.01.dp)) }
                    items(filter){ items ->
//                    placeCard(items , onClick = {onPlaceClick(items.title)})
                        placeCard(items , onClick = {onRClick(items.id)}, onFav = {})
                    }
                    item {
                        Spacer(modifier = Modifier.width(15.dp))
                    }
                }
            }

        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
        }
    }
}

@Composable
fun ImageSliderImgCity(imgRes:String) {
//    Image(painter = painterResource(id = imgRes), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier
//        .fillMaxWidth()
//        .height(200.dp))
Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
    Spacer(modifier = Modifier.height(95.dp))
    Image(painter = rememberAsyncImagePainter(model = imgRes), contentDescription = null, modifier = Modifier
        .height(300.dp)
        .width(400.dp)
        .background(color = Color.Gray)
        , contentScale = ContentScale.Crop)
}



}

@Composable
fun indicatorCity(active:Boolean) {
    val color = if (active) Color.White else Color.Transparent.copy(0.6f)
    val size = if (active) 7.dp else 7.dp
    val Rcs = if(active) 30.dp else 50.dp
    val Wi = animateDpAsState(targetValue = if (active) 20.dp else 10.dp)
    Box (modifier = Modifier
        .width(Wi.value)
        .clip(RoundedCornerShape(Rcs))
        .background(color)
        .size(size), contentAlignment = Alignment.Center){

    }
}

@Composable
fun imgsliderwithindicatorCity(img:List<String>,pname:String,city:String) {
    val currentIndex = remember {
        mutableStateOf(0)
    }
    LaunchedEffect(Unit){
        while (true){
            delay(3000)
            currentIndex.value = (currentIndex.value+1) % img.size
        }

    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Box (modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
//            .padding(top = 10.dp)
//            .weight(1f)
        ){
//            Spacer(modifier = Modifier.height(50.dp))
            ImageSliderImgCity(imgRes = img[currentIndex.value])
//          188,  210 pixel
            Row (modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .padding(end = 30.dp, bottom = 27.dp)
//                .padding(top = 258.dp, end = 10.dp)
//                .fillMaxWidth()
            ){

//                Text(text = pname+", "+city)
                img.forEachIndexed { index, i ->
                    indicatorCity(active = index == currentIndex.value)
                    if (index < img.size - 1 ){
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                }
            }
            Row(modifier = Modifier
                .padding(start = 16.dp, top = 350.dp)
            ) {
                Text(text = city, fontWeight = FontWeight.Bold, color = Color.White, fontSize = 22.sp)
            }
        }

    }
}