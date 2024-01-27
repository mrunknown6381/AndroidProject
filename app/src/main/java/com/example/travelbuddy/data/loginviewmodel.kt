package com.example.travelbuddy.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.travelbuddy.data.rules.validator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener

class loginviewmodel :ViewModel(){
    private val Tag = regviewmodel::class.simpleName
    var loginuistate = mutableStateOf(loginuistate())
    var allvalidationpassed = mutableStateOf(false)
    var signininprogress = mutableStateOf(false)
    var success = mutableStateOf(false)
    var failure = mutableStateOf(false)
    var logout = mutableStateOf(false)
    fun onevent(event:loginuievent){

        when(event){
            is loginuievent.emailchanged -> {
                loginuistate.value = loginuistate.value.copy(
                    email = event.email
                )
            }
            is loginuievent.passchanged -> {
                loginuistate.value = loginuistate.value.copy(
                    pass = event.pass
                )
            }
            is loginuievent.loginbtnclicked -> {
                login()

            }
        }
        validatedatawithrules()
}

    private fun login() {
        signininprogress.value = true
        val email = loginuistate.value.email
        val pass = loginuistate.value.pass
        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email,pass)
            .addOnCompleteListener {
                Log.d(Tag,"${it.isSuccessful}")
                if (it.isSuccessful){
                    success.value = true
                    signininprogress.value = false
                }
            }
            .addOnFailureListener{
                Log.d(Tag,"Failure")
                failure.value = true
                signininprogress.value = false
            }
    }

    private fun validatedatawithrules() {
        val emailres = validator.validateemail(
            email = loginuistate.value.email
        )
        val passres = validator.validatepass(
            pass = loginuistate.value.pass
        )
        loginuistate.value = loginuistate.value.copy(
            emailerr = emailres.status,
            passerr = passres.status,
        )
        allvalidationpassed.value = emailres.status && passres.status
    }

    fun logout(){
        val firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signOut()
        val authStateListener = AuthStateListener{
            if(it.currentUser == null){
                logout.value = true
            }
        }
        firebaseAuth.addAuthStateListener(authStateListener)
    }
}
