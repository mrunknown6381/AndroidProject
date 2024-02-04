package com.example.travelbuddy.features.registration
//it holds value
data class reguistate(
    var fname :String = "",
    var lname :String = "",
    var email :String = "",
    var pass :String = "",
    var termsandcondition :Boolean = false,
    var fnameerr : Boolean = false,
    var lnameerr : Boolean = false,
    var emailerr : Boolean = false,
    var passerr : Boolean = false,
    var termserr: Boolean = false,

)