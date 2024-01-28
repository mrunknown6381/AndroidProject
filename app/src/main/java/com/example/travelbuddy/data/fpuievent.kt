package com.example.travelbuddy.data
//it holds value that user are typing and changes frequently
sealed class fpuievent {
    data class emailchanged(val email:String) : fpuievent()
    object fpbtnclicked : fpuievent()
}