package com.example.travelbuddy.mainscreens.home

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.travelbuddy.R
import com.example.travelbuddy.data.placeRepo
import com.example.travelbuddy.mainscreens.wishlist.userFav
import com.example.travelbuddy.ui.theme.Cardcontainer
import com.example.travelbuddy.ui.theme.pure
import com.example.travelbuddy.ui.theme.text
import kotlinx.coroutines.delay
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun detailsScreen(navController: NavHostController, id: Int, onNavigateUp: () -> Unit, onHClick:(id:Int) -> Unit, onRClick:(id:Int) -> Unit, onPlaceClick:(id:Int) -> Unit) {
    val placeData = placeRepo.first { it.id == id }
    val link = LocalUriHandler.current
    val minimumLineLength = 3
    var expandedState by remember { mutableStateOf(false) }
    var showReadMoreButtonState by remember { mutableStateOf(false) }
    var expandedState2 by remember { mutableStateOf(false) }
    var showReadMoreButtonState2 by remember { mutableStateOf(false) }
    var expandedState3 by remember { mutableStateOf(false) }
    var showReadMoreButtonState3 by remember { mutableStateOf(false) }
    var expandedState4 by remember { mutableStateOf(false) }
    var showReadMoreButtonState4 by remember { mutableStateOf(false) }
    val maxLines = if (expandedState) 200 else minimumLineLength
    val maxLines2 = if (expandedState2) 200 else minimumLineLength
    val maxLines3 = if (expandedState3) 200 else minimumLineLength
    val maxLines4 = if (expandedState4) 200 else minimumLineLength
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val userFav: userFav = viewModel()

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .nestedScroll(scrollBehavior.nestedScrollConnection)
        , topBar = {
        CenterAlignedTopAppBar(scrollBehavior = scrollBehavior,title = {
            Text(text = "")
        },colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ), navigationIcon = {
            IconButton(onClick = onNavigateUp) {
                Box(modifier = Modifier
                    .clip(CircleShape)
                    .size(100.dp)
                    .background(color = Color.Transparent.copy(0.4f)), contentAlignment = Alignment.Center) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null, tint = Color(0xFFFFFFFF), modifier = Modifier)
//                        .clip(
//                            CircleShape
//                        )
//                        .background(Color.Black)
//                        .size(100.dp))
                }

            }
        }, actions = {
            IconButton(onClick = {link.openUri(placeData.location)}) {
                Box(modifier = Modifier
                    .clip(CircleShape)
                    .size(100.dp)
                    .background(color = Color.Transparent.copy(0.4f)), contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.White
                    )
                }
            }
            //like,dislike
            IconButton(onClick = {link.openUri(placeData.location)}) {
                Box(modifier = Modifier
                    .clip(CircleShape)
                    .size(100.dp)
                    .background(color = Color.Transparent.copy(0.4f)), contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Favorite, contentDescription = null, tint = if(placeData.fav.value == true) Color.Red else Color.White,
                        modifier = Modifier.clickable {
                            placeData.fav.value = !placeData.fav.value
                        }
                    )
                }
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
                imgsliderwithindicator(img = imgOfPlace,pname,city)
                Spacer(modifier = Modifier.height(10.dp))
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp), verticalAlignment = Alignment.CenterVertically){
                    Row (modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(10.dp), horizontalArrangement = Arrangement.Start){
                        Box(modifier = Modifier
                            .background(
                                if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(10.dp)
                            .clip(RoundedCornerShape(20.dp)), contentAlignment = Alignment.Center){
                            Icon(Icons.Filled.AccessTimeFilled, contentDescription = null, tint = MaterialTheme.colorScheme.pure,modifier = Modifier
                                .size(30.dp)
                                ,)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column (horizontalAlignment = Alignment.CenterHorizontally){
                            Text(text = "Duration", fontWeight = FontWeight.Bold)
                            Text(text = "${placeData.duration}"+" Hours", fontSize = 14.sp)
                        }
                    }
                    Row (modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp), horizontalArrangement = Arrangement.Center){
                        Box(modifier = Modifier
                            .background(
                                if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(10.dp)
                            .clip(RoundedCornerShape(20.dp)), contentAlignment = Alignment.Center){
                            Icon(Icons.Filled.Star, contentDescription = null, tint = Color.Yellow,modifier = Modifier
                                .size(30.dp)
                                ,)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Ratings", fontWeight = FontWeight.Bold)
                            Text(text = "${placeData.rating}")
                        }
                    }

                }
                Column (modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)){
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "About Destination", color = MaterialTheme.colorScheme.text, fontSize = 18.sp, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(5.dp))
                }
                Column(modifier = Modifier.padding(vertical = 0.dp, horizontal = 16.dp)) {
                    Text(
                        text = placeData.disc,
                        fontSize = 15.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = maxLines4,
                        onTextLayout = { textLayoutResult: TextLayoutResult ->
                            if (textLayoutResult.lineCount > minimumLineLength-1) {
                                if (textLayoutResult.isLineEllipsized(minimumLineLength-1)) showReadMoreButtonState4 = true
                            }
                        }
                    )
                    if (showReadMoreButtonState4) {
                        Text(
                            text = if (expandedState4) "Read Less" else "Read More",
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,

                            modifier = Modifier.clickable {
                                expandedState4 = !expandedState4
                            },

                            style = MaterialTheme.typography.bodySmall

                        )
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
                    Text(text = "Attractions nearby", color = MaterialTheme.colorScheme.text, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                }
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                var cat = arrayListOf("Beach","Hill Station","Heritage","Spiritual","Honeymoon","Zoo")
               var filter = if(placeData.city == "Surat" || placeData.city == "Ahmedabad"){
                    placeRepo.filter { placeData.city == it.city && it.title != placeData.title && it.category.contains("Hotel") == false && it.category.contains("Restaurant") == false && it.category.contains("City") == false}
                }else{
                    placeRepo.filter { it.title != placeData.title && it.category.contains("Hotel") == false && it.category.contains("Restaurant") == false && it.category.contains("City") == false}
                }
                LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)){
                    item { Spacer(modifier = Modifier.width(0.01.dp)) }
                    items(filter.take(5)){ items ->
//                    placeCard(items , onClick = {onPlaceClick(items.title)})
                        placeCard(items , onClick = {
                            navController.popBackStack()
                            onPlaceClick(items.id)
                        }, onFav = {})
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
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)){
                    Text(text = "Hotels nearby", color = MaterialTheme.colorScheme.text, fontSize = 18.sp, fontWeight = FontWeight.Medium)
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
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)){
                    Text(text = "Restaurants nearby", color = MaterialTheme.colorScheme.text,  fontSize = 18.sp, fontWeight = FontWeight.Medium)
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
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)){
                    Text(text = "Reviews", color = MaterialTheme.colorScheme.text, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                }
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                HorizontalDivider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp))
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()) {
                    Row (modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp)){
                        Icon(Icons.Filled.Star, contentDescription = null, tint = Color.Yellow)
                        Icon(Icons.Filled.Star, contentDescription = null, tint = Color.Yellow)
                        Icon(Icons.Filled.Star, contentDescription = null, tint = Color.Yellow)
                        Icon(Icons.Filled.Star, contentDescription = null, tint = Color.Yellow)
                        Icon(Icons.Filled.Star, contentDescription = null, tint = Color.Yellow)
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "50k reviews", fontSize = 14.sp)
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Row (modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp)){
                        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                            Text(text = "Excellent", fontSize = 14.sp)
                            Box(modifier = Modifier
                                .height(10.dp)
                                .width(180.dp)
                                .background(Color.Gray, shape = RoundedCornerShape(20.dp))){
                                Column(modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(0.9f)
                                    .background(
                                        MaterialTheme.colorScheme.primary,
                                        shape = RoundedCornerShape(20.dp)
                                    )) {

                                }
                            }
                            Text(text = "20,000", fontSize = 14.sp)
                        }
                    }
                    Row (modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp)){
                        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                            Text(text = "Very good", fontSize = 14.sp)
                            Box(modifier = Modifier
                                .height(10.dp)
                                .width(180.dp)
                                .background(Color.Gray, shape = RoundedCornerShape(20.dp))){
                                Column(modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(0.5f)
                                    .background(
                                        MaterialTheme.colorScheme.primary,
                                        shape = RoundedCornerShape(20.dp)
                                    )) {

                                }
                            }
                            Text(text = "       700", fontSize = 14.sp)
                        }
                    }
                    Row (modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp)){
                        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                            Text(text = "average  ", fontSize = 14.sp)
                            Box(modifier = Modifier
                                .height(10.dp)
                                .width(180.dp)
                                .background(Color.Gray, shape = RoundedCornerShape(20.dp))){
                                Column(modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(0.3f)
                                    .background(
                                        MaterialTheme.colorScheme.primary,
                                        shape = RoundedCornerShape(20.dp)
                                    )) {

                                }
                            }
                            Text(text = "     300", fontSize = 14.sp)
                        }
                    }
                    Row (modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp)){
                        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                            Text(text = "poor     ", fontSize = 14.sp)
                            Box(modifier = Modifier
                                .height(10.dp)
                                .width(180.dp)
                                .background(Color.Gray, shape = RoundedCornerShape(20.dp))){
                                Column(modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(0.15f)
                                    .background(
                                        MaterialTheme.colorScheme.primary,
                                        shape = RoundedCornerShape(20.dp)
                                    )) {

                                }
                            }
                            Text(text = "    50", fontSize = 14.sp)
                        }
                    }
                    Row (modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp)){
                        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                            Text(text = "terrible ", fontSize = 14.sp)
                            Box(modifier = Modifier
                                .height(10.dp)
                                .width(180.dp)
                                .background(Color.Gray, shape = RoundedCornerShape(20.dp))){
                                Column(modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(0.05f)
                                    .background(
                                        MaterialTheme.colorScheme.primary,
                                        shape = RoundedCornerShape(20.dp)
                                    )) {

                                }
                            }
                            Text(text = "     15", fontSize = 14.sp)
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                HorizontalDivider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp))
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp)) {
                    Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                        Column {
                            Image(
                                painter = rememberAsyncImagePainter(model = "https://i.pinimg.com/474x/82/e5/89/82e5892f2ecca5b65929f065f8ae0d43.jpg"), contentDescription = "user",
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape)
                                    .background(Color.Gray)
                                    .clickable { }, contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column (horizontalAlignment = Alignment.CenterHorizontally){
                            Text(text = "Steve")
                            Text(text = "Usa", fontSize = 14.sp, color = Color.Gray)
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row (modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically){
                        Icon(Icons.Filled.Star, contentDescription = null, tint = Color.Yellow)
                        Icon(Icons.Filled.Star, contentDescription = null, tint = Color.Yellow)
                        Icon(Icons.Filled.Star, contentDescription = null, tint = Color.Yellow)
                        Icon(Icons.Filled.Star, contentDescription = null, tint = Color.Yellow)
                        Icon(Icons.Filled.Star, contentDescription = null, tint = Color.Yellow)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                        Text(
                            text = placeData.reviews.get(0),
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
                HorizontalDivider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp))
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp)) {
                    Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                        Column {
                            Image(
                                painter = rememberAsyncImagePainter(model = "https://i.pinimg.com/474x/82/71/01/827101a92c59042a0bc9e83ac43b0c30.jpg"), contentDescription = "user",
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape)
                                    .background(Color.Gray)
                                    .clickable { }, contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column (horizontalAlignment = Alignment.CenterHorizontally){
                            Text(text = "Sara")
                            Text(text = "Uk", fontSize = 14.sp, color = Color.Gray)
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row (modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically){
                        Icon(Icons.Filled.Star, contentDescription = null, tint = Color.Yellow)
                        Icon(Icons.Filled.Star, contentDescription = null, tint = Color.Yellow)
                        Icon(Icons.Filled.Star, contentDescription = null, tint = Color.Yellow)
                        Icon(Icons.Filled.Star, contentDescription = null, tint = Color.Yellow)
                        Icon(Icons.Outlined.StarOutline, contentDescription = null, tint = Color.Yellow)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                        Text(
                            text = placeData.reviews.get(1),
                            fontSize = 15.sp,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = maxLines2,
                            onTextLayout = { textLayoutResult: TextLayoutResult ->
                                if (textLayoutResult.lineCount > minimumLineLength-1) {
                                    if (textLayoutResult.isLineEllipsized(minimumLineLength-1)) showReadMoreButtonState2 = true
                                }
                            }
                        )
                        if (showReadMoreButtonState2) {
                            Text(
                                text = if (expandedState2) "Read Less" else "Read More",
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold,

                                modifier = Modifier.clickable {
                                    expandedState2 = !expandedState2
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
                HorizontalDivider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp))
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp)) {
                    Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
                        Column {
                            Image(
                                painter = rememberAsyncImagePainter(model = "https://play-lh.googleusercontent.com/_eDPmfa9QLBCY8X6czR6GxzRxseLLAoYORVKmlBBnwtxxz18zbkaZJzw_K512b3uSw=w526-h296-rw"), contentDescription = "user",
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape)
                                    .background(Color.Gray)
                                    .clickable { }, contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column (horizontalAlignment = Alignment.CenterHorizontally){
                            Text(text = "William")
                            Text(text = "Nz", fontSize = 14.sp, color = Color.Gray)
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row (modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically){
                        Icon(Icons.Filled.Star, contentDescription = null, tint = Color.Yellow)
                        Icon(Icons.Filled.Star, contentDescription = null, tint = Color.Yellow)
                        Icon(Icons.Filled.Star, contentDescription = null, tint = Color.Yellow)
                        Icon(Icons.Filled.Star, contentDescription = null, tint = Color.Yellow)
                        Icon(Icons.Outlined.StarOutline, contentDescription = null, tint = Color.Yellow)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                        Text(
                            text = placeData.reviews.get(2),
                            fontSize = 15.sp,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = maxLines3,
                            onTextLayout = { textLayoutResult: TextLayoutResult ->
                                if (textLayoutResult.lineCount > minimumLineLength-1) {
                                    if (textLayoutResult.isLineEllipsized(minimumLineLength-1)) showReadMoreButtonState3 = true
                                }
                            }
                        )
                        if (showReadMoreButtonState3) {
                            Text(
                                text = if (expandedState3) "Read Less" else "Read More",
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold,

                                modifier = Modifier.clickable {
                                    expandedState3 = !expandedState3
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
                HorizontalDivider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp))
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }

    }
}}

@Composable
fun ImageSliderImg(imgRes:String) {
//    Image(painter = painterResource(id = imgRes), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier
//        .fillMaxWidth()
//        .height(200.dp))
    Image(painter = rememberAsyncImagePainter(model = imgRes), contentDescription = null, modifier = Modifier
        .aspectRatio(16f / 11.5f)
        .fillMaxWidth()
        .fillMaxHeight()
        , contentScale = ContentScale.Crop)


}

@Composable
fun indicator(active:Boolean) {
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
fun imgsliderwithindicator(img:List<String>,pname:String,city:String) {
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
            .fillMaxHeight(0.33f)
//            .padding(top = 10.dp)
//            .weight(1f)
        ){
            ImageSliderImg(imgRes = img[currentIndex.value])
//          188,  210 pixel
            Row (modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .padding(15.dp)
//                .padding(top = 258.dp, end = 10.dp)
//                .fillMaxWidth()
            ){
                
//                Text(text = pname+", "+city)
                img.forEachIndexed { index, i ->
                    indicator(active = index == currentIndex.value)
                    if (index < img.size - 1 ){
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                }
            }
            Row(modifier = Modifier
                .padding(start = 15.dp, top = 250.dp)
                ) {
                Text(text = "$pname", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 18.sp)
            }
        }

    }
}