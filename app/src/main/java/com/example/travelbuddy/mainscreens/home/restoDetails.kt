package com.example.travelbuddy.mainscreens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.travelbuddy.data.Places
import com.example.travelbuddy.data.placeRepo
import com.example.travelbuddy.ui.theme.Cardcontainer
import com.example.travelbuddy.ui.theme.pure
import com.example.travelbuddy.ui.theme.text
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun restoDetails(id: Int,onNavigateUp: () -> Unit,onHClick:(id:Int) -> Unit, onPlaceClick:(id:Int) -> Unit) {
    val placeData = placeRepo.first { it.id == id }
    val link = LocalUriHandler.current
    val minimumLineLength = 3
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    //Adding States
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


    Scaffold(modifier = Modifier
        .fillMaxSize()
        .nestedScroll(scrollBehavior.nestedScrollConnection)
        , topBar = {
            CenterAlignedTopAppBar(title = {
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

                    Icon(
                        Icons.Default.LocationOn, contentDescription = null, tint = MaterialTheme.colorScheme.pure
                    )

                }
                IconButton(onClick = {link.openUri(placeData.location)}) {

                    Icon(
                        Icons.Default.Favorite, contentDescription = null, tint = if(placeData.fav.value == true) Color.Red else MaterialTheme.colorScheme.text,
                        modifier = Modifier.clickable {
                            placeData.fav.value = !placeData.fav.value
                        }
                    )

                }
            }, scrollBehavior = scrollBehavior)
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
                imgsliderwithindicatorHotel(img = imgOfPlace,pname,city)
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
                    Text(text = "Cuisines", color = MaterialTheme.colorScheme.text, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                }
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                var filter = placeRepo.filter { it.category.contains("Cuisine") }
                LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)){
                    item { Spacer(modifier = Modifier.width(0.01.dp)) }
                    items(filter){ items ->
//                    placeCard(items , onClick = {onPlaceClick(items.title)})
                        cuisineCard(items , onClick = {})
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
                    Text(text = "Attractions nearby", color = MaterialTheme.colorScheme.text, fontSize = 16.sp, fontWeight = FontWeight.Medium)
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
                        placeCard(items , onClick = {onPlaceClick(items.id)}, onFav = {})
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
                    Text(text = "Popular Dishes", color = MaterialTheme.colorScheme.text, fontSize = 18.sp, fontWeight = FontWeight.Medium)
                }
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            items(items =  placeRepo.filter { it.category.contains("Dishes") }, key = {
                it.id
            }){ items ->
//                    placeCard(items , onClick = {onPlaceClick(items.title)})
                DishCard(items , onClick = {})
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
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}



@Composable
fun cuisineCard(items: Places, onClick:() -> Unit) {
    var selected by rememberSaveable {
        mutableStateOf(false)
    }
    val auth = FirebaseAuth.getInstance()
    val userid = auth.currentUser?.uid
    var favPlace: MutableState<MutableList<String>> = remember {
        mutableStateOf(mutableListOf())
    }
    ElevatedCard (modifier = Modifier
//        .clip(RoundedCornerShape(20.dp))
        .size(height = 165.dp, width = 200.dp),onClick = onClick, elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)){
        Column(modifier = Modifier
            .background(Cardcontainer)
            .fillMaxHeight()) {
            Box (modifier = Modifier,contentAlignment = Alignment.Center){
                Image(modifier = Modifier
                    .clip(RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp))
                    .fillMaxWidth(1f)
                    .fillMaxHeight(1f)
                    .background(Color.Gray)
                    , contentScale = ContentScale.Crop,painter = rememberAsyncImagePainter(model = items.picture.get(0)), contentDescription = null)
                Text(text = items.title, fontWeight = FontWeight.Bold, fontSize = 23.sp, color = Color.White, modifier = Modifier.background(
                    Color.Transparent))
            }
        }

    }

}

@Composable
fun DishCard(items: Places, onClick:() -> Unit) {
    var selected by rememberSaveable {
        mutableStateOf(false)
    }
    Card (modifier = Modifier
        .padding(horizontal = 16.dp)
//        .padding(8.dp)
        .fillMaxWidth()
        , shape = RoundedCornerShape(0.dp), colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),onClick = onClick
    ){
        Spacer(modifier = Modifier.height(5.dp))
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
                .fillMaxWidth(0.8f)
                .fillMaxHeight()
                .align(Alignment.CenterVertically)) {
                Row {
                    Text(text = items.title, fontWeight = FontWeight.Bold, fontSize = 19.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "Rs "+"${items.duration}", fontWeight = FontWeight.Medium, fontSize = 13.sp)
                }
            }
            Column(modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .align(Alignment.CenterVertically)) {
               Icon(Icons.Filled.Add, contentDescription = null)
            }
            
        }


    }
    Spacer(modifier = Modifier.height(5.dp))
    HorizontalDivider(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp), thickness = 0.2.dp, color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray)

}