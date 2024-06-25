package com.example.travelbuddy.location

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class locationviewmodel:ViewModel() {
    private val _location = mutableStateOf<locationdata?>(null)
    val location: State<locationdata?> = _location

    fun updatelocation(newLocation:locationdata){
        _location.value = newLocation
    }
}