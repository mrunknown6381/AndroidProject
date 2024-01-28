package com.example.travelbuddy.appcomponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//function that holds button and their properties

@Composable
fun textbutton(text: String,onClick: () -> Unit) {
    TextButton(modifier = Modifier,onClick = onClick,shape = RoundedCornerShape(size = 30.dp), colors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.onSecondaryContainer,
        disabledContainerColor = MaterialTheme.colorScheme.onPrimary
    )) {
        Text(text = text, style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.background)
    }
}


@Composable
fun textbuttonwlcm(text: String,onClick: () -> Unit,onbuttonclicked :() -> Unit) {
    TextButton(modifier = Modifier.width(315.dp),onClick = { onbuttonclicked.invoke() }, elevation = ButtonDefaults.buttonElevation(
        defaultElevation = 10.dp,
        pressedElevation = 20.dp
    )
        ,shape = RoundedCornerShape(size = 10.dp), border = BorderStroke(0.7.dp, color = Black), colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onSecondaryContainer

        )) {
        Text(text = text, modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp), fontSize = 20.sp, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.background)

    }
}
@Composable
fun textbuttonRL(text: String,onClick: () -> Unit,onbuttonclicked :() -> Unit,isEnabled:Boolean = false) {
    TextButton(modifier = Modifier.width(315.dp),onClick = { onbuttonclicked.invoke() }, elevation = ButtonDefaults.buttonElevation(
        defaultElevation = 10.dp,
        pressedElevation = 20.dp
    ), enabled = isEnabled
        ,shape = RoundedCornerShape(size = 10.dp), border = BorderStroke(0.7.dp, color = Black), colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onSecondaryContainer

        )) {
        Text(text = text, modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp), fontSize = 20.sp, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.background)

    }
}


