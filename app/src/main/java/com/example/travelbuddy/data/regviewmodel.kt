package com.example.travelbuddy.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.travelbuddy.data.rules.validator
import com.google.firebase.auth.FirebaseAuth

class regviewmodel ():ViewModel() {

    private val Tag = regviewmodel::class.simpleName
    var reguistate = mutableStateOf(reguistate())
    var allvalidationpassed = mutableStateOf(false)
    var signupinprogress = mutableStateOf(false)
    var success = mutableStateOf(false)
    var failure = mutableStateOf(false)
    fun onevent(event:reguievent){
        when(event){
            is reguievent.fnamechanged -> {
                reguistate.value = reguistate.value.copy(
                    fname = event.fname
                )
            }
            is reguievent.lnamechanged -> {
                reguistate.value = reguistate.value.copy(
                    lname = event.lname
                )
            }
            is reguievent.emailchanged -> {
                reguistate.value = reguistate.value.copy(
                    email = event.email
                )
            }
            is reguievent.passchanged -> {
                reguistate.value = reguistate.value.copy(
                    pass = event.pass
                )
            }
            is reguievent.regbtnclicked -> {
                signup()

            }
            is reguievent.termsclicked -> {
                reguistate.value = reguistate.value.copy(
                    termsandcondition = event.status
                )
            }
        }
        validatedatawithrules()
    }

    private fun signup() {
        createuser(
            email = reguistate.value.email,
            pass = reguistate.value.pass

        )

    }

    private fun validatedatawithrules() {

        val fnameres = validator.validatefname(
            fname = reguistate.value.fname
        )
        val lnameres = validator.validatelname(
            lname = reguistate.value.lname
        )
        val emailres = validator.validateemail(
            email = reguistate.value.email
        )
        val passres = validator.validatepass(
            pass = reguistate.value.pass
        )
        val tacresult = validator.tacaccept(
            statusval = reguistate.value.termsandcondition
        )
        allvalidationpassed.value = fnameres.status && lnameres.status && emailres.status && passres.status && tacresult.status
        reguistate.value = reguistate.value.copy(
            fnameerr = fnameres.status,
            lnameerr = lnameres.status,
            emailerr = emailres.status,
            passerr = passres.status,
            termserr = tacresult.status,
        )

    }



    fun createuser(email:String,pass:String){
        signupinprogress.value = true
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email,pass)
            .addOnCompleteListener {
                Log.d(Tag,"succesfull")
                Log.d(Tag,"${it.isSuccessful}")
                signupinprogress.value = false
                if (it.isSuccessful){
                    success.value = true
                }

            }

            .addOnFailureListener{
                Log.d(Tag,"${it.message}")
                Log.d(Tag,"${it.localizedMessage}")
                failure.value = true
            }


    }
}