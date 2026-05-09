package com.cherry.carenet.ui.screens.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cherry.carenet.navigation.ROUTE_PERMISSIONS
import com.cherry.carenet.ui.theme.SoftPowderBlue

@Composable
fun RoleCard(
    title: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) SoftPowderBlue.copy(alpha = 0.15f)
            else Color.White
        )
    ) {

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clickable { onClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = SoftPowderBlue,
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {

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
fun RoleSelectionScreen(navController: NavController){


    var selectedRole by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F3ED))
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Choose Your Role",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = SoftPowderBlue
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Select how you want to use CareNet",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(25.dp))

        // 👤 USER ROLE
        RoleCard(
            title = "User",
            description = "Request help from your community",
            icon = Icons.Default.Person,
            selected = selectedRole == "user",
            onClick = { selectedRole = "user" }
        )

        // 🤝 HELPER ROLE
        RoleCard(
            title = "Helper",
            description = "Offer help to people nearby",
            icon = Icons.Default.Person,
            selected = selectedRole == "helper",
            onClick = { selectedRole = "helper" }
        )

        // 🏢 ORGANIZATION (OPTIONAL)
        RoleCard(
            title = "Organization/Admin",
            description = "Manage community support groups",
            icon = Icons.Default.Build,
            selected = selectedRole == "org",
            onClick = { selectedRole = "org" }
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                if (selectedRole != null) {
                    navController.navigate(ROUTE_PERMISSIONS)
                }
            },
            enabled = selectedRole != null,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = SoftPowderBlue,
                disabledContainerColor = Color.LightGray
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Continue", color = Color.White)
        }
    }


}
@Preview(showBackground = true)
@Composable
fun RoleSelectionScreenPreview(){

    RoleSelectionScreen(rememberNavController())

}