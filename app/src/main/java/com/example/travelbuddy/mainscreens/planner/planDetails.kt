package com.example.travelbuddy.mainscreens.planner

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.CurrencyRupee
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.EmojiTransportation
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Hotel
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.travelbuddy.R
import com.example.travelbuddy.appcomponents.MyViewModel
import com.example.travelbuddy.data.Places
import com.example.travelbuddy.data.placeRepo
import com.example.travelbuddy.data.plans
import com.example.travelbuddy.mainscreens.home.placeCard
import com.example.travelbuddy.ui.theme.pure
import com.example.travelbuddy.ui.theme.text
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.launch
import okhttp3.internal.wait

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun planDetails(id: String,navController: NavHostController, onPlaceClick:(id:Int) -> Unit,onHClick:(id:Int) -> Unit) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val context = LocalContext.current
    val days = listOf(
        days(
            title = "Day 1"
        ),
        days(
            title = "Day 2"
        ),
        days(
            title = "Day 3"
        ),
    )
    var selected by remember {
        mutableStateOf(false)
    }
    val plan: MyViewModel = viewModel()
    var condition = remember{
        mutableStateOf(false)
    }
    if (plan.itemList.value.isNotEmpty()){
        condition.value = true
    }
    if (plan.itemList.value.isNotEmpty()){
        var x = plan.itemList.value.get(0)
//        Toast.makeText(context, x.days, Toast.LENGTH_SHORT).show()
    }

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    val pagerState = rememberPagerState {
        if(plan.itemList.value.isNotEmpty()){
            plan.itemList.value[id.toInt()].days.toInt()
        }else{
            5
        }
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage){
        if(!pagerState.isScrollInProgress){
            selectedTabIndex = pagerState.currentPage
        }
    }
    LaunchedEffect(pagerState.currentPage){
        selectedTabIndex = pagerState.currentPage
    }




    Scaffold(modifier = Modifier
        .padding(vertical = 0.dp)
        .fillMaxHeight()
        .fillMaxSize()
        .nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
//        Surface(shadowElevation = 5.dp) {


        CenterAlignedTopAppBar(modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth(),
            navigationIcon = {
                IconButton(modifier = Modifier,onClick = {navController.popBackStack()}) {
                    Icon(
                        Icons.Default.ArrowBack, contentDescription = null, modifier = Modifier.size(25.dp), tint = MaterialTheme.colorScheme.pure
                    )
                }
            },title = {
                Text(modifier = Modifier,text = if (condition.value) plan.itemList.value.get(id.toInt()).title else "")
            }, colors = TopAppBarDefaults.topAppBarColors(
                scrolledContainerColor = Color.Transparent,
                containerColor = Color.Transparent,
                titleContentColor = MaterialTheme.colorScheme.text,

                ), scrollBehavior = scrollBehavior,actions = {
                IconButton(modifier = Modifier,onClick = { selected = !selected}) {
                    Icon(
                        Icons.Default.Favorite, contentDescription = null, modifier = Modifier.size(25.dp), tint = if(selected) Color.Red else MaterialTheme.colorScheme.text
                    )
                }
            }
        )

//        }
    }) {
        Column(modifier = Modifier
//            .fillMaxSize()
            .padding(top = 100.dp)
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
            TabRow(modifier = Modifier
                .background(Color.Black)
                .fillMaxWidth(),selectedTabIndex = selectedTabIndex, indicator = { tabPositions ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                        .height(4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .padding(horizontal = 30.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(8.dp)
                        )
                )
            }) {
                days.take(if(plan.itemList.value.isNotEmpty()){
                    plan.itemList.value[id.toInt()].days.toInt()
                }else{
                    5
                }).forEachIndexed { index, item ->
                    Tab(modifier = Modifier,selected = index == selectedTabIndex
                        , onClick = {
                            scope.launch { pagerState.animateScrollToPage(index)
                                selectedTabIndex = index
                            }},
                        text = {
                            Text(text = item.title, fontWeight = FontWeight.Bold)
                        }
                    )
                }
            }
            HorizontalPager(state = pagerState, modifier = Modifier) {
                if(it == 0){

                    LazyColumn {
                        item { 
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                        item {
                            Row (modifier = Modifier.padding(horizontal = 20.dp)){
                                Text(text = "Enjoy the journey and try to get better every day. And don't lose the passion and the love for what you do.")
                            }

                        }
                        item {
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                        item {
                            Row (modifier = Modifier.padding(horizontal = 20.dp)){
                                Text(text = "Attractions", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                            }

                        }
                        item {
                            Spacer(modifier = Modifier.height(20.dp))
                        }
//                        item {
//                            LazyColumn(verticalArrangement = Arrangement.SpaceBetween) {
//                                var filter = placeRepo.filter {
//                                    it.category.contains("Beach") || it.category.contains("Heritage") && it.rating >= 4.5
//                                }
//                                item { Spacer(modifier = Modifier.width(0.01.dp)) }
//                                items(placeRepo.take(10)) { items ->
//                                    testplan(items, onClick = {})
//
//                                    }
//
//                            }
//                        }
                        if(plan.itemList.value.isNotEmpty()){
                            var y = placeRepo.filter { plan.itemList.value.get(id.toInt()).sights.contains("${it.id}") }
//                            Toast.makeText(context,"$x", Toast.LENGTH_SHORT).show()
                            items(y.take(3)) { items ->
                                testplan(items, onClick = {onPlaceClick(items.id)})
                                Column(modifier = Modifier.fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(Icons.Filled.ArrowDownward, contentDescription = null, modifier = Modifier
                                        .width(15.dp)
                                        .height(20.dp))
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Icon(Icons.Filled.DirectionsCar, contentDescription = null, modifier = Modifier
                                        .width(15.dp)
                                        .height(20.dp))
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Icon(Icons.Filled.ArrowDownward, contentDescription = null, modifier = Modifier
                                        .width(15.dp)
                                        .height(20.dp))
                                }
                            }
                        }
                        item {
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                        item {
                            Row (modifier = Modifier.padding(horizontal = 20.dp)){
                                Text(text = "Hotels", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                            }

                        }
                        item {
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                        if(plan.itemList.value.isNotEmpty()){
                            var y = placeRepo.filter { plan.itemList.value.get(id.toInt()).hotels.contains("${it.id}") }
//                            Toast.makeText(context,"$x", Toast.LENGTH_SHORT).show()
                            items(y.take(1)) { items ->
                                testplan(items, onClick = {onHClick(items.id)})
                            }
                        }
                        item {
                            Spacer(modifier = Modifier.height(20.dp))
                        }


                    }
                }
                if(it == 1){

                    LazyColumn {
                        item {
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                        item {
                            Row (modifier = Modifier.padding(horizontal = 20.dp)){
                                Text(text = "Enjoy the journey and try to get better every day. And don't lose the passion and the love for what you do.")
                            }

                        }
                        item {
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                        item {
                            Row (modifier = Modifier.padding(horizontal = 20.dp)){
                                Text(text = "Attractions", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                            }

                        }
                        item {
                            Spacer(modifier = Modifier.height(20.dp))
                        }
//                        item {
//                            LazyColumn(verticalArrangement = Arrangement.SpaceBetween) {
//                                var filter = placeRepo.filter {
//                                    it.category.contains("Beach") || it.category.contains("Heritage") && it.rating >= 4.5
//                                }
//                                item { Spacer(modifier = Modifier.width(0.01.dp)) }
//                                items(placeRepo.take(10)) { items ->
//                                    testplan(items, onClick = {})
//
//                                    }
//
//                            }
//                        }
                        if(plan.itemList.value.isNotEmpty()){
                            var x = placeRepo.filter { plan.itemList.value.get(id.toInt()).sights.contains("${it.id}") }
//                            Toast.makeText(context,"$x", Toast.LENGTH_SHORT).show()
                            if(x.size >= 9){
                                  x =  x.drop(3)
                            }
                            else{
                                x = x.takeLast(3)
                            }
                            items(x) { items ->
                                testplan(items, onClick = {onPlaceClick(items.id)})
                                Column(modifier = Modifier.fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(Icons.Filled.ArrowDownward, contentDescription = null, modifier = Modifier
                                        .width(15.dp)
                                        .height(20.dp))
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Icon(Icons.Filled.DirectionsCar, contentDescription = null, modifier = Modifier
                                        .width(15.dp)
                                        .height(20.dp))
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Icon(Icons.Filled.ArrowDownward, contentDescription = null, modifier = Modifier
                                        .width(15.dp)
                                        .height(20.dp))
                                }
                            }
                        }
                        item {
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                        item {
                            Row (modifier = Modifier.padding(horizontal = 20.dp)){
                                Text(text = "Hotels", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                            }

                        }
                        item {
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                        if(plan.itemList.value.isNotEmpty()){
                            var x = placeRepo.filter { plan.itemList.value.get(id.toInt()).hotels.contains("${it.id}") }
//                            Toast.makeText(context,"$x", Toast.LENGTH_SHORT).show()
                            items(x.take(1)) { items ->
                                testplan(items, onClick = {onHClick(items.id)})
                            }
                        }
                        item {
                            Spacer(modifier = Modifier.height(20.dp))
                        }


                    }
                }
                if(it == 2){

                    LazyColumn {
                        item {
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                        item {
                            Row (modifier = Modifier.padding(horizontal = 20.dp)){
                                Text(text = "Enjoy the journey and try to get better every day. And don't lose the passion and the love for what you do.")
                            }

                        }
                        item {
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                        item {
                            Row (modifier = Modifier.padding(horizontal = 20.dp)){
                                Text(text = "Attractions", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                            }

                        }
                        item {
                            Spacer(modifier = Modifier.height(20.dp))
                        }
//                        item {
//                            LazyColumn(verticalArrangement = Arrangement.SpaceBetween) {
//                                var filter = placeRepo.filter {
//                                    it.category.contains("Beach") || it.category.contains("Heritage") && it.rating >= 4.5
//                                }
//                                item { Spacer(modifier = Modifier.width(0.01.dp)) }
//                                items(placeRepo.take(10)) { items ->
//                                    testplan(items, onClick = {})
//
//                                    }
//
//                            }
//                        }
                        if(plan.itemList.value.isNotEmpty()){
                            var x = placeRepo.filter { plan.itemList.value.get(id.toInt()).sights.contains("${it.id}") }
//                            Toast.makeText(context,"$x", Toast.LENGTH_SHORT).show()
                            items(x.takeLast(3)) { items ->
                                testplan(items, onClick = {onPlaceClick(items.id)})
                                Column(modifier = Modifier.fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(Icons.Filled.ArrowDownward, contentDescription = null, modifier = Modifier
                                        .width(15.dp)
                                        .height(20.dp))
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Icon(Icons.Filled.DirectionsCar, contentDescription = null, modifier = Modifier
                                        .width(15.dp)
                                        .height(20.dp))
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Icon(Icons.Filled.ArrowDownward, contentDescription = null, modifier = Modifier
                                        .width(15.dp)
                                        .height(20.dp))
                                }
                            }
                        }
                        item {
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                        item {
                            Row (modifier = Modifier.padding(horizontal = 20.dp)){
                                Text(text = "Hotels", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                            }

                        }
                        item {
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                        if(plan.itemList.value.isNotEmpty()){
                            var x = placeRepo.filter { plan.itemList.value.get(id.toInt()).hotels.contains("${it.id}") }
//                            Toast.makeText(context,"$x", Toast.LENGTH_SHORT).show()
                            items(x.take(1)) { items ->
                                testplan(items, onClick = {onHClick(items.id)})
                            }
                        }
                        item {
                            Spacer(modifier = Modifier.height(20.dp))
                        }


                    }
                }




//            Box (modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
//                    Text(text = tabitems[it].title)
//            }
            }
        }




    }
}

data class days(
    val title:String
)



@Composable
fun testplan(items: Places, onClick:() -> Unit) {

    Card(
        modifier = Modifier
//        .padding(8.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.35f)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSystemInDarkTheme()) Color(0xFF25191D) else Color(0XFFE4F4F3),
        ),
        onClick = onClick
    ) {

        Box(contentAlignment = Alignment.TopCenter) {
            Image(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .background(Color.Gray),
                contentScale = ContentScale.Crop,
                painter = rememberAsyncImagePainter(model = items.picture.get(0)),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row (modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
            Text(text = items.title, fontSize = if(items.title.length < 15) 17.sp else 12.sp, fontWeight = FontWeight.Bold)

                if (!items.category.contains("Hotel")){
                    Row (modifier = Modifier
                        , horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically){
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
                    Column (modifier = Modifier,horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                        Text(text = "Duration", fontWeight = FontWeight.Bold)
                        Text(text = "${items.duration}"+" Hours", fontSize = 14.sp)
                    }
                }

            }

        }
        Column (modifier = Modifier.padding(16.dp)){
            Text(text = items.disc, maxLines = 3)
        }
    }



}
