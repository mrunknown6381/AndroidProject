package com.example.travelbuddy.data

sealed class loginuievent {
    data class emailchanged(val email:String) : loginuievent()
    data class passchanged(val pass:String) : loginuievent()
    object loginbtnclicked : loginuievent()
}