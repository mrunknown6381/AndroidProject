package com.example.travelbuddy.features.login.forgetpassword
//it holds value that user are typing and changes frequently
sealed class fpuievent {
    data class emailchanged(val email:String) : fpuievent()
    object fpbtnclicked : fpuievent()
}