package com.cherry.carenet.ui.screens.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cherry.carenet.navigation.ROUTE_APPCHECK
import com.cherry.carenet.navigation.ROUTE_PERMISSIONS
import com.cherry.carenet.ui.theme.SoftPowderBlue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import com.cherry.carenet.navigation.ROUTE_LOGIN

@Composable

fun PermissionItem(title: String, description: String, icon:androidx.compose.ui.graphics.vector.ImageVector) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {

            // ✅ ICON GOES HERE
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = SoftPowderBlue,
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {

                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Text(
                    text = description,
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
@Composable
fun PermissionsScreen(navController: NavController){

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF7F3ED))
                .padding(16.dp)
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Permissions",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = SoftPowderBlue
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "CareNet needs a few permissions to help you connect and stay safe in your community.",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(25.dp))

            // 📍 LOCATION
            PermissionItem(
                title = "Location",
                description = "Find nearby help and community services",
                icon = Icons.Default.LocationOn
            )

            // 📷 CAMERA
            PermissionItem(
                title = "Camera",
                description = "Upload profile photos and verify identity",
                icon = Icons.Filled.Person
            )

            // 🖼️ STORAGE
            PermissionItem(
                title = "Storage",
                description = "Save and share images",
                icon = Icons.Filled.List
            )

            // 🔔 NOTIFICATIONS
            PermissionItem(
                title = "Notifications",
                description = "Get alerts for messages and help requests",
                icon = Icons.Default.Notifications
                            )

            Spacer(modifier = Modifier.weight(1f))

            // 🔵 BUTTONS
            Button(
                onClick = {
                    navController.navigate(ROUTE_LOGIN) {
                        popUpTo(ROUTE_PERMISSIONS) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SoftPowderBlue
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Allow & Continue", color = Color.White)
            }

            Spacer(modifier = Modifier.height(10.dp))

            TextButton(
                onClick = {
                    navController.navigate(ROUTE_APPCHECK)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Not now", color = Color.Gray)
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun PermissionsScreenPreview(){

    PermissionsScreen(rememberNavController())

}