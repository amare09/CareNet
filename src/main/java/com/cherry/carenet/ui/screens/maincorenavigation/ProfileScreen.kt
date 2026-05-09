package com.cherry.carenet.ui.screens.maincorenavigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cherry.carenet.navigation.ROUTE_BLOCKED_USERS
import com.cherry.carenet.ui.theme.SoftPowderBlue
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileScreen(
    navController: NavController
) {

    val user =
        FirebaseAuth.getInstance().currentUser

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        SoftPowderBlue.copy(alpha = 0.25f),
                        Color(0xFFF7F3ED)
                    )
                )
            )
            .padding(16.dp)
    ) {

        Text("Profile")

        Spacer(modifier = Modifier.height(20.dp))

        Text("Email: ${user?.email}")

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {

                navController.navigate(
                    ROUTE_BLOCKED_USERS
                )
            }
        ) {

            Text("Blocked Users")
        }
    }
}










@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){

    ProfileScreen(rememberNavController())

}