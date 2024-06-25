package com.example.travelbuddy.features.guide

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.travelbuddy.R
import com.example.travelbuddy.appcomponents.textbuttonRL
import com.example.travelbuddy.mainscreens.dataviewmodel
import com.example.travelbuddy.navigation.routes
import com.example.travelbuddy.ui.theme.text
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore



@Composable
fun catChoice(navController: NavHostController, dataviewmodel: dataviewmodel = viewModel()) {

    var getdata = dataviewmodel.state.value
    var uname:String = getdata.username
    val auth = FirebaseAuth.getInstance()
    val userid = auth.currentUser?.uid
    var selectedCat: MutableState<MutableList<String>> = remember {
        mutableStateOf(mutableListOf())
    }
    val CategorySelected = hashMapOf(
        "selectedCat" to selectedCat.value,
        "username" to uname.toString(),
        "user_id" to userid.toString()
    ).toMap()
    var catBeach by remember { mutableStateOf(false) }
    var cathill by remember { mutableStateOf(false) }
    var catHeri by remember { mutableStateOf(false) }
    var catSpiri by remember { mutableStateOf(false) }
    var catHoney by remember { mutableStateOf(false) }
    var catZoo by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            Text(text = "Select your favourite places to travel", textAlign = TextAlign.Center, modifier = Modifier.padding(horizontal = 10.dp), style = MaterialTheme.typography.bodySmall,
                color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(50.dp))
        }
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            Text(text = "Please Select Three or More to proceed", textAlign = TextAlign.Center, modifier = Modifier.padding(horizontal = 10.dp), style = MaterialTheme.typography.bodySmall,
                color = Color.Black, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        }

        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Spacer(modifier = Modifier.width(10.dp))
            FilterChip(modifier = Modifier.padding(20.dp),
                onClick = { catHeri = !catHeri },
                label = {
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.height(30.dp))
                        Spacer(modifier = Modifier.width(110.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.heritage),
                            contentDescription = null, modifier = Modifier.size(70.dp)
                        )
                        Text("Heritage", fontSize = 20.sp)
                        Spacer(modifier = Modifier.height(30.dp))
                    }
                },
                selected = catHeri,
                trailingIcon = if (catHeri) {
                    {
                        if (selectedCat.value.contains("Heritage")) {
                        } else {
                            selectedCat.value.add("Heritage")
                        }
                    }
                } else {
                    selectedCat.value.remove("Heritage")
                    null
                },elevation = FilterChipDefaults.elevatedFilterChipElevation(
                    elevation = if(catHeri) 10.dp else 0.dp,
                )
            )
