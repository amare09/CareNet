


package com.cherry.carenet.ui.screens.authentication

//import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
import com.cherry.carenet.navigation.*
import com.cherry.carenet.ui.theme.SoftPowderBlue
import com.google.accompanist.permissions.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cherry.carenet.navigation.ROUTE_APPCHECK
import com.cherry.carenet.navigation.ROUTE_LOGIN
import com.cherry.carenet.navigation.ROUTE_PERMISSIONS
import com.cherry.carenet.ui.theme.SoftPowderBlue
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
//import java.util.jar.Manifest
import android.Manifest
import androidx.compose.runtime.*
import com.google.accompanist.permissions.*

@Composable
fun PermissionItem(
    title: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {

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
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionsScreen(navController: NavController) {

    val permissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.POST_NOTIFICATIONS
        )
    )

    // Auto request once
    LaunchedEffect(Unit) {
        permissionState.launchMultiplePermissionRequest()
    }

    // Navigate when ALL permissions are granted
    LaunchedEffect(permissionState.allPermissionsGranted) {
        if (permissionState.allPermissionsGranted) {
            navController.navigate(ROUTE_LOGIN) {
                popUpTo(ROUTE_PERMISSIONS) { inclusive = true }
                launchSingleTop = true
            }
        }
    }

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
            text = "CareNet needs permissions to work properly.",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(25.dp))

        PermissionItem(
            title = "Location",
            description = "Find nearby help and services",
            icon = Icons.Default.LocationOn
        )

        PermissionItem(
            title = "Camera",
            description = "Upload profile photos",
            icon = Icons.Default.Person
        )

        PermissionItem(
            title = "Notifications",
            description = "Get alerts and messages",
            icon = Icons.Default.Notifications
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                permissionState.launchMultiplePermissionRequest()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = SoftPowderBlue
            )
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