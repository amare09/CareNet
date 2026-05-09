package com.cherry.carenet.ui.screens.trustandidentity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cherry.carenet.ui.theme.SoftPowderBlue
import com.cherry.carenet.ui.theme.WarmCream
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import java.io.File
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cherry.carenet.viewmodel.ProfileViewModel

@Composable
fun EditProfileScreen(navController: NavController) {

    val viewModel: ProfileViewModel = viewModel()

    val user = viewModel.userState.value
    var name by remember(user.name) { mutableStateOf(user.name) }
    var email by remember (user.email){ mutableStateOf(user.email) }
    var address by remember(user.address) { mutableStateOf(user.address) }
    var bio by remember (user.bio){ mutableStateOf(user.bio) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        imageUri = uri
    }
    val photoFile = remember {
        File(context.cacheDir, "profile.jpg")
    }
    val photoUri = remember {
        FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            photoFile
        )

    }

    val cameraLauncher = rememberLauncherForActivityResult(

        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            imageUri = photoUri
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F3ED))
    ) {

        // 🌊 1. GRADIENT HEADER (TOP SECTION)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            SoftPowderBlue,
                            SoftPowderBlue.copy(alpha = 0.7f)
                        )
                    )
                )
        )

        // 👤 2. PROFILE HERO (OVERLAPPING HEADER)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .background(Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ) {

                if (imageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = "Profile Image",
                        modifier = Modifier.size(90.dp)
                    )
                } else {

                    Text(
                        text = name.firstOrNull()?.uppercase()?.toString() ?: "?",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = SoftPowderBlue
                    )


                }//else
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { galleryLauncher.launch("image/*") }) {
                Text("Gallery")
            }


            Button(onClick = { cameraLauncher.launch(photoUri) }) {
                Text("Camera")
            }


            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = "⭐ Trusted Member",
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.8f)
            )
        }

        // 🧾 3. FLOATING EDIT CARD
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .align(Alignment.BottomCenter)
                .offset(y = (-40).dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {

            Column(modifier = Modifier.padding(16.dp)) {

                Text(
                    text = "Edit Profile",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = SoftPowderBlue
                )

                Spacer(modifier = Modifier.height(15.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Full Name") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(Icons.Default.Person, null)
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = SoftPowderBlue,
                        unfocusedBorderColor = SoftPowderBlue
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(Icons.Default.Email, null)
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = SoftPowderBlue,
                        unfocusedBorderColor = SoftPowderBlue
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text("Address") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(Icons.Default.LocationOn, null)
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = SoftPowderBlue,
                        unfocusedBorderColor = SoftPowderBlue
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))


                OutlinedTextField(
                    value = bio,
                    onValueChange = { bio = it },
                    label = { Text("Your Bio") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(Icons.Default.List, null)
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = SoftPowderBlue,
                        unfocusedBorderColor = SoftPowderBlue
                    )
                )

                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    onClick = {
                        viewModel.saveProfile(
                            name=name,
                            bio=bio,
                            //address=address
                        )

                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SoftPowderBlue
                    )
                ) {
                    Text("Save Changes", color = Color.White)
                }
            }
        }
    }
}
    @Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview(){

    EditProfileScreen(rememberNavController())

}
