package com.cherry.carenet.ui.screens.maincorenavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.cherry.carenet.viewmodel.RequestViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateHelpRequestScreen(navController: NavController) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val viewModel: RequestViewModel = viewModel()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF6F2FF),
                        Color.White
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),

            verticalArrangement = Arrangement.Center
        ) {

            // 🔥 HEADER
            Text(
                text = "Request Help",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,

            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Tell the community what support you need.",
                fontSize = 15.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(30.dp))

            // 🔥 FORM CARD
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),

                    verticalArrangement = Arrangement.spacedBy(18.dp)
                ) {

                    // TITLE FIELD
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },

                        modifier = Modifier.fillMaxWidth(),

                        label = {
                            Text("Request Title")
                        },

                        placeholder = {
                            Text("Food, Medical Help...")
                        },

                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Help,
                                contentDescription = null,
                                tint = SoftPowderBlue
                            )
                        },

                        shape = RoundedCornerShape(16.dp),

                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = SoftPowderBlue,
                            unfocusedBorderColor = Color.LightGray
                        )
                    )

                    // DESCRIPTION FIELD
                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp),

                        label = {
                            Text("Description")
                        },

                        placeholder = {
                            Text("Describe your situation...")
                        },

                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Description,
                                contentDescription = null,
                                tint = SoftPowderBlue
                            )
                        },

                        shape = RoundedCornerShape(16.dp),

                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = SoftPowderBlue,
                            unfocusedBorderColor = Color.LightGray
                        )
                    )

                    // SUBMIT BUTTON
                    Button(
                        onClick = {

                            viewModel.createRequest(
                                title,
                                description
                            )

                            navController.popBackStack()
                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),

                        shape = RoundedCornerShape(18.dp),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = SoftPowderBlue
                        )
                    ) {

                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = null,
                            tint = Color.White
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Submit Request",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateHelpRequestScreenPreview() {
    CreateHelpRequestScreen(rememberNavController())
}