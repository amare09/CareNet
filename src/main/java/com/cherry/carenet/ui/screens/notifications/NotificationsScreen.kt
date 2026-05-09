package com.cherry.carenet.ui.screens.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cherry.carenet.ui.theme.SoftPowderBlue
import com.cherry.carenet.viewmodel.NotificationViewModel
import com.google.firebase.auth.FirebaseAuth


@Composable
fun NotificationsScreen(
    navController: NavController,
    viewModel: NotificationViewModel=viewModel()
    ){

    val userId=
        FirebaseAuth.getInstance().currentUser?.uid ?: ""



    LaunchedEffect(Unit) {
        viewModel.loadNotifications(userId)

    }

Column(
    modifier = Modifier
        .fillMaxSize()
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    SoftPowderBlue,
                    Color.White
                )
            )
        )
        .padding(16.dp)

) {
    Text(
        text="Your Notifications",
         fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Default


    )

    Spacer(modifier = Modifier .height(12.dp))

    viewModel.notifications.forEach { notif->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            onClick = {
                viewModel.markRead(userId, notif.id)
            }
        ) {

                    Column(modifier = Modifier.padding(12.dp)) { }
Text(
    text = notif.type.uppercase(),
    fontWeight = FontWeight.Bold
)
            Text(notif.message)

            Text(
                if (notif.read)"Read" else "New",
                fontSize = 12.sp,
                color = if (notif.read) Color.Gray else Color.Red

            )

        }
    }

  }

}
@Preview(showBackground = true)
@Composable
fun NotificationsScreenPreview(){

    NotificationsScreen(rememberNavController())

}