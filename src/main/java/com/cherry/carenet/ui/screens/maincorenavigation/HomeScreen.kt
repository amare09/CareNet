package com.cherry.carenet.ui.screens.maincorenavigation

import android.window.SplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cherry.carenet.data.NotificationRepository
import com.cherry.carenet.models.AppNotification
import com.cherry.carenet.navigation.ROUTE_CHAT_LIST
import com.cherry.carenet.navigation.ROUTE_EMERGENCY_MONITOR
import com.cherry.carenet.navigation.ROUTE_EMERGENCY_REQUESTS
import com.cherry.carenet.navigation.ROUTE_HELP_DETAILS
import com.cherry.carenet.navigation.ROUTE_PROFILE
import com.cherry.carenet.navigation.ROUTE_REQUEST_HELP
//import com.cherry.carenet.ui.screens.maincorenavigation.viewModel
import com.cherry.carenet.ui.theme.SoftPowderBlue
import com.cherry.carenet.viewmodel.HomeViewModel
import com.cherry.carenet.viewmodel.RequestViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable

fun HomeFeedItem(title: String, distance: String) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {

        Column(modifier = Modifier.padding(12.dp)) {

            Text(title, fontWeight = FontWeight.Bold)

            Text(
                distance,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}
@Composable
fun HomeActionCard(title: String,
                   icon: androidx.compose.ui.graphics.vector.ImageVector,
                   onClick:()-> Unit

) {

    Card(
        modifier = Modifier.size(100.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = onClick

    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Icon(icon, contentDescription = null, tint = SoftPowderBlue)

            Spacer(modifier = Modifier.height(6.dp))

            Text(title, fontSize = 12.sp)
        }
    }
}
@Composable
fun HomeScreen(
    navController: NavController
 )
 {

    val  requestViewModel: RequestViewModel= viewModel()

     val homeViewModel: HomeViewModel = viewModel()


     val user = homeViewModel.user
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F3ED))
    ) {

        // 🔵 HEADER
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(SoftPowderBlue)
                .padding(16.dp)
        ) {

            Column {

                Text(
                    text = "Hello, ${user.name} 👋",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = "Trust Score: ${ user.trustScore}% ⭐",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // ⚡ QUICK ACTIONS
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            HomeActionCard(
                "Request Help",
                Icons.Default.AddCircle,
                onClick = {navController.navigate(ROUTE_REQUEST_HELP)}
            )


            HomeActionCard(
                "Chat",
                Icons.Default.MailOutline,
                onClick = {navController.navigate(ROUTE_CHAT_LIST)}
            )


            HomeActionCard(
                "Alerts",
                Icons.Default.Notifications,
                onClick = {navController.navigate(ROUTE_EMERGENCY_MONITOR)}

            )

        }

        Spacer(modifier = Modifier.height(10.dp))

        // 📍 FEED TITLE
        Text(
            text = "Nearby Requests",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(12.dp),
            color = SoftPowderBlue
        )

        // 📍 FEED LIST
        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
    requestViewModel.requests.forEach { request ->
        Card(
            onClick = {
                navController.navigate("$ROUTE_HELP_DETAILS/${request.requestId}")
            }
        ) {
            HomeFeedItem(
                title = request.title,
                distance = request.status
            )
        }



    }




        }//column for requests
    }

    // 🚨 FLOATING EMERGENCY BUTTON
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingActionButton(
            onClick = {

                val uid = FirebaseAuth.getInstance().currentUser?.uid

                if (uid.isNullOrEmpty()) {
                    android.util.Log.e("EMERGENCY", "User not logged in")
                    return@FloatingActionButton
                }

                val notificationRepo = NotificationRepository()

                val notification = AppNotification(
                    userId = uid,
                    type = "emergency",
                    message = "🚨 Emergency alert triggered!!"
                )

                android.util.Log.d("EMERGENCY", "Sending: $notification")

                notificationRepo.sendNotification(notification)

                android.util.Log.d("EMERGENCY", "Send function called")
            },
            containerColor = Color.Red,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Warning, contentDescription = null)
        }
    }
}





@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){

    HomeScreen(rememberNavController())

}