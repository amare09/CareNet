package com.cherry.carenet.ui.screens.requestmanagement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.cherry.carenet.ui.theme.SoftPowderBlue
import com.cherry.carenet.viewmodel.RequestViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ReceiveRequestScreen(
    navController: NavController,
    viewModel: RequestViewModel = viewModel()
) {

    val currentUserId =
        FirebaseAuth.getInstance()
            .currentUser?.uid ?: ""

    val myRequests =
        viewModel.requests.filter {

            it.receiverId == currentUserId
        }

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

        Text("Received Requests")

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {

            items(myRequests) { request ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(14.dp)
                    ) {

                        Text(request.title)

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(request.description)
                    }
                }
            }
        }
    }
}