package com.example.travelbuddy.features.registration

import androidx.compose.ui.graphics.vector.ImageVector

//it holds value that user are typing and changes frequently
sealed class reguievent{
    data class namechanged(val name:String) : reguievent()
    data class unamechanged(val uname:String) : reguievent()
    data class emailchanged(val email:String) : reguievent()
    data class passchanged(val pass:String) : reguievent()
    data class termsclicked(val status:Boolean) : reguievent()
    object regbtnclicked : reguievent()

}