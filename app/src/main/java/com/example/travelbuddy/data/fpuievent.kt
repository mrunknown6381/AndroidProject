package com.example.travelbuddy.data

sealed class fpuievent {
    data class emailchanged(val email:String) : fpuievent()
    object fpbtnclicked : fpuievent()
}