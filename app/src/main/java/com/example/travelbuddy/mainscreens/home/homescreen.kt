package com.example.travelbuddy.mainscreens.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.travelbuddy.MainActivity
import com.example.travelbuddy.R
import com.example.travelbuddy.data.Places
import com.example.travelbuddy.data.placeRepo
import com.example.travelbuddy.location.locationUtils
import com.example.travelbuddy.location.locationviewmodel
import com.example.travelbuddy.mainscreens.dataviewmodel
import com.example.travelbuddy.mainscreens.wishlist.userFav
import com.example.travelbuddy.ui.theme.Cardcontainer
import com.example.travelbuddy.ui.theme.pure
import com.example.travelbuddy.ui.theme.text
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.FamilyRestroom
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.travelbuddy.ui.theme.BlueSky
import com.example.travelbuddy.ui.theme.BorderColor
import com.example.travelbuddy.ui.theme.TravelBuddyTheme
import com.example.travelbuddy.ui.theme.NightSky
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
    fun homescreen(navController: NavHostController,onPlaceClick:(id:Int) -> Unit,onShowMoreClick:(cat:String) -> Unit,onCityClick:(id:Int) -> Unit,dataviewmodel: dataviewmodel = viewModel()) {
        var getdata = dataviewmodel.state.value
    val context = LocalContext.current
    var getdata2 = dataviewmodel.state3.value
    var catData = dataviewmodel.state2.value
    var test = catData.selectedCat
    val userFav:userFav = viewModel()
    var testcase: MutableList<String> = remember {
        mutableListOf()
    }
    val scope = rememberCoroutineScope()
//    val isBackPressed = remember { mutableStateOf(false) }
//    BackHandler( !isBackPressed.value) {
//        isBackPressed.value = true
//        Toast.makeText(context, "Press back again to exit", Toast.LENGTH_SHORT).show()
//        scope.launch {
//            delay(2000L)
//            isBackPressed.value = false
//        }
//    }
//    val db = FirebaseFirestore.getInstance()
//    val uid = getdata.user_id
//    var x = db.collection("favPlaces").whereEqualTo("user_id",uid).get()
//    x.addOnCompleteListener {
//        var x = it
//        Toast.makeText(context, "${x}", Toast.LENGTH_SHORT).show()
//    }
    var x = userFav.state.value
    var y = x.favPlace
    LaunchedEffect(y){
        if(y.isNotEmpty()){
           var k = y.toSet()
//            Toast.makeText(context, k.toString(), Toast.LENGTH_SHORT).show()
//            var selectFav = placeRepo.forEach {y.contains("${it.id}")
//                if(y.contains("${it.id}")){
//                    it.fav.value =  true
//                }
//            }
            var selectFav = placeRepo.filter {  k.contains("${it.id}") }
            selectFav.forEach {
                it.fav.value = true
            }
            if(true){
                var f = placeRepo.filter { it.fav.value == true}
                var y = listOf("")
                f.forEach {
                    testcase.add("${it.id}")
                }
//                Toast.makeText(context, testcase.toString(), Toast.LENGTH_SHORT).show()
                testcase.addAll(y)
            }

        }
    }


    val g7 = Brush.verticalGradient(
        0.0f to Color(0xFFFF0000),
        1.0f to Color(0xFF2E00AD),
        startY = 0.0f,
        endY = 3000.0f
    )
    LaunchedEffect(dataviewmodel.state2.value){
       var test = dataviewmodel.state2.value.selectedCat
    }
    val viewModel: locationviewmodel = viewModel()

    var ImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val auth = FirebaseAuth.getInstance()
    val userid = auth.currentUser?.uid
    var favPlace: MutableState<MutableList<String>> = remember {
        mutableStateOf(mutableListOf())
    }
    favPlace.value.addAll(y)
    val favPlaceSelected = hashMapOf(
        "favPlace" to testcase,
        "user_id" to userid.toString()
    ).toMap()
//    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.travel))


ImageUri = getdata2.url.toUri()


        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {


            LazyColumn(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
//            .padding(horizontal = 16.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Home",
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            Spacer(modifier = Modifier.height(13.dp))
                            top(viewModel)
                        }


//                Image(
//                    painter = if (ImageUri == null) {
//                        painterResource(id = R.drawable.userdp)
//                    } else {
//                        rememberAsyncImagePainter(model = ImageUri)
//                    }, contentDescription = "user",
//                    modifier = Modifier
//                        .size(60.dp)
//                        .clip(CircleShape)
//                        .background(Color.Gray)
//                        .border(width = 1.2.dp, brush = g7, shape = CircleShape)
//                        .clickable { }, contentScale = ContentScale.Crop
//                )

                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .height(120.dp)
                                .fillMaxWidth(0.90f)
                                .clip(RoundedCornerShape(10.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.homebg),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                            Text(
                                modifier = Modifier,
                                text = "Where do you want to explore today ?",
                                fontSize = 18.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }


                }
                item {
                    Spacer(modifier = Modifier.height(15.dp))
                }
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = "Browse Categories",
                            color = MaterialTheme.colorScheme.text,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }
                item {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                        item {
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(
                                modifier = Modifier.clickable { onShowMoreClick("Heritage") },
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(
                                            if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray,
                                            shape = RoundedCornerShape(100.dp)
                                        )
                                        .padding(10.dp)
                                        .clip(RoundedCornerShape(100.dp)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.heritage),
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.pure,
                                        modifier = Modifier
                                            .size(40.dp),
                                    )
                                }
                                Spacer(modifier = Modifier.height(7.dp))
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(text = "Heritage", fontWeight = FontWeight.Bold)
                                }
                            }
                            Spacer(modifier = Modifier.width(20.dp))
                            Column(
                                modifier = Modifier.clickable { onShowMoreClick("Hill Station") },
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(
                                            if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray,
                                            shape = RoundedCornerShape(100.dp)
                                        )
                                        .padding(10.dp)
                                        .clip(RoundedCornerShape(100.dp)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.mountain),
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.pure,
                                        modifier = Modifier
                                            .size(40.dp),
                                    )
                                }
                                Spacer(modifier = Modifier.height(7.dp))
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = "Hill Station",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(20.dp))
                            Column(
                                modifier = Modifier.clickable { onShowMoreClick("Beach") },
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(
                                            if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray,
                                            shape = RoundedCornerShape(100.dp)
                                        )
                                        .padding(10.dp)
                                        .clip(RoundedCornerShape(100.dp)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.beach),
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.pure,
                                        modifier = Modifier
                                            .size(40.dp),
                                    )
                                }
                                Spacer(modifier = Modifier.height(7.dp))
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(text = "Beach", fontWeight = FontWeight.Bold)
                                }
                            }
                            Spacer(modifier = Modifier.width(20.dp))
                            Column(
                                modifier = Modifier.clickable { onShowMoreClick("Spiritual") },
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(
                                            if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray,
                                            shape = RoundedCornerShape(100.dp)
                                        )
                                        .padding(10.dp)
                                        .clip(RoundedCornerShape(100.dp)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.spiritual),
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.pure,
                                        modifier = Modifier
                                            .size(40.dp),
                                    )
                                }
                                Spacer(modifier = Modifier.height(7.dp))
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(text = "Spiritual", fontWeight = FontWeight.Bold)
                                }
                            }
                            Spacer(modifier = Modifier.width(20.dp))
                            Column(
                                modifier = Modifier.clickable { onShowMoreClick("Zoo") },
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(
                                            if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray,
                                            shape = RoundedCornerShape(100.dp)
                                        )
                                        .padding(10.dp)
                                        .clip(RoundedCornerShape(100.dp)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.zoo),
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.pure,
                                        modifier = Modifier
                                            .size(40.dp),
                                    )
                                }
                                Spacer(modifier = Modifier.height(7.dp))
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(text = "Zoo", fontWeight = FontWeight.Bold)
                                }
                            }
                            Spacer(modifier = Modifier.width(20.dp))
                        }

                    }
                }
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = "Recommended For You",
                            color = MaterialTheme.colorScheme.text,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }
                item {
//            val filter = placeRepo.filter { it.city == "Patan" || it.rating == 4.8f }
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                        var filter = placeRepo.filter {
                            it.category.contains("Beach") || it.category.contains("Heritage") && it.rating >= 4.5
                        }
                        item { Spacer(modifier = Modifier.width(0.01.dp)) }
                        items(filter.take(10)) { items ->
//                    placeCard(items , onClick = {onPlaceClick(items.title)})
                            placeCard(items, onClick = {
                                onPlaceClick(items.id)
                            }, onFav = {
                                if (y.containsAll(favPlace.value)) {

                                } else {
                                    favPlace.value.addAll(y)

                                }

                                if (items.id != null) {

                                    if (!items.fav.value) {
                                        favPlace.value.addAll(y)
                                        Toast.makeText(context, "liked", Toast.LENGTH_SHORT).show()
                                        favPlace.value.add(items.id.toString())
                                        if (testcase.contains("${items.id}")) {

                                        } else {
                                            testcase.add("${items.id}")
                                        }

                                        testcase.distinct()
                                        testcase.remove("")
                                        FirebaseFirestore.getInstance().collection("favPlaces")
                                            .document("$userid")
                                            .set(favPlaceSelected, SetOptions.merge())
                                    } else {
                                        favPlace.value.addAll(y)
                                        Toast.makeText(context, "Disliked", Toast.LENGTH_SHORT)
                                            .show()
                                        favPlace.value.removeAll(listOf(items.id.toString()))
                                        while (testcase.contains("${items.id}")) {
                                            testcase.remove("${items.id}")
                                        }
                                        testcase.remove("")
                                        FirebaseFirestore.getInstance().collection("favPlaces")
                                            .document("$userid")
                                            .set(favPlaceSelected, SetOptions.merge())
                                    }
                                }
                            })
                        }
                    }
                }
                if (test.contains("Heritage")) {
                    item {
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            Image(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = R.drawable.heritage),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(
                                    MaterialTheme.colorScheme.primary
                                )
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Heritage",
                                color = MaterialTheme.colorScheme.text,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    item {
            val filter = placeRepo.filter { it.category.contains("Heritage") }
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                            item { Spacer(modifier = Modifier.width(0.01.dp)) }
                            items(filter.take(4)) { items ->
//                    placeCard(items , onClick = {onPlaceClick(items.title)})
                                placeCard(items, onClick = {
                                    onPlaceClick(items.id)
//                        favPlace.value.add(items.id.toString())
//                        FirebaseFirestore.getInstance().collection("favPlaces").document("$userid")
//                            .set(favPlaceSelected)
                                }, onFav = {
                                    if (y.containsAll(favPlace.value)) {

                                    } else {
                                        favPlace.value.addAll(y)

                                    }

                                    if (items.id != null) {

                                        if (!items.fav.value) {
                                            favPlace.value.addAll(y)
                                            Toast.makeText(context, "liked", Toast.LENGTH_SHORT)
                                                .show()
                                            favPlace.value.add(items.id.toString())
                                            if (testcase.contains("${items.id}")) {

                                            } else {
                                                testcase.add("${items.id}")
                                            }

                                            testcase.distinct()
                                            testcase.remove("")
                                            FirebaseFirestore.getInstance().collection("favPlaces")
                                                .document("$userid")
                                                .set(favPlaceSelected, SetOptions.merge())
                                        } else {
                                            favPlace.value.addAll(y)
                                            Toast.makeText(context, "Disliked", Toast.LENGTH_SHORT)
                                                .show()
                                            favPlace.value.removeAll(listOf(items.id.toString()))
                                            while (testcase.contains("${items.id}")) {
                                                testcase.remove("${items.id}")
                                            }
                                            testcase.remove("")
                                            FirebaseFirestore.getInstance().collection("favPlaces")
                                                .document("$userid")
                                                .set(favPlaceSelected, SetOptions.merge())
                                        }
                                    }
                                })
                            }
                            item {
//                    Spacer(modifier = Modifier.width(15.dp))
                                ElevatedCard(modifier = Modifier
//                        .clip(RoundedCornerShape(20.dp))
                                    .size(height = 230.dp, width = 200.dp),
                                    onClick = { onShowMoreClick("Heritage") },
                                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .background(Cardcontainer)
                                            .fillMaxHeight()
                                            .fillMaxWidth(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(text = "Show More", fontWeight = FontWeight.Bold)
                                    }
                                }
                                Spacer(modifier = Modifier.width(15.dp))
                            }
                        }
                    }
                }
                if (test.contains("Beach")) {
                    item {
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            Image(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = R.drawable.beach),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(
                                    MaterialTheme.colorScheme.primary
                                )
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Beach",
                                color = MaterialTheme.colorScheme.text,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    item {
            val filter = placeRepo.filter { it.category.contains("Beach") }
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                            item { Spacer(modifier = Modifier.width(0.01.dp)) }
                            items(filter.take(4)) { items ->
//                    placeCard(items , onClick = {onPlaceClick(items.title)})
                                placeCard(items, onClick = { onPlaceClick(items.id) }, onFav = {
                                    if (y.containsAll(favPlace.value)) {

                                    } else {
                                        favPlace.value.addAll(y)

                                    }

                                    if (items.id != null) {

                                        if (!items.fav.value) {
                                            favPlace.value.addAll(y)
                                            Toast.makeText(context, "liked", Toast.LENGTH_SHORT)
                                                .show()
                                            favPlace.value.add(items.id.toString())
                                            if (testcase.contains("${items.id}")) {

                                            } else {
                                                testcase.add("${items.id}")
                                            }

                                            testcase.distinct()
                                            testcase.remove("")
                                            FirebaseFirestore.getInstance().collection("favPlaces")
                                                .document("$userid")
                                                .set(favPlaceSelected, SetOptions.merge())
                                        } else {
                                            favPlace.value.addAll(y)
                                            Toast.makeText(context, "Disliked", Toast.LENGTH_SHORT)
                                                .show()
                                            favPlace.value.removeAll(listOf(items.id.toString()))
                                            while (testcase.contains("${items.id}")) {
                                                testcase.remove("${items.id}")
                                            }
                                            testcase.remove("")
                                            FirebaseFirestore.getInstance().collection("favPlaces")
                                                .document("$userid")
                                                .set(favPlaceSelected, SetOptions.merge())
                                        }
                                    }
                                })
                            }
                            item {
//                    Spacer(modifier = Modifier.width(15.dp))
                                ElevatedCard(modifier = Modifier
//                        .clip(RoundedCornerShape(20.dp))
                                    .size(height = 230.dp, width = 200.dp),
                                    onClick = { onShowMoreClick("Beach") },
                                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .background(Cardcontainer)
                                            .fillMaxHeight()
                                            .fillMaxWidth(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(text = "Show More", fontWeight = FontWeight.Bold)
                                    }
                                }
                                Spacer(modifier = Modifier.width(15.dp))
                            }
                        }
                    }
                }
                if (test.contains("Zoo")) {
                    item {
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            Image(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = R.drawable.zoo),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(
                                    MaterialTheme.colorScheme.primary
                                )
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Zoo",
                                color = MaterialTheme.colorScheme.text,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    item {
            val filter = placeRepo.filter { it.category.contains("Zoo") }
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                            item { Spacer(modifier = Modifier.width(0.01.dp)) }
                            items(filter.take(4)) { items ->
//                    placeCard(items , onClick = {onPlaceClick(items.title)})
                                placeCard(items, onClick = { onPlaceClick(items.id) }, onFav = {
                                    if (y.containsAll(favPlace.value)) {

                                    } else {
                                        favPlace.value.addAll(y)

                                    }

                                    if (items.id != null) {

                                        if (!items.fav.value) {
                                            favPlace.value.addAll(y)
                                            Toast.makeText(context, "liked", Toast.LENGTH_SHORT)
                                                .show()
                                            favPlace.value.add(items.id.toString())
                                            if (testcase.contains("${items.id}")) {

                                            } else {
                                                testcase.add("${items.id}")
                                            }

                                            testcase.distinct()
                                            testcase.remove("")
                                            FirebaseFirestore.getInstance().collection("favPlaces")
                                                .document("$userid")
                                                .set(favPlaceSelected, SetOptions.merge())
                                        } else {
                                            favPlace.value.addAll(y)
                                            Toast.makeText(context, "Disliked", Toast.LENGTH_SHORT)
                                                .show()
                                            favPlace.value.removeAll(listOf(items.id.toString()))
                                            while (testcase.contains("${items.id}")) {
                                                testcase.remove("${items.id}")
                                            }
                                            testcase.remove("")
                                            FirebaseFirestore.getInstance().collection("favPlaces")
                                                .document("$userid")
                                                .set(favPlaceSelected, SetOptions.merge())
                                        }
                                    }
                                })
                            }
                            item {
//                    Spacer(modifier = Modifier.width(15.dp))
                                ElevatedCard(modifier = Modifier
//                        .clip(RoundedCornerShape(20.dp))
                                    .size(height = 230.dp, width = 200.dp),
                                    onClick = { onShowMoreClick("Zoo") },
                                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .background(Cardcontainer)
                                            .fillMaxHeight()
                                            .fillMaxWidth(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(text = "Show More", fontWeight = FontWeight.Bold)
                                    }
                                }
                                Spacer(modifier = Modifier.width(15.dp))
                            }
                        }
                    }
                }
                if (test.contains("Spiritual")) {
                    item {
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            Image(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = R.drawable.spiritual),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(
                                    MaterialTheme.colorScheme.primary
                                )
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Spiritual",
                                color = MaterialTheme.colorScheme.text,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    item {
            val filter = placeRepo.filter { it.category.contains("Spiritual") }
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                            item { Spacer(modifier = Modifier.width(0.01.dp)) }
                            items(filter.take(4)) { items ->
//                    placeCard(items , onClick = {onPlaceClick(items.title)})
                                placeCard(items, onClick = { onPlaceClick(items.id) }, onFav = {
                                    if (y.containsAll(favPlace.value)) {

                                    } else {
                                        favPlace.value.addAll(y)

                                    }

                                    if (items.id != null) {

                                        if (!items.fav.value) {
                                            favPlace.value.addAll(y)
                                            Toast.makeText(context, "liked", Toast.LENGTH_SHORT)
                                                .show()
                                            favPlace.value.add(items.id.toString())
                                            if (testcase.contains("${items.id}")) {

                                            } else {
                                                testcase.add("${items.id}")
                                            }

                                            testcase.distinct()
                                            testcase.remove("")
                                            FirebaseFirestore.getInstance().collection("favPlaces")
                                                .document("$userid")
                                                .set(favPlaceSelected, SetOptions.merge())
                                        } else {
                                            favPlace.value.addAll(y)
                                            Toast.makeText(context, "Disliked", Toast.LENGTH_SHORT)
                                                .show()
                                            favPlace.value.removeAll(listOf(items.id.toString()))
                                            while (testcase.contains("${items.id}")) {
                                                testcase.remove("${items.id}")
                                            }
                                            testcase.remove("")
                                            FirebaseFirestore.getInstance().collection("favPlaces")
                                                .document("$userid")
                                                .set(favPlaceSelected, SetOptions.merge())
                                        }
                                    }
                                })
                            }
                            item {
//                    Spacer(modifier = Modifier.width(15.dp))
                                ElevatedCard(modifier = Modifier
//                        .clip(RoundedCornerShape(20.dp))
                                    .size(height = 230.dp, width = 200.dp),
                                    onClick = { onShowMoreClick("Spiritual") },
                                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .background(Cardcontainer)
                                            .fillMaxHeight()
                                            .fillMaxWidth(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(text = "Show More", fontWeight = FontWeight.Bold)
                                    }
                                }
                                Spacer(modifier = Modifier.width(15.dp))
                            }
                        }
                    }
                }
                if (test.contains("Hill Station")) {
                    item {
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            Image(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = R.drawable.mountain),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(
                                    MaterialTheme.colorScheme.primary
                                )
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Hill Station",
                                color = MaterialTheme.colorScheme.text,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    item {
                        val filter = placeRepo.filter { it.category.contains("Hill Station") }
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                            item { Spacer(modifier = Modifier.width(0.01.dp)) }
                            items(filter) { items ->
//                    placeCard(items , onClick = {onPlaceClick(items.title)})
                                placeCard(items, onClick = { onPlaceClick(items.id) }, onFav = {
                                    if (y.containsAll(favPlace.value)) {

                                    } else {
                                        favPlace.value.addAll(y)

                                    }

                                    if (items.id != null) {

                                        if (!items.fav.value) {
                                            favPlace.value.addAll(y)
                                            Toast.makeText(context, "liked", Toast.LENGTH_SHORT)
                                                .show()
                                            favPlace.value.add(items.id.toString())
                                            if (testcase.contains("${items.id}")) {

                                            } else {
                                                testcase.add("${items.id}")
                                            }

                                            testcase.distinct()
                                            testcase.remove("")
                                            FirebaseFirestore.getInstance().collection("favPlaces")
                                                .document("$userid")
                                                .set(favPlaceSelected, SetOptions.merge())
                                        } else {
                                            favPlace.value.addAll(y)
                                            Toast.makeText(context, "Disliked", Toast.LENGTH_SHORT)
                                                .show()
                                            favPlace.value.removeAll(listOf(items.id.toString()))
                                            while (testcase.contains("${items.id}")) {
                                                testcase.remove("${items.id}")
                                            }
                                            testcase.remove("")
                                            FirebaseFirestore.getInstance().collection("favPlaces")
                                                .document("$userid")
                                                .set(favPlaceSelected, SetOptions.merge())
                                        }
                                    }
                                })
                            }
                            item {
//                    Spacer(modifier = Modifier.width(15.dp))
                                Spacer(modifier = Modifier.width(15.dp))
                            }
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(15.dp))
                }
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Image(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = R.drawable.city),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(
                                MaterialTheme.colorScheme.primary
                            )
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "City",
                            color = MaterialTheme.colorScheme.text,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }
                item {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                        item { Spacer(modifier = Modifier.width(0.01.dp)) }
                        var filter = placeRepo.filter { it.category.contains("City") }
                        items(filter.take(4)) { items ->
//                    placeCard(items , onClick = {onPlaceClick(items.title)})
                            placeCardCity(items, onClick = {
                                onCityClick(items.id)
                            }, onFav = {
                                if (y.containsAll(favPlace.value)) {

                                } else {
                                    favPlace.value.addAll(y)

                                }

                                if (items.id != null) {

                                    if (!items.fav.value) {
                                        favPlace.value.addAll(y)
                                        Toast.makeText(context, "liked", Toast.LENGTH_SHORT).show()
                                        favPlace.value.add(items.id.toString())
                                        if (testcase.contains("${items.id}")) {

                                        } else {
                                            testcase.add("${items.id}")
                                        }

                                        testcase.distinct()
                                        testcase.remove("")
                                        FirebaseFirestore.getInstance().collection("favPlaces")
                                            .document("$userid")
                                            .set(favPlaceSelected, SetOptions.merge())
                                    } else {
                                        favPlace.value.addAll(y)
                                        Toast.makeText(context, "Disliked", Toast.LENGTH_SHORT)
                                            .show()
                                        favPlace.value.removeAll(listOf(items.id.toString()))
                                        while (testcase.contains("${items.id}")) {
                                            testcase.remove("${items.id}")
                                        }
                                        testcase.remove("")
                                        FirebaseFirestore.getInstance().collection("favPlaces")
                                            .document("$userid")
                                            .set(favPlaceSelected, SetOptions.merge())
                                    }
                                }
                            })
                        }
                        item {
//                    Spacer(modifier = Modifier.width(15.dp))
                            ElevatedCard(modifier = Modifier


//                        .clip(RoundedCornerShape(20.dp))
                                .size(height = 230.dp, width = 200.dp),
                                onClick = { onShowMoreClick("City") },
                                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .background(Cardcontainer)
                                        .fillMaxHeight()
                                        .fillMaxWidth(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(text = "Show More", fontWeight = FontWeight.Bold)
                                }
                            }
                            Spacer(modifier = Modifier.width(15.dp))
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(15.dp))
                }


//            LottieAnimation(composition = composition, modifier = Modifier.size(200.dp), iterations = LottieConstants.IterateForever)


            }
        }

    }

@Composable
fun top(viewModel: locationviewmodel) {
    val context = LocalContext.current
    val locationUtils = locationUtils(context)
    locationdisplay(context = context, locationUtils = locationUtils,viewModel)
}

@Composable
fun locationdisplay(context: Context,locationUtils: locationUtils,viewModel: locationviewmodel) {

    var city = ""
    val dataviewmodel:dataviewmodel = viewModel()
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
                    Toast.makeText(context,"location required",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(context,"enable using settings",Toast.LENGTH_LONG).show()
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
fun placeCard(items:Places,onClick:() -> Unit,onFav:() -> Unit) {
    var selected by rememberSaveable {
        mutableStateOf(false)
    }
    ElevatedCard (modifier = Modifier
//        .clip(RoundedCornerShape(20.dp))
        .size(height = 230.dp, width = 200.dp),onClick = onClick, elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)){
        Column(modifier = Modifier
            .background(Cardcontainer)
            .fillMaxHeight()) {
            Box (modifier = Modifier,contentAlignment = Alignment.TopEnd){
                Image(modifier = Modifier
                    .clip(RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp))
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.6f)
                    .background(Color.Gray)
                    , contentScale = ContentScale.Crop,painter = rememberAsyncImagePainter(model = items.picture.get(0)), contentDescription = null)
               FloatingActionButton(shape = CircleShape,onClick = onFav, modifier = Modifier
                   .padding(end = 4.dp, top = 4.dp)
                   .size(40.dp), containerColor = Color.Black) {
                   Icon(Icons.Default.Favorite, contentDescription = null, tint = if(items.fav.value == true) Color.Red else Color.White,modifier = Modifier.clickable {
                       onFav.invoke()
                       items.fav.value = !items.fav.value
                   })
               }
            }
            Spacer(modifier = Modifier.height(7.dp))
            
            Row(modifier = Modifier) {
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = items.title, color = MaterialTheme.colorScheme.text, fontWeight = FontWeight.Bold, fontSize = if(items.title.length < 10)  18.sp else 14.sp)
            }
            Row (modifier = Modifier,horizontalArrangement = Arrangement.spacedBy(2.dp)){
                Spacer(modifier = Modifier.width(1.dp))
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(5.dp))
                Icon(Icons.Filled.LocationOn, contentDescription = null, modifier = Modifier.size(15.dp))
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = items.city,fontSize = 13.sp)
//                if(items.title.length >= 15){
//                    Text(items.city)
//                }
//                else{
//                    Text(text = items.title+", "+items.city)
//                }

            }
        }

    }

}


@Composable
fun placeCardCity(items:Places,onClick:() -> Unit,onFav:() -> Unit) {
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
            Box (modifier = Modifier,contentAlignment = Alignment.TopEnd){
                Image(modifier = Modifier
                    .clip(RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp))
                    .fillMaxWidth(1f)
                    .fillMaxHeight(1f)
                    .background(Color.Gray)
                    , contentScale = ContentScale.Crop,painter = rememberAsyncImagePainter(model = items.picture.get(0)), contentDescription = null)
                FloatingActionButton(shape = CircleShape,onClick = onFav, modifier = Modifier
                    .padding(end = 4.dp, top = 4.dp)
                    .size(40.dp), containerColor = Color.Black) {
                    Icon(Icons.Default.Favorite, contentDescription = null, tint = if(items.fav.value == true) Color.Red else Color.White,
                        modifier = Modifier.clickable {
                            onFav.invoke()
                            items.fav.value = !items.fav.value
                        }
                    )
                }
                Box(modifier = Modifier
                    .padding(start = 10.dp, bottom = 10.dp)
                    .fillMaxSize(),contentAlignment = Alignment.BottomStart) {
                    Text(text = items.title, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.White)
                }
            }
        }

    }

}

