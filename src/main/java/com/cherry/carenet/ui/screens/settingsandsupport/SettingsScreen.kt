package com.cherry.carenet.ui.screens.settingsandsupport

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cherry.carenet.navigation.ROUTE_BLOCKED_USERS
import com.cherry.carenet.navigation.ROUTE_LOGIN
import com.cherry.carenet.navigation.ROUTE_PROFILE
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SettingsScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Settings",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Profile",
            modifier = Modifier.clickable {
                navController.navigate(ROUTE_PROFILE)
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Blocked Users",
            modifier = Modifier.clickable {
                navController.navigate(ROUTE_BLOCKED_USERS)
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Logout",
            color = Color.Red,
            modifier = Modifier.clickable {
                FirebaseAuth.getInstance().signOut()
                navController.navigate(ROUTE_LOGIN) {
                    popUpTo(0)
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview(){

    SettingsScreen(rememberNavController())



}