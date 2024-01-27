package com.example.travelbuddy.data

sealed class reguievent{
    data class fnamechanged(val fname:String) : reguievent()
    data class lnamechanged(val lname:String) : reguievent()
    data class emailchanged(val email:String) : reguievent()
    data class passchanged(val pass:String) : reguievent()
    data class termsclicked(val status:Boolean) : reguievent()
    object regbtnclicked : reguievent()

}