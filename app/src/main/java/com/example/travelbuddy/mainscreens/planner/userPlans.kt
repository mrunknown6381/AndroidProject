package com.example.travelbuddy.mainscreens.planner

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelbuddy.mainscreens.wishlist.favData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class userPlans: ViewModel(){
    val state = mutableStateOf(favPlans())
    init {
        getdata()
    }
    private fun getdata(){
        viewModelScope.launch {
            state.value = getFavplansdataF()
        }
    }
}
suspend fun getFavplansdataF(): favPlans {
    val db = FirebaseFirestore.getInstance()
    var cloudData = favPlans()
    var uid = FirebaseAuth.getInstance().currentUser?.uid
    try{
        db.collection("favPlans").whereEqualTo("user_id","$uid").get().await().map {
            val rslt = it.toObject(favPlans::class.java)
            cloudData = rslt
        }

    }catch (e: FirebaseFirestoreException){

    }
    return cloudData
}