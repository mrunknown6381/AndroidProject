package com.example.travelbuddy.data

data class plans(
    val id:String,
    val title: String,
    val budget:String,
    val city:List<String> = listOf<String>(),
    val days:String,
    val hotels:List<String> = listOf<String>(),
    val img:String,
    val sights:List<String> = listOf<String>(),
    val sightsName:List<String> = listOf<String>()
)
