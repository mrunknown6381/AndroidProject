package com.example.travelbuddy.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.travelbuddy.R
import com.example.travelbuddy.navigation.screens
import com.example.travelbuddy.appcomponents.clickabletextf
import com.example.travelbuddy.appcomponents.clickabletextl
import com.example.travelbuddy.appcomponents.loadanim
import com.example.travelbuddy.appcomponents.passtextfeild
import com.example.travelbuddy.appcomponents.textbuttonRL
import com.example.travelbuddy.appcomponents.textfeild
import com.example.travelbuddy.data.dimens
import com.example.travelbuddy.data.loginuievent
import com.example.travelbuddy.data.loginviewmodel
import com.example.travelbuddy.data.reguievent
import com.example.travelbuddy.data.regviewmodel


@Composable
fun loginscreen(navController: NavController, loginviewmodel: loginviewmodel = viewModel()) {
    val context = LocalContext.current
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        topsection()
        Spacer(modifier = Modifier.height(36.dp))
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp), horizontalAlignment = Alignment.CenterHorizontally){
            if(loginviewmodel.success.value){
                Toast.makeText(context,"Login Success",Toast.LENGTH_SHORT).show()
                navController.navigate(screens.Screenhome.route)
            }
            if (loginviewmodel.failure.value){
                Toast.makeText(context,"Invalid Credential",Toast.LENGTH_SHORT).show()
            }
            textfeild(modifier = Modifier.fillMaxWidth(), label = "Email", trailing = ""
                ,painterResource(id = R.drawable.email), onTextSelected = {
                    loginviewmodel.onevent(loginuievent.emailchanged(it))
                })
            Spacer(modifier = Modifier.height(15.dp))
            passtextfeild(modifier = Modifier.fillMaxWidth(), label = "Password", trailing = "",
                painterResource(id = R.drawable.password), onTextSelected = {
                    loginviewmodel.onevent(loginuievent.passchanged(it))
                })
            Spacer(modifier = Modifier.height(5.dp))
            Box (modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.05f), contentAlignment = Alignment.CenterEnd){
                clickabletextf(value = String(), onTextSelected = {
                    navController.navigate(screens.Screenforget.route)
                })
            }
            Spacer(modifier = Modifier.height(25.dp))
            textbuttonRL(text = "Login", onClick = {
            }, onbuttonclicked = {
                loginviewmodel.onevent(loginuievent.loginbtnclicked)
            },isEnabled = loginviewmodel.allvalidationpassed.value)
            Box (modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f), contentAlignment = Alignment.Center){
               clickabletextl(value = stringResource(id = R.string.reg), onTextSelected = {
                   navController.navigate(screens.Screenregister.route)
               })
            }
            if (loginviewmodel.signininprogress.value){
                loadanim()
            }
        }
    }
}

@Composable
private fun topsection() {
    Box(modifier = Modifier, contentAlignment = Alignment.TopCenter) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.shape),
            contentDescription = null
        )
        Row(
            modifier = Modifier.padding(top = 120.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(35.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(7.dp))
            Column {
                Text(
                    text = "Travel Buddy",
                    modifier = Modifier.padding(horizontal = dimens.MP1),
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Smarter your Journey",
                    modifier = Modifier.padding(horizontal = dimens.MP1),
                    style = MaterialTheme.typography.titleSmall.copy(fontStyle = FontStyle.Italic),
                    color = MaterialTheme.colorScheme.primary
                )

            }
        }
        Text(
            text = "Login your account",
            modifier = Modifier
                .padding(bottom = 10.dp)
                .align(alignment = Alignment.BottomCenter),
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )

    }
}