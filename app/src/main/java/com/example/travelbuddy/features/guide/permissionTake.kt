package com.example.travelbuddy.features.guide

import android.Manifest
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.travelbuddy.MainActivity
import com.example.travelbuddy.R
import com.example.travelbuddy.appcomponents.loadanim
import com.example.travelbuddy.location.locationUtils
import com.example.travelbuddy.location.locationviewmodel
import com.example.travelbuddy.mainscreens.dataviewmodel
import com.example.travelbuddy.mainscreens.home.locationdisplay
import com.example.travelbuddy.mainscreens.wishlist.userFav
import com.example.travelbuddy.navigation.routes
import com.example.travelbuddy.ui.theme.pure
import com.example.travelbuddy.ui.theme.text
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore


//navController: NavHostController

@Composable
fun permissionReq( navController: NavHostController,locationUtils: locationUtils,viewModel: locationviewmodel) {
    var city = ""
    val dataviewmodel: dataviewmodel = viewModel()
    var getdata = dataviewmodel.state.value
    val context = LocalContext.current
    var test = 0

    val loc = viewModel.location.value
    val address = loc?.let {
        locationUtils.reverseGeocodeLocation(loc)
    }
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = {permissions ->
            if(permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true &&
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ){
                locationUtils.requestLocationUpdates(viewModel = viewModel)
            }else{
                val rationalRequired = ActivityCompat.shouldShowRequestPermissionRationale(
                    context as MainActivity,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) ||
                        ActivityCompat.shouldShowRequestPermissionRationale(
                            context ,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                if (rationalRequired){
                    Toast.makeText(context,"location required", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(context,"enable using settings", Toast.LENGTH_LONG).show()
                }
            }
        })
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background), contentAlignment = Alignment.Center
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(120.dp))
            Box(modifier = Modifier
                .background(
                    if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray,
                    shape = RoundedCornerShape(200.dp)
                )
                .padding(30.dp)
                .clip(RoundedCornerShape(50.dp)), contentAlignment = Alignment.Center){
                Icon(modifier = Modifier
                    .size(150.dp)
                    ,painter = painterResource(id = R.drawable.loc), contentDescription = null, tint = MaterialTheme.colorScheme.pure)
            }
            Spacer(modifier = Modifier.height(70.dp))
            Column (modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Would you like to explore", fontWeight = FontWeight.Bold, fontSize = 30.sp)
                Text(text = "places nearby ?", fontWeight = FontWeight.Bold, fontSize = 30.sp)
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Start with sharing your location", fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.Gray)
                Text(text = "with us", fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.Gray)
            }
            Spacer(modifier = Modifier.height(100.dp))
            Button(onClick = {
                //here is change
                if (locationUtils.hasLocationPermisssion(context)) {
                    //Granted
                    locationUtils.requestLocationUpdates(viewModel)
                } else {
                    //denied
                    requestPermissionLauncher.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    )
                }
            }, modifier = Modifier.height(50.dp),elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp,
                pressedElevation = 20.dp
            ),shape = RoundedCornerShape(size = 30.dp),colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
            ) {
                Text(text = "Allow Location Service", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { navController.navigate(routes.bottombar.routes) }, modifier = Modifier.height(50.dp),shape = RoundedCornerShape(size = 30.dp),colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            )) {
                Text(text = "Maybe Later", fontWeight = FontWeight.Medium, textDecoration = TextDecoration.Underline, fontSize = 18.sp, color = MaterialTheme.colorScheme.text)
            }
//            textbuttonwlcm(text = "Allow", onClick = {
//            }, onbuttonclicked = {
////                navController.navigate(routes.Screenregister.routes)
//            })
//            Spacer(modifier = Modifier.height(30.dp))
//            textbuttonwlcm(text = "Skip", onClick = {
//            }, onbuttonclicked = {
////                navController.navigate(routes.Screenlogin.routes)
//            })
            Spacer(modifier = Modifier.height(30.dp))
            if (locationUtils.hasLocationPermisssion(context)) {
                loadanim()
            }
            if(address!=null){
                val auth = FirebaseAuth.getInstance()
                val userid = auth.currentUser?.uid
                val db = Firebase.firestore
                db.collection("users").document("$userid").update("city",address).addOnSuccessListener {
                    navController.navigate(routes.bottombar.routes)
                }
            }
        }
    }
    
}


@Composable
fun locTakeInit(context: Context, locationUtils: locationUtils, viewModel: locationviewmodel) {

    var city = ""
    val dataviewmodel: dataviewmodel = viewModel()
    var getdata = dataviewmodel.state.value
    if(getdata.city!=null){
        city = getdata.city.toString()
    }

    val loc = viewModel.location.value
    val address = loc?.let {
        locationUtils.reverseGeocodeLocation(loc)
    }
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = {permissions ->
            if(permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true &&
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ){
                locationUtils.requestLocationUpdates(viewModel = viewModel)
            }else{
                val rationalRequired = ActivityCompat.shouldShowRequestPermissionRationale(
                    context as MainActivity,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) ||
                        ActivityCompat.shouldShowRequestPermissionRationale(
                            context ,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                if (rationalRequired){
                    Toast.makeText(context,"location required", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(context,"enable using settings", Toast.LENGTH_LONG).show()
                }
            }
        })
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.clickable {
                if (locationUtils.hasLocationPermisssion(context)) {
                    //Granted
                    locationUtils.requestLocationUpdates(viewModel)
                } else {
                    //denied
                    requestPermissionLauncher.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    )
                }
            },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(imageVector = Icons.Filled.LocationOn, contentDescription = null)
            Spacer(modifier = Modifier.width(7.dp))
            Column {
//                if (getdata.city != null){
//                    Text(text = "$city,India", fontWeight = FontWeight.Medium)
//                }
                if(address!=null){
                    Text(text = "$address,India", fontWeight = FontWeight.Bold)
                    val auth = FirebaseAuth.getInstance()
                    val userid = auth.currentUser?.uid
                    val db = Firebase.firestore
                    db.collection("users").document("$userid").update("city",address)
                }
                else if(getdata.city != null){
                    Text(text = "$city,India", fontWeight = FontWeight.Medium)
                }
                else{
                    Text(text = "Enable Location")
                }

            }
        }

    }
}

@Composable
fun topInit(viewModel: locationviewmodel) {
    val context = LocalContext.current
    val locationUtils = locationUtils(context)
    locationdisplay(context = context, locationUtils = locationUtils,viewModel)
}