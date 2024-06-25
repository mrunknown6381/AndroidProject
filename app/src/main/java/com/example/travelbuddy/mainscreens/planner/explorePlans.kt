package com.example.travelbuddy.mainscreens.planner

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.travelbuddy.appcomponents.MyViewModel
import com.example.travelbuddy.mainscreens.wishlist.userFav
import com.example.travelbuddy.ui.theme.text
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun explorePlan(onNavigateUp: () -> Unit,onPClick:(id:String) -> Unit) {
    val plan: MyViewModel = viewModel()
    val context = LocalContext.current
    var favPlan: MutableState<MutableList<String>> = remember {
        mutableStateOf(mutableListOf())
    }
    val userPlans: userPlans = viewModel()
    val auth = FirebaseAuth.getInstance()
    val userid = auth.currentUser?.uid
    val favPlaneSelected = hashMapOf(
        "favPlans" to favPlan.value,
        "user_id" to userid.toString()
    ).toMap()
    var x = userPlans.state.value
    var yy = x.favPlans
    if(yy.isNotEmpty()){
//        Toast.makeText(context,"$yy", Toast.LENGTH_SHORT).show()
    }

    val tabitems = listOf(
        tabitem(
            title = "Popular"
        ),
        tabitem(
            title = "1 Day"
        ),
        tabitem(
            title = "2 Days"
        ),
        tabitem(
            title = "3 Days"
        ),
    )
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    val pagerState = rememberPagerState {
        tabitems.size
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

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(modifier = Modifier
//        .fillMaxSize()
        .nestedScroll(scrollBehavior.nestedScrollConnection)
        , topBar = {
            CenterAlignedTopAppBar(scrollBehavior = scrollBehavior,title = {
                Text(text = "Explore Plans", color = MaterialTheme.colorScheme.text, fontSize = 16.sp)
            },colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.text,
            ), navigationIcon = {
                IconButton(onClick = onNavigateUp) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null, tint = MaterialTheme.colorScheme.text, modifier = Modifier)
                }
            })
        }) {
        Column(modifier = Modifier.fillMaxSize().padding(top = 100.dp)) {
            TabRow(modifier = Modifier.background(Color.Black),selectedTabIndex = selectedTabIndex, indicator = { tabPositions ->
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
                tabitems.forEachIndexed { index, item ->
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
                    LazyColumn(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
//            .padding(horizontal = 16.dp)
                    ) {
                        item {
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                        items(plan.itemList.value){
                            test(items = it, onClick = {
                                onPClick(it.id)

                            } , onFav = {
                                favPlan.value.add(it.id)
                                FirebaseFirestore.getInstance().collection("favPlans")
                                    .document("$userid")
                                    .set(favPlaneSelected, SetOptions.merge())
                            }, selected = yy.contains(it.id))
                        }


                    }
                }
                if(it == 1){
                    LazyColumn(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
//            .padding(horizontal = 16.dp)
                    ) {
                        item {
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                        var x = plan.itemList.value.filter { it.days.toInt() == 1 }
                        items(x){
                            test(items = it, onClick = {
                                onPClick(it.id)
                                favPlan.value.add(it.id)
                                FirebaseFirestore.getInstance().collection("favPlans")
                                    .document("$userid")
                                    .set(favPlaneSelected, SetOptions.merge())
                            }, onFav = {
                                favPlan.value.add(it.id)
                                FirebaseFirestore.getInstance().collection("favPlans")
                                    .document("$userid")
                                    .set(favPlaneSelected, SetOptions.merge())
                            }, selected = yy.contains(it.id) )
                        }


                    }
                }
                if(it == 2){
                    LazyColumn(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
//            .padding(horizontal = 16.dp)
                    ) {
                        item {
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                        var y = plan.itemList.value.filter { it.days.toInt() == 2 }
                        items(y){
                            test(items = it, onClick = {
                                onPClick(it.id)
                            } , onFav = {
                                favPlan.value.add(it.id)
                                FirebaseFirestore.getInstance().collection("favPlans")
                                    .document("$userid")
                                    .set(favPlaneSelected, SetOptions.merge())
                            }, selected = yy.contains(it.id))
                        }


                    }
                }
                if(it == 3){
                    LazyColumn(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
//            .padding(horizontal = 16.dp)
                    ) {
                        item {
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                        var z = plan.itemList.value.filter { it.days.toInt() == 3 }
                        items(z){
                            test(items = it, onClick = {
                                onPClick(it.id)

                            } , onFav = {
                                favPlan.value.add(it.id)
                                FirebaseFirestore.getInstance().collection("favPlans")
                                    .document("$userid")
                                    .set(favPlaneSelected, SetOptions.merge())
                            }, selected = yy.contains(it.id))
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
data class tabitem(
    val title:String
)