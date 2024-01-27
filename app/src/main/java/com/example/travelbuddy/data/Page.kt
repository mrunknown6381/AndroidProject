package com.example.travelbuddy.data

import androidx.annotation.DrawableRes
import com.example.travelbuddy.R

data class Page(
    val title: String,
    val desc: String,
    @DrawableRes val img: Int
)
val pages = listOf(
    Page(
        title = "Lets explore the world",
        desc = "Find thousands of tourist destinations ready for you to visit.",
        img = R.drawable.ob1
    ),
    Page(
        title = "Create & Organize your travel Journey",
        desc = "It allows users to efficiently organize and manage their travel arrangements.",
        img  = R.drawable.ob2
    ),
    Page(
        title = "Let the Adventure Begin",
        desc = "Sign up to start your journey and unlock exclusive features.",
        img = R.drawable.ob3
    )
)