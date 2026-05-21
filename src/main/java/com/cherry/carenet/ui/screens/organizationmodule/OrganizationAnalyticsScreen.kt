
package com.cherry.carenet.ui.screens.organization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.cherry.carenet.ui.theme.SoftPowderBlue
import com.cherry.carenet.utilis.AdminUtils
import com.cherry.carenet.viewmodel.OrganizationViewModel

@Composable
fun OrganizationAnalyticsScreen(
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
                text = "Access Denied",
                color = Color.Red,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

        return
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

        Text(
            "Analytics",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        AnalyticsCard(
            "Total Requests",
            stats.totalRequests.toString()
        )

        AnalyticsCard(
            "Pending Requests",
            stats.pendingRequests.toString()
        )

        AnalyticsCard(
            "Completed Requests",
            stats.completedRequests.toString()
        )

        AnalyticsCard(
            "Emergencies",
            stats.emergencyRequests.toString()
        )
    }
}

@Composable
fun AnalyticsCard(
    title: String,
    value: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(title)

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                value,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = SoftPowderBlue
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun OrganizationAnalyticsScreenPreview(){

    OrganizationAnalyticsScreen(rememberNavController())

}