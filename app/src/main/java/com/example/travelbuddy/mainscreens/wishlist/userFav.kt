package com.example.travelbuddy.mainscreens.wishlist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.travelbuddy.data.placeRepo
import com.example.travelbuddy.mainscreens.profile.UserChoice.getPrefdataF
import com.example.travelbuddy.mainscreens.profile.pfpData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class userFav:ViewModel(){
    val state = mutableStateOf(favData())
    init {
        getdata()
    }
    private fun getdata(){
        viewModelScope.launch {
            state.value = getFavdataF()
        }
    }
}
suspend fun getFavdataF(): favData {
    val db = FirebaseFirestore.getInstance()
    var cloudData = favData()
    var uid = FirebaseAuth.getInstance().currentUser?.uid
    try{
        db.collection("favPlaces").whereEqualTo("user_id","$uid").get().await().map {
            val rslt = it.toObject(favData::class.java)
            cloudData = rslt
        }

    }catch (e: FirebaseFirestoreException){

    }
    return cloudData
}