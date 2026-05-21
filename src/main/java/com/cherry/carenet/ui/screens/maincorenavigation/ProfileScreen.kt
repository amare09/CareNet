package com.cherry.carenet.ui.screens.maincorenavigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.cherry.carenet.navigation.ROUTE_ADMIN_DASHBOARD
import com.cherry.carenet.navigation.ROUTE_BLOCKED_USERS
import com.cherry.carenet.navigation.ROUTE_EDIT_PROFILE
import com.cherry.carenet.navigation.ROUTE_LOGIN
import com.cherry.carenet.ui.theme.SoftPowderBlue
import com.cherry.carenet.viewmodel.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth


@Composable
fun ProfileScreen(navController: NavController) {

    val viewModel: ProfileViewModel = viewModel()
    val user = viewModel.userState.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        SoftPowderBlue,
                        Color.White
                    )
                )
            )


    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            // ================= HEADER =================
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Black, SoftPowderBlue)
                        )
                    )
                    .padding(30.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Box(
                        modifier = Modifier
                            .size(110.dp)
                            .background(Color.White.copy(alpha = 0.2f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {

                        if (user.profileImage.isNotEmpty()) {
                            Image(
                                painter = rememberAsyncImagePainter(user.profileImage),
                                contentDescription = null,

                                contentScale = ContentScale.Crop,

                                modifier = Modifier
                                    .size(110.dp)
                                    .clip(CircleShape)
                            )      } else {
                            Icon(
                                Icons.Default.Person,
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier.size(60.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(user.name.ifEmpty { "No Name Set" }, color = Color.White)
                    Text(user.email, color = Color.White.copy(alpha = 0.8f))
                    Text(
                        "Trust Score: ${user.trustScore} ⭐",
                        color = Color.White.copy(alpha = 0.7f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // PROFILE DETAILS (your cards stay here)
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                Text("Profile Details", fontWeight = FontWeight.Bold)

                // Address card
                Card(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text("📍 Address", color = SoftPowderBlue)
                        Text(user.address.ifEmpty { "No address added" })
                    }
                }

                // Bio card
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text("📝 Bio", color = SoftPowderBlue)
                        Text(user.bio.ifEmpty { "No bio added yet" })
                    }
                }


                if (user.role == "admin") {

                    Spacer(modifier = Modifier.height(14.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),

                        onClick = {

                            navController.navigate(
                                ROUTE_ADMIN_DASHBOARD
                            )
                        },

                        colors = CardDefaults.cardColors(
                            containerColor = SoftPowderBlue.copy(alpha = 0.15f)
                        ),

                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {

                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                imageVector = Icons.Default.Warning,
                                contentDescription = null,
                                tint = SoftPowderBlue
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Column {

                                Text(
                                    text = "Admin Dashboard",
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    text = "Manage requests and emergencies",
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            }
                        }
                    }


                    Spacer(modifier = Modifier.height(14.dp))
                }
                Spacer(modifier = Modifier.height(20.dp))

                //logout btn
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        FirebaseAuth.getInstance().signOut()
                        navController.navigate(ROUTE_LOGIN ){
                            popUpTo (0)
                        }


                    },
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFEBEE)
                    ),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically


                    ) {
                        Icon(
                            imageVector =
                                Icons.Default.MailOutline,contentDescription=null,
                                tint= Color.Red





                        )
                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text="Log Out",
                            color=Color.Black,
                            fontWeight = FontWeight.Bold


                        )



                    }








                }


            }
        }

        // ================= FLOATING BUTTON (FIXED) =================
        FloatingActionButton(
            onClick = {
               navController.navigate(ROUTE_EDIT_PROFILE)
            },
            containerColor = SoftPowderBlue,
            modifier = Modifier
                .align(Alignment.BottomEnd)
              .padding(20.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Edit",
                tint = Color.White
            )
        }
    }

}