//                Spacer(modifier = Modifier.width(10.dp))
            FilterChip(modifier = Modifier.padding(20.dp),
                onClick = { cathill = !cathill },
                label = {
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.height(30.dp))
                        Spacer(modifier = Modifier.width(110.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.mountain),
                            contentDescription = null, modifier = Modifier.size(70.dp)
                        )
                        Text("Hill Station", fontSize = 18.sp)
                        Spacer(modifier = Modifier.height(30.dp))
                    }
                },
                selected = cathill,
                trailingIcon = if (cathill) {
                    {
                        if (selectedCat.value.contains("Hill Station")) {
                        } else {
                            selectedCat.value.add("Hill Station")
                        }
                    }
                } else {
                    selectedCat.value.remove("Hill Station")
                    null
                },elevation = FilterChipDefaults.elevatedFilterChipElevation(
                    elevation = if(cathill) 10.dp else 0.dp,
                )

            )
            Spacer(modifier = Modifier.width(10.dp))

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            FilterChip(modifier = Modifier.padding(20.dp),
                onClick = { catBeach = !catBeach },
                label = {
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.height(30.dp))
                        Spacer(modifier = Modifier.width(110.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.beach),
                            contentDescription = null, modifier = Modifier.size(70.dp)
                        )
                        Text("Beach", fontSize = 20.sp)
                        Spacer(modifier = Modifier.height(30.dp))
                    }
                },
                selected = catBeach,
                trailingIcon = if (catBeach) {
                    {
                        if (selectedCat.value.contains("Beach")) {
                        } else {
                            selectedCat.value.add("Beach")
                        }
                    }
                } else {
                    selectedCat.value.remove("Beach")
                    null
                },elevation = FilterChipDefaults.elevatedFilterChipElevation(
                    elevation = if(catBeach) 10.dp else 0.dp,
                )
            )
            FilterChip(modifier = Modifier.padding(20.dp),
                onClick = { catSpiri = !catSpiri },
                label = {
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.height(30.dp))
                        Spacer(modifier = Modifier.width(110.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.spiritual),
                            contentDescription = null, modifier = Modifier.size(70.dp)
                        )
                        Text("Spiritual", fontSize = 20.sp)
                        Spacer(modifier = Modifier.height(30.dp))
                    }
                },
                selected = catSpiri,
                trailingIcon = if (catSpiri) {
                    {
                        if (selectedCat.value.contains("Spiritual")) {
                        } else {
                            selectedCat.value.add("Spiritual")
                        }
                    }
                } else {
                    selectedCat.value.remove("Spiritual")
                    null
                },elevation = FilterChipDefaults.elevatedFilterChipElevation(
                    elevation = if(catSpiri) 10.dp else 0.dp,
                )
            )
            Spacer(modifier = Modifier.width(10.dp))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Spacer(modifier = Modifier.width(10.dp))
            FilterChip(modifier = Modifier.padding(20.dp),
                onClick = { catHoney = !catHoney },
                label = {
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.height(30.dp))
                        Spacer(modifier = Modifier.width(110.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.honeymoon),
                            contentDescription = null, modifier = Modifier.size(70.dp)
                        )
                        Text("Honeymoon", fontSize = 20.sp)
                        Spacer(modifier = Modifier.height(30.dp))
                    }
                },
                selected = catHoney,
                trailingIcon = if (catHoney) {
                    {
                        if (selectedCat.value.contains("Honeymoon")) {
                        } else {
                            selectedCat.value.add("Honeymoon")
                        }
                    }
                } else {
                    selectedCat.value.remove("Honeymoon")
                    null
                },elevation = FilterChipDefaults.elevatedFilterChipElevation(
                    elevation = if(catHoney) 10.dp else 0.dp,
                )
            )
            FilterChip(modifier = Modifier.padding(20.dp),
                onClick = { catZoo = !catZoo },
                label = {
                    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.height(30.dp))
                        Spacer(modifier = Modifier.width(110.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.zoo),
                            contentDescription = null, modifier = Modifier.size(70.dp)
                        )
                        Text("Zoo", fontSize = 20.sp)
                        Spacer(modifier = Modifier.height(30.dp))
                    }
                },
                selected = catZoo,
                trailingIcon = if (catZoo) {
                    {
                        if (selectedCat.value.contains("Zoo")) {
                        } else {
                            selectedCat.value.add("Zoo")
                        }
                    }
                } else {
                    selectedCat.value.remove("Zoo")
                    null
                },elevation = FilterChipDefaults.elevatedFilterChipElevation(
                    elevation = if(catZoo) 10.dp else 0.dp,
                )
            )
            Spacer(modifier = Modifier.width(10.dp))
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ) {
            textbuttonRL(text = "Submit", onClick = {
//                navController.navigate(routes.locreq.routes)
//                FirebaseFirestore.getInstance().collection("UserChoice").document("$userid")
//                    .set(CategorySelected)
//                navController.navigate(routes.locreq.routes)

            }, onbuttonclicked = {
//                navController.navigate(routes.locreq.routes)
                FirebaseFirestore.getInstance().collection("UserChoice").document("$userid")
                    .set(CategorySelected).addOnSuccessListener {
                        navController.navigate(routes.locreq.routes)
                    }

            },isEnabled = if(selectedCat.value.size >=3){
                true
            }else{
                false
            })
        }
//            Text(text = "${test.toString()}")

//        Text(text = "${selectedCat.value}")
    }


    }

