package com.example.travelbuddy.data.rules

import android.annotation.SuppressLint
import java.util.regex.Pattern

//it validates the all things like email,pass etc
object validator {
    fun validateuname(uname:String):validationresult{

        return validationresult(
            (!uname.isNullOrEmpty() && uname.length>=2 && uname.startsWith("@"))
        )
    }
    fun validatename(name:String):validationresult{
        return validationresult(
            (!name.isNullOrEmpty() && name.length>=2)
        )
    }

    @SuppressLint("SuspiciousIndentation")
    fun validateemail(email:String):validationresult{
//        val pattern = Pattern.compile(   "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}\$")
//        val match = pattern.matcher(email)
            return validationresult(
//                match.matches()
                (!email.isNullOrEmpty() && email.length>=2)
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