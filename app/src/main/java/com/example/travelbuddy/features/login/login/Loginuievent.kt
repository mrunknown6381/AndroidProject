package com.example.travelbuddy.features.login.login
//it holds value that user are typing and changes frequently
sealed class loginuievent {
    data class emailchanged(val email:String) : loginuievent()
    data class passchanged(val pass:String) : loginuievent()
    object loginbtnclicked : loginuievent()
}