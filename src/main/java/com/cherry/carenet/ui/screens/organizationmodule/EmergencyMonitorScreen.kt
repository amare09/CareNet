package com.cherry.carenet.ui.screens.organizationmodule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.cherry.carenet.ui.theme.SoftPowderBlue
import com.cherry.carenet.utilis.AdminUtils
import com.cherry.carenet.viewmodel.OrganizationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmergencyMonitorScreen(
    navController: NavController,
    viewModel: OrganizationViewModel = viewModel()
) {

    val emergencies =
        viewModel.requests.filter { it.isEmergency }
    if (!AdminUtils.isAdmin()) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "Only admins can access this screen",
                color = Color.Red,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        return
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F8FA)) // ✅ NORMAL BACKGROUND
    ) {

        // 🔵 TOP APP BAR
        TopAppBar(
            title = {
                Text(
                    text = "Emergency Monitor",
                    fontWeight = FontWeight.Bold
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = SoftPowderBlue
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            // 🔵 LIVE CARD
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {

                Column(modifier = Modifier.padding(14.dp)) {

                    Text(
                        text = "LIVE UPDATES",
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Live emergency alerts from users",
                        color = Color.DarkGray,
                        fontSize = 13.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 🔵 LAST MESSAGE (PLAIN ON SCREEN)
            Text(
                text = "Last update: Monitoring system active",
                color = Color.Black,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 🔵 CONTENT
            if (emergencies.isEmpty()) {

                Text(
                    text = "No active emergencies 🎉",
                    color = Color.Gray,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(20.dp)
                )

            } else {

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(emergencies.toList()) { request ->
                   // items(emergencies) { request ->

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            elevation = CardDefaults.cardElevation(6.dp)
                        ) {

                            Column(modifier = Modifier.padding(14.dp)) {

                                Text(
                                    text = "🚨 ${request.title}",
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFB00020),
                                    fontSize = 16.sp
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                Text(
                                    text = request.description,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}