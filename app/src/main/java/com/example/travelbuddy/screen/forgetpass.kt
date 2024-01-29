package com.example.travelbuddy.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.travelbuddy.R
import com.example.travelbuddy.appcomponents.basetextfeild
import com.example.travelbuddy.appcomponents.loadanim
import com.example.travelbuddy.appcomponents.textbuttonRL
import com.example.travelbuddy.appcomponents.textbuttonwlcm
import com.example.travelbuddy.data.dimens
import com.example.travelbuddy.data.fpuievent
import com.example.travelbuddy.data.fpviewmodel
import com.example.travelbuddy.data.loginuievent
import com.example.travelbuddy.navigation.screens


@Composable
fun forgetpass(navController: NavController, fpviewmodel: fpviewmodel = viewModel()) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .padding(16.dp)
            .fillMaxWidth()
    ) {
            Column (
                modifier = Modifier.fillMaxSize().fillMaxWidth().fillMaxHeight(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                if(fpviewmodel.success.value){
                    Toast.makeText(context,"Reset Link Sent to Your Mail", Toast.LENGTH_SHORT).show()
                    navController.navigate(screens.Screenlogin.route)
                }
                if (fpviewmodel.failure.value){
                    Toast.makeText(context,"Invalid Credential", Toast.LENGTH_SHORT).show()
                }
                Spacer(modifier = Modifier.height(120.dp))
                Text(text = "Forget Password", textAlign = TextAlign.Center, modifier = Modifier.padding(horizontal = dimens.MP1), style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color(0xFF6750A4))
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Enter your email we will send your password ", textAlign = TextAlign.Center, modifier = Modifier.padding(horizontal = dimens.MP1), style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = Color(0xFF6750A4))
                Spacer(modifier = Modifier.height(30.dp))
                basetextfeild(modifier = Modifier.fillMaxWidth(), label = "Email", trailing = "",
                    painterResource(id = R.drawable.email), onTextSelected = {
                        fpviewmodel.onevent(fpuievent.emailchanged(it))
                    })
                Spacer(modifier = Modifier.height(30.dp))
                textbuttonRL(text = "Reset", onClick = {
                }, onbuttonclicked = {
                    fpviewmodel.onevent(fpuievent.fpbtnclicked)
                },isEnabled = fpviewmodel.allvalidationpassed.value)
                Spacer(modifier = Modifier.height(60.dp))
                if (fpviewmodel.fpinprogress.value){
                    loadanim()
                }
            }
    }
    }

