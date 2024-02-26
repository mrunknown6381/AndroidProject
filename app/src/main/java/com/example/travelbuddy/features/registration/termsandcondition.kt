package com.example.travelbuddy.features.registration

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.travelbuddy.data.dimens
import com.example.travelbuddy.ui.theme.text


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun termsandcondition(navController: NavController) {

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(title = {
            Text(text = "Terms And Condition")
        },colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ), navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = null)
            }
        })
    }) {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ){
        Spacer(modifier = Modifier.height(125.dp))
        Text(text = "Introduction", textAlign = TextAlign.Left, modifier = Modifier.padding(horizontal = dimens.MP1), style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(7.dp))
        Text(text = "Welcome to Travel Buddy! These terms and conditions govern your use of our platform and services.", textAlign = TextAlign.Left, modifier = Modifier.padding(horizontal = dimens.MP1), style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.text)
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "Acceptance of Terms", textAlign = TextAlign.Left, modifier = Modifier.padding(horizontal = dimens.MP1), style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(7.dp))
        Text(text = "By using Travel Buddy, you agree to comply with and be bound by these terms and conditions. If you do not agree, please refrain from using our services.", textAlign = TextAlign.Left, modifier = Modifier.padding(horizontal = dimens.MP1), style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.text)
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "User Eligibility", textAlign = TextAlign.Left, modifier = Modifier.padding(horizontal = dimens.MP1), style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(7.dp))
        Text(text = "Users must be at least 18 years old to use Travel Buddy. Users are responsible for providing accurate and current information during registration.", textAlign = TextAlign.Left, modifier = Modifier.padding(horizontal = dimens.MP1), style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.text)
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "Account Security", textAlign = TextAlign.Left, modifier = Modifier.padding(horizontal = dimens.MP1), style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(7.dp))
        Text(text = "Users are responsible for maintaining the confidentiality of their account credentials. Notify Travel Buddy immediately if you suspect unauthorized access.", textAlign = TextAlign.Left, modifier = Modifier.padding(horizontal = dimens.MP1), style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.text)
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "Termination", textAlign = TextAlign.Left, modifier = Modifier.padding(horizontal = dimens.MP1), style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(7.dp))
        Text(text = "Travel Buddy reserves the right to terminate accounts for violation of terms. Users can terminate their account at any time.", textAlign = TextAlign.Left, modifier = Modifier.padding(horizontal = dimens.MP1), style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.text)
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "Updates to Terms", textAlign = TextAlign.Left, modifier = Modifier.padding(horizontal = dimens.MP1), style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(7.dp))
        Text(text = "Travel Buddy may update these terms periodically. Continued use of the platform implies acceptance of the updated terms.", textAlign = TextAlign.Left, modifier = Modifier.padding(horizontal = dimens.MP1), style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.text)
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "Contact Information", textAlign = TextAlign.Left, modifier = Modifier.padding(horizontal = dimens.MP1), style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(7.dp))
        Text(text = " For questions or concerns, contact Travel Buddy at support@travelbuddy.com.", textAlign = TextAlign.Left, modifier = Modifier.padding(horizontal = dimens.MP1), style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.text)
}
}}