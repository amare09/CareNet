package com.cherry.carenet.ui.screens.requestmanagement

//import android.view.PixelCopy.request
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.cherry.carenet.navigation.ROUTE_CHAT
import com.cherry.carenet.ui.theme.SoftPowderBlue
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
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Loading...")
        }
        return
    }
    val isOwner = request.senderId == currentUserId

    Column(
        modifier = Modifier
            .fillMaxSize()

            // 🌈 OPTION A GRADIENT BACKGROUND
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

        // 🟦 MAIN CARD
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {

            Column(modifier = Modifier.padding(16.dp)) {

                // 🔥 TITLE
                Text(
                    text = request.title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(10.dp))

                // 🔥 DESCRIPTION
                Text(
                    text = request.description,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 🔥 STATUS BADGE
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = when (request.status) {
                            "pending" -> Color(0xFFFFF3CD)
                            "accepted" -> Color(0xFFD1ECF1)
                            else -> Color(0xFFD4EDDA)
                        }
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {

                    Text(
                        text = "Status: ${request.status}",
                        modifier = Modifier.padding(10.dp),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // 🔥 ACTIONS
                when {

                    // 🟢 PENDING + NOT MY REQUEST


                        request.status == "pending" && !isOwner -> {

                            Button(
                                onClick = {
                                    viewModel.acceptRequest(
                                        request.requestId,
                                        request.senderId,
                                        request.senderName
                                    ) { roomId, userName ->
                                        navController.navigate("$ROUTE_CHAT/$roomId/$userName")
                                    }
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Accept Request")
                            }
                        }

                    // 🟡 MY OWN REQUEST
                    request.senderId == currentUserId -> {

                        Text(
                            text = "This is your own request",
                            color = Color.Gray,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    // 🔵 ACCEPTED BY ME
                    request.receiverId == currentUserId -> {

                        Button(
                            onClick = {

                                viewModel.completeRequest(
                                    request.requestId
                                )

                                navController.popBackStack()
                            },

                            modifier = Modifier.fillMaxWidth(),

                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF4CAF50)
                            )

                        ) {

                            Text(
                                "Complete Task",
                                color = Color.White
                            )
                        }
                    }

                    // 🔴 TAKEN
                    else -> {

                        Text(
                            text = "Already taken by another user",
                            color = Color.Red,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}