package com.cherry.carenet.ui.screens.requestmanagement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.cherry.carenet.navigation.ROUTE_CHAT
import com.cherry.carenet.viewmodel.RequestViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HelpDetailsScreen(
    navController: NavController,
    requestId: String,
    viewModel: RequestViewModel = viewModel()
) {

    val currentUserId =
        FirebaseAuth.getInstance().currentUser?.uid ?: ""

    val request =
        viewModel.requests.find { it.requestId == requestId }

    if (request == null) {
        Text("Loading...")
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // 🔥 TITLE
        Text(
            request.title,
            fontSize = 20.sp,
            fontWeight =
                FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        // 🔥 DESCRIPTION
        Text(request.description)

        Spacer(modifier = Modifier.height(20.dp))

        // 🔥 STATUS
        Text("Status: ${request.status}")

        Spacer(modifier = Modifier.height(20.dp))

        // 🔥 ACTION AREA (FIXED LOGIC)
        when {

            // 🟢 PENDING → SHOW ACCEPT
            request.status == "pending" -> {

                Button(onClick = {
                    viewModel.acceptRequest(
                        request.requestId,
                        request.senderId,
                        request.senderName
                    )


                    { roomId, userName->
                         navController.navigate("$ROUTE_CHAT/$roomId/$userName")
                    }            }) {
                    Text("Accept Request")
                }
            }

            // 🔵 ACCEPTED BY ME → SHOW COMPLETE
            request.receiverId == currentUserId -> {

                Button(onClick = {
                    viewModel.completeRequest(request.requestId)
                    navController.popBackStack()
                }) {
                    Text("Complete Task")
                }
            }

            // 🔴 ACCEPTED BY SOMEONE ELSE
            else -> {

                Text("Already taken by another user")
            }
        }
    }
}