package com.example.travelbuddy.features.registration

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.navigation.NavHostController
import com.example.travelbuddy.R
import com.example.travelbuddy.appcomponents.checkbox
import com.example.travelbuddy.appcomponents.clickabletextr
import com.example.travelbuddy.appcomponents.loadanim
import com.example.travelbuddy.appcomponents.passtextfeild
import com.example.travelbuddy.appcomponents.textbuttonRL
import com.example.travelbuddy.appcomponents.textfeild
import com.example.travelbuddy.data.dimens
import com.example.travelbuddy.navigation.routes

@Composable
fun registerscreen(navController: NavHostController, regviewmodel: regviewmodel = viewModel()) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        topsection1()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center

        ) {
            if(regviewmodel.success.value){
                Toast.makeText(context,"Registration Success",Toast.LENGTH_SHORT).show()
                navController.navigate(routes.initCat.routes)
            }
            if (regviewmodel.failure.value){
                Toast.makeText(context,"Invalid Credential",Toast.LENGTH_SHORT).show()
            }

            Spacer(modifier = Modifier.height(25.dp))
            textfeild(modifier = Modifier.fillMaxWidth(), label = "Name",painterResource(id = R.drawable.name), onTextSelected = {regviewmodel.onevent(
                reguievent.namechanged(it))},errorStatus = regviewmodel.reguistate.value.nameerr)
            Spacer(modifier = Modifier.height(15.dp))
            textfeild(modifier = Modifier.fillMaxWidth(), label = "Username",painterResource(id = R.drawable.name), onTextSelected = {regviewmodel.onevent(
                reguievent.unamechanged(it))},errorStatus = regviewmodel.reguistate.value.unameerr)
            Spacer(modifier = Modifier.height(15.dp))
            textfeild(modifier = Modifier.fillMaxWidth(), label = "Email",painterResource(id = R.drawable.email), onTextSelected = {regviewmodel.onevent(
                reguievent.emailchanged(it))},errorStatus = regviewmodel.reguistate.value.emailerr)
            Spacer(modifier = Modifier.height(15.dp))
            passtextfeild(modifier = Modifier.fillMaxWidth(), label = "Password", trailing = "",painterResource(id = R.drawable.password), onTextSelected = {regviewmodel.onevent(
                reguievent.passchanged(it))},errorStatus = regviewmodel.reguistate.value.passerr)
            checkbox(value = stringResource(id = R.string.tac), onTextSelected = {
                navController.navigate(routes.Screentac.routes)
            }, oncheckedchange = {regviewmodel.onevent(reguievent.termsclicked(it))})
            Spacer(modifier = Modifier.height(10.dp))
            textbuttonRL(text = "Register", onClick = {
            }, onbuttonclicked = {
                regviewmodel.onevent(reguievent.regbtnclicked)
            },isEnabled = regviewmodel.allvalidationpassed.value)
            Box (modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f), contentAlignment = Alignment.Center){
               clickabletextr(value = stringResource(id = R.string.login), onTextSelected = {
                   navController.navigate(routes.initCat.routes)
               })
            }
           if (regviewmodel.signupinprogress.value){
               loadanim()
           }
        }
    }

}

@Composable
fun topsection1() {
    Box(modifier = Modifier, contentAlignment = Alignment.TopCenter) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.shape),
            contentDescription = null
        )
        Row(
            modifier = Modifier.padding(top = 70.dp),
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
            text = "Create an account",
            modifier = Modifier
                .padding(bottom = 10.dp)
                .align(alignment = Alignment.BottomCenter),
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )

    }
}

