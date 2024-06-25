package com.example.travelbuddy.appcomponents

import android.content.ClipData
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.travelbuddy.data.plans
import com.google.firebase.firestore.FirebaseFirestore

class MyViewModel : ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()

    val itemList: MutableState<List<plans>> = mutableStateOf(emptyList())

    init {
        fetchItems()
    }

    private fun fetchItems() {
        firestore.collection("test")
            .get()
            .addOnSuccessListener { documents ->
                val items = mutableListOf<plans>()
                for (document in documents) {
                    val data = document.data
                    // Map Firestore document data to your custom Content class
                    val item = plans(
                        data["id"] as String,
                        data["title"] as String,
                        data["budget"] as String,
                        data["city"] as List<String>,
                        data["days"] as String,
                        data["hotels"] as List<String>,
                        data["img"] as String,
                        data["sights"] as List<String>,
                        data["sightsName"] as List<String>
                    )
                    items.add(item)
                }
                itemList.value = items
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error getting documents: ", exception)
            }
    }
}