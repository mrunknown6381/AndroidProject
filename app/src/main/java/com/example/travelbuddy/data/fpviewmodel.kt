package com.example.travelbuddy.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.travelbuddy.data.rules.validator
import com.google.firebase.auth.FirebaseAuth

class fpviewmodel:ViewModel() {
    var fpuistate = mutableStateOf(fpuistate())
    var allvalidationpassed = mutableStateOf(false)
    var fpinprogress = mutableStateOf(false)
    var success = mutableStateOf(false)
    var failure = mutableStateOf(false)
    private val Tag = fpviewmodel::class.simpleName
    fun onevent(event:fpuievent){
        when(event){
            is fpuievent.emailchanged -> {
                fpuistate.value = fpuistate.value.copy(
                    email = event.email
                )
            }
            is fpuievent.fpbtnclicked -> {
                forgetpassword()

            }
        }
        validatedatawithrules3()
    }

    private fun validatedatawithrules3() {
        val emailres = validator.validateemail(
            email = fpuistate.value.email
        )
        fpuistate.value = fpuistate.value.copy(
            emailerr = emailres.status,
        )
        allvalidationpassed.value = emailres.status
    }

    fun forgetpassword(){
        fpinprogress.value = true
        val auth = FirebaseAuth.getInstance()
        val email = fpuistate.value.email
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener {
                Log.d(Tag,"${it.isSuccessful}")
                if (it.isSuccessful){
                    success.value = true
                    fpinprogress.value = false
                }
            }
            .addOnFailureListener{
                Log.d(Tag,"Failure")
                failure.value = true
                fpinprogress.value = false
            }
    }
}