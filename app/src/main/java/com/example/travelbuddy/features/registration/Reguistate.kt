package com.example.travelbuddy.features.registration

//it holds value
data class reguistate(
    var name:String = "",
    var uname:String = "",
    var email:String = "",
    var pass:String = "",
    var termsandcondition:Boolean = false,
    var nameerr:Boolean = false,
    var unameerr:Boolean = false,
    var emailerr: Boolean = false,
    var passerr: Boolean = false,
    var termserr: Boolean = false,

    )