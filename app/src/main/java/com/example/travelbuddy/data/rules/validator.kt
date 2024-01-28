package com.example.travelbuddy.data.rules
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
        return validationresult(
            (!email.isNullOrEmpty())
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