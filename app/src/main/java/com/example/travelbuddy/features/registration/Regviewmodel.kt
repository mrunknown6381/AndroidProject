package com.example.travelbuddy.features.registration

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.travelbuddy.data.rules.validator
import com.example.travelbuddy.mainscreens.usermodel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.storage


//it captures the event and update or changes the state
class regviewmodel ():ViewModel() {
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseDatabase.getInstance()

    //    val userRef = db.getReference("users/${UUID.randomUUID()}.jpg")
    private val storageRef = Firebase.storage.reference
    private val imageref = storageRef.child("userRecords")
    private val _firebaseUser = MutableLiveData<FirebaseUser>()
    val firebaseuser: LiveData<FirebaseUser> = _firebaseUser

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        _firebaseUser.value = auth.currentUser
    }

    private val Tag = regviewmodel::class.simpleName
    var reguistate = mutableStateOf(reguistate())
    var allvalidationpassed = mutableStateOf(false)
    var signupinprogress = mutableStateOf(false)
    var success = mutableStateOf(false)
    var failure = mutableStateOf(false)


    fun onevent(event: reguievent) {
        when (event) {
            is reguievent.namechanged -> {
                reguistate.value = reguistate.value.copy(
                    name = event.name
                )
            }
            is reguievent.unamechanged -> {
                reguistate.value = reguistate.value.copy(
                    uname = event.uname
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
            pass = reguistate.value.pass,
            uname = reguistate.value.uname,
            name = reguistate.value.name
        )

    }

    private fun validatedatawithrules() {
        val nameres = validator.validatename(
            name = reguistate.value.name
        )
        val unameres = validator.validateuname(
            uname = reguistate.value.uname
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
        allvalidationpassed.value =
            unameres.status && emailres.status && passres.status && tacresult.status && nameres.status
        reguistate.value = reguistate.value.copy(
            nameerr = nameres.status,
            unameerr = unameres.status,
            emailerr = emailres.status,
            passerr = passres.status,
            termserr = tacresult.status,
        )

    }


    fun createuser(email: String, pass: String, uname: String,name:String) {
        signupinprogress.value = true
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener {
                Log.d(Tag, "succesfull")
                Log.d(Tag, "${it.isSuccessful}")
                signupinprogress.value = false
                if (it.isSuccessful) {
                    success.value = true
                    _firebaseUser.postValue(auth.currentUser)
                    addUserDb(uname,email,name)
                }

            }

            .addOnFailureListener {
                Log.d(Tag, "${it.message}")
                Log.d(Tag, "${it.localizedMessage}")
                failure.value = true
            }


    }

    private fun addUserDb(uname: String, email: String,name: String) {
        val userid = auth.currentUser?.uid
        val user = usermodel(id = null,userid = userid.toString(),name = name.toString(), username = uname.toString(),
            email = email.toString(), bio = "Set Your Bio", city = "").toMap()

        FirebaseFirestore.getInstance().collection("users").document("$userid")
            .set(user)
    }
}
