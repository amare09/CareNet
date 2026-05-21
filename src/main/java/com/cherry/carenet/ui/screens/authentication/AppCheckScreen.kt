package com.cherry.carenet.ui.screens.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cherry.carenet.navigation.ROUTE_APPCHECK
import com.cherry.carenet.navigation.ROUTE_HOME
import com.cherry.carenet.navigation.ROUTE_LOGIN
import com.cherry.carenet.ui.theme.SoftPowderBlue
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppCheckScreen(navController: NavController) {

    val auth = FirebaseAuth.getInstance()

    LaunchedEffect(auth.currentUser) {

        if (auth.currentUser != null) {

            navController.navigate(ROUTE_HOME) {
                popUpTo(ROUTE_APPCHECK) { inclusive = true }
                launchSingleTop = true
            }

        } else {

            navController.navigate(ROUTE_LOGIN) {
                popUpTo(ROUTE_APPCHECK) { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    // LOADING UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        SoftPowderBlue,
                        SoftPowderBlue.copy(alpha = 0.7f)
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Loading CareNet...",
            fontSize = 18.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(10.dp))

        CircularProgressIndicator(color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun AppCheckScreenPreview() {
    AppCheckScreen(rememberNavController())
}