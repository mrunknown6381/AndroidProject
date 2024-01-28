package com.example.travelbuddy.data
//it holds value that user are typing and changes frequently
sealed class reguievent{
    data class fnamechanged(val fname:String) : reguievent()
    data class lnamechanged(val lname:String) : reguievent()
    data class emailchanged(val email:String) : reguievent()
    data class passchanged(val pass:String) : reguievent()
    data class termsclicked(val status:Boolean) : reguievent()
    object regbtnclicked : reguievent()

}