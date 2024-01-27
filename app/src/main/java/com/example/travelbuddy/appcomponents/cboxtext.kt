package com.example.travelbuddy.appcomponents

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun checkbox(value:String,onTextSelected: (String) -> Unit ,oncheckedchange :(Boolean) -> Unit) {
    Row (modifier = Modifier
        .fillMaxWidth()
        .heightIn(56.dp)
        .padding(10.dp), verticalAlignment = Alignment.CenterVertically,){
        val checkedstate = remember {
            mutableStateOf(false)
        }
        Checkbox(checked =checkedstate.value, onCheckedChange = {
            checkedstate.value = !checkedstate.value
            oncheckedchange.invoke(it)
        })
        clickabletext(value = value, onTextSelected)
    }
}

@Composable
fun clickabletext(value:String,onTextSelected : (String) -> Unit ) {
    val itext = "By creating an account, you agree to our "
    val tac = "Terms and Condition"
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)){
            pushStringAnnotation(tag = itext, annotation = itext)
            append(itext)
        }
        withStyle(style = SpanStyle(color = Color(0xff405DE6))){
            pushStringAnnotation(tag = tac, annotation = tac)
            append(tac)
        }
    }
    ClickableText(text = annotatedString, onClick = {offset ->
        annotatedString.getStringAnnotations(offset,offset)
            .firstOrNull()?.also { span ->
                Log.d("clickabletext","${span.item}")
                if(span.item == tac){
                    onTextSelected(span.item)
                }
            }
    })
}

@Composable
fun clickabletextr(value:String,onTextSelected : (String) -> Unit ) {
    val itext = "Already have an account? "
    val tac = "Login"
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)){
            pushStringAnnotation(tag = itext, annotation = itext)
            append(itext)
        }
        withStyle(style = SpanStyle(color = Color(0xff405DE6))){
            pushStringAnnotation(tag = tac, annotation = tac)
            append(tac)
        }
    }
    ClickableText(text = annotatedString, onClick = {offset ->
        annotatedString.getStringAnnotations(offset,offset)
            .firstOrNull()?.also { span ->
                Log.d("clickabletext","${span.item}")
                if(span.item == tac){
                    onTextSelected(span.item)
                }
            }
    })
}

@Composable
fun clickabletextl(value:String,onTextSelected : (String) -> Unit ) {
    val itext = "Don't have account? "
    val tac = "Create new"
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)){
            pushStringAnnotation(tag = itext, annotation = itext)
            append(itext)
        }
        withStyle(style = SpanStyle(color = Color(0xff405DE6))){
            pushStringAnnotation(tag = tac, annotation = tac)
            append(tac)
        }
    }
    ClickableText(text = annotatedString, onClick = {offset ->
        annotatedString.getStringAnnotations(offset,offset)
            .firstOrNull()?.also { span ->
                Log.d("clickabletext","${span.item}")
                if(span.item == tac){
                    onTextSelected(span.item)
                }
            }
    })
}

@Composable
fun clickabletextf(value:String,onTextSelected : (String) -> Unit ) {
    val tac = "Forget Password?"
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xff405DE6))){
            pushStringAnnotation(tag = tac, annotation = tac)
            append(tac)
        }
    }
    ClickableText(text = annotatedString, onClick = {offset ->
        annotatedString.getStringAnnotations(offset,offset)
            .firstOrNull()?.also { span ->
                Log.d("clickabletext","${span.item}")
                if(span.item == tac){
                    onTextSelected(span.item)
                }
            }
    })
}