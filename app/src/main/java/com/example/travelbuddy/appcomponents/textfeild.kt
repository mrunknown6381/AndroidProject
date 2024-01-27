package com.example.travelbuddy.appcomponents

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.travelbuddy.ui.theme.text
import com.example.travelbuddy.ui.theme.textfieldcontainer
import com.example.travelbuddy.ui.theme.unfocusedText

@Composable
fun textfeild(modifier: Modifier,label:String,trailing:String,painterResource: Painter,onTextSelected: (String) -> Unit,errorStatus:Boolean = false) {
    val textval = remember {
        mutableStateOf("")
    }
    TextField(modifier = Modifier.width(315.dp),value = textval.value, onValueChange = {
        textval.value = it
        onTextSelected(it)
                                                                                       },label = {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
        )
    }, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.unfocusedText,
            focusedPlaceholderColor = MaterialTheme.colorScheme.text,
            unfocusedContainerColor = MaterialTheme.colorScheme.textfieldcontainer,
            focusedContainerColor = MaterialTheme.colorScheme.textfieldcontainer,
        ),
        trailingIcon = {
            TextButton(onClick = {}) {
                Text(
                    text = trailing,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight =  FontWeight.Medium),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        isError = !errorStatus
        )
}


@Composable
fun passtextfeild(modifier: Modifier,label:String,trailing:String,painterResource: Painter,onTextSelected: (String) -> Unit,errorStatus:Boolean = false) {
    val localFocusManager = LocalFocusManager.current
    val password = remember {
        mutableStateOf("")
    }
    val passwordVisible = remember {
        mutableStateOf(false)
    }
    TextField(modifier = Modifier.width(315.dp),value = password.value, onValueChange = {
        password.value = it
        onTextSelected(it)
                                                                                        },label = {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
        )
    }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
        singleLine = true,
        maxLines = 1,
        keyboardActions = KeyboardActions{
            localFocusManager.clearFocus()
        },
        colors = TextFieldDefaults.colors(
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.unfocusedText,
            focusedPlaceholderColor = MaterialTheme.colorScheme.text,
            unfocusedContainerColor = MaterialTheme.colorScheme.textfieldcontainer,
            focusedContainerColor = MaterialTheme.colorScheme.textfieldcontainer,
        ),
        trailingIcon = {
            val iconImage = if(passwordVisible.value){
                Icons.Filled.Visibility
            }else{
                Icons.Filled.VisibilityOff
            }
            var description = if(passwordVisible.value){
                "Hide password"
        }else{
            "Show password"
        }
            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        visualTransformation = if(passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        isError = !errorStatus
    )
}


@Composable
fun basetextfeild(modifier: Modifier,label:String,trailing:String,painterResource: Painter,onTextSelected: (String) -> Unit) {
    val textval = remember {
        mutableStateOf("")
    }
    TextField(modifier = Modifier.width(315.dp),value = textval.value, onValueChange = {
        textval.value = it
        onTextSelected(it)
    },label = {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
        )
    }, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.unfocusedText,
            focusedPlaceholderColor = MaterialTheme.colorScheme.text,
            unfocusedContainerColor = MaterialTheme.colorScheme.textfieldcontainer,
            focusedContainerColor = MaterialTheme.colorScheme.textfieldcontainer,
        ),
        trailingIcon = {
            TextButton(onClick = {}) {
                Text(
                    text = trailing,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight =  FontWeight.Medium),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        }

    )
}