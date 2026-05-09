package com.cherry.carenet.ui.screens.authentication
//is a routing controller,and not a real screen users interact with
//it just checks if user is logged in then redirects,if user is logged in,path is:Splash to Home Screen if not,it isiSplash - Log in- Register-Home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@Composable
fun AppCheckScreen(navController: NavController) {

    var hasNavigated by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {

        if (!hasNavigated) {

            val isLoggedIn = false // later: Firebase/Auth/DataStore

            hasNavigated = true

            if (isLoggedIn) {
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
    }

    // 🟦 SIMPLE LOADING UI (NOT A REAL SCREEN)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        SoftPowderBlue,
                        SoftPowderBlue.copy(alpha = 0.7f)


                    )

                )
            ),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Loading CareNet...",
            fontSize = 18.sp,
color= Color.White
        )
        Spacer(modifier = Modifier.height(10.dp))
        CircularProgressIndicator(color=Color.White)
    }
}
@Preview(showBackground = true)
@Composable
fun AppCheckScreenPreview(){

    AppCheckScreen(rememberNavController())

}