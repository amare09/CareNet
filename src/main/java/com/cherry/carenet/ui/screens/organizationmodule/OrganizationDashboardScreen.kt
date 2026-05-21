package com.cherry.carenet.ui.screens.organization

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cherry.carenet.navigation.ROUTE_ORGANIZATION_REQUEST_DETAILS
import com.cherry.carenet.ui.theme.SoftPowderBlue
import com.cherry.carenet.utilis.AdminUtils
import com.cherry.carenet.viewmodel.OrganizationViewModel

@Composable
fun StatCard(
    title: String,
    value: String



) {



    Card(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = title,
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = value,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = SoftPowderBlue
            )
        }
    }
}

@Composable
fun RequestCard(
    title: String,
    status: String,
    sender: String,
    isEmergency: Boolean,
    onClick:()-> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(3.dp),
        onClick = onClick
    ) {

        Column(
            modifier = Modifier.padding(14.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                if (isEmergency) {

                    Spacer(modifier = Modifier.weight(1f))

                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Sender: $sender",
                fontSize = 13.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Status: $status",
                fontSize = 13.sp,
                color = when (status) {
                    "completed" -> Color(0xFF2E7D32)
                    "accepted" -> Color(0xFF1565C0)
                    else -> Color(0xFFE65100)
                }
            )
        }
    }
}

@Composable
fun OrganizationDashboardScreen(
    navController: NavController,
    viewModel: OrganizationViewModel = viewModel()
) {

    val stats = viewModel.stats.value
    if (!AdminUtils.isAdmin()) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "Admins Only",
                color = Color.Red,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

        return
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        SoftPowderBlue.copy(alpha = 0.25f),
                        Color(0xFFF7F3ED)
                    )
                )
            )
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            item {

                Text(
                    text = "Organization Dashboard",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = SoftPowderBlue
                )

                Spacer(modifier = Modifier.height(20.dp))
            }

            // ✅ ANALYTICS SECTION

            item {

                Text(
                    text = "Analytics",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))
            }

            item {

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        StatCard(
                            title = "Total Requests",
                            value = stats.totalRequests.toString()
                        )

                        StatCard(
                            title = "Pending",
                            value = stats.pendingRequests.toString()
                        )

                        StatCard(
                            title = "Completed",
                            value = stats.completedRequests.toString()
                        )
                    }

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        StatCard(
                            title = "Emergencies",
                            value = stats.emergencyRequests.toString()
                        )

                        StatCard(
                            title = "Users Helped",
                            value = stats.usersHelped.toString()
                        )
                    }
                }
            }

            item {

                Spacer(modifier = Modifier.height(20.dp))

                HorizontalDivider()

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Live Requests",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))
            }

            // ✅ LIVE REQUESTS

            items(viewModel.requests) { request ->

                RequestCard(
                    title = request.title,
                    status = request.status,
                    sender = request.senderName,
                    isEmergency = request.isEmergency,
                    onClick={
                        navController.navigate(
                            "$ROUTE_ORGANIZATION_REQUEST_DETAILS/${request.requestId}"
                        )



                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrganizationDashboardScreenPreview() {

    OrganizationDashboardScreen(
        rememberNavController()
    )
}