package com.example.travelbuddy.data.rules

import java.util.regex.Pattern

//it validates the all things like email,pass etc
object validator {
    fun validatefname(fname:String):validationresult{
        return validationresult(
            (!fname.isNullOrEmpty() && fname.length>=2)
        )
    }
    fun validatelname(lname:String):validationresult{
        return validationresult(
            (!lname.isNullOrEmpty() && lname.length>=4)
        )
    }
    fun validateemail(email:String):validationresult{
        val pattern = Pattern.compile(   "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}\$")
        val match = pattern.matcher(email)
            return validationresult(
                match.matches()
        )
    }
    fun validatepass(pass:String):validationresult{
        return validationresult(
            (!pass.isNullOrEmpty()&& pass.length>=4 )
        )
    }
    fun tacaccept(statusval:Boolean):validationresult{
        return validationresult(
            statusval
        )
    }
}

data class validationresult(
    val status :Boolean = false
)