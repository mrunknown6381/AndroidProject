package com.example.travelbuddy.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import javax.annotation.concurrent.Immutable

@Immutable
data class Places(
    val id:Int,
    val title:String,
    var rating:Double,
    val picture:List<String>,
    val disc:String,
    val category:List<String>,
    val city:String,
    val location:String,
    var fav:MutableState<Boolean> =  mutableStateOf(false),
    val reviews:List<String>,
    val duration:Int
)

// add city and
