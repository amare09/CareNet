package com.cherry.carenet.ui.screens.organizationmodule

import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.cherry.carenet.ui.theme.SoftPowderBlue
import com.cherry.carenet.viewmodel.RequestViewModel

@Composable
fun OrganizationRequestDetailsScreen(

    navController: NavController,

    requestId: String,

    viewModel: RequestViewModel = viewModel()

) {

    val request =
        viewModel.requests.find {

            it.requestId == requestId
        }



    if (request == null) {

        Text("Loading...")
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
            .verticalScroll(rememberScrollState())
            .padding(16.dp)

    ) {

        Text(

            text = "Request Details",

            fontSize = 26.sp,

            fontWeight = FontWeight.Bold,

            color = SoftPowderBlue
        )



        Spacer(modifier = Modifier.height(20.dp))



        Card(

            modifier = Modifier.fillMaxWidth(),

            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )

        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = request.title,

                    fontSize = 22.sp,

                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                HorizontalDivider()

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Description:",
                    fontWeight = FontWeight.Bold
                )

                Text(request.description)

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Requester:",
                    fontWeight = FontWeight.Bold
                )

                Text(request.senderName)

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Request Status:",
                    fontWeight = FontWeight.Bold
                )

                Text(request.status)

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Emergency:",
                    fontWeight = FontWeight.Bold
                )

                Text(
                    if (request.isEmergency)
                        "YES 🚨"
                    else
                        "No"
                )
            }
        }



        Spacer(modifier = Modifier.height(24.dp))



        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // ✅ APPROVE REQUEST
            Button(

                onClick = {

                    viewModel.acceptRequest(

                        requestId = request.requestId,

                        senderId = request.senderId,

                        senderName = request.senderName,

                        onRoomReady = { _, _ -> }
                    )
                },

                modifier = Modifier.fillMaxWidth()

            ) {

                Text("Approve Request")
            }



            // ✅ COMPLETE REQUEST
            Button(

                onClick = {

                    viewModel.completeRequest(
                        request.requestId
                    )

                    navController.popBackStack()
                },

                modifier = Modifier.fillMaxWidth()

            ) {

                Text("Mark As Completed")
            }



            // ✅ DELETE REQUEST
            Button(

                onClick = {

                    viewModel.completeRequest(
                        request.requestId
                    )

                    navController.popBackStack()
                },

                modifier = Modifier.fillMaxWidth()

            ) {

                Text("Delete Request")
            }

        }
    }
}


@Composable
fun OrganizationRequestDetailsScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize()
    ){






    }//main column

}
@Preview(showBackground = true)
@Composable
fun OrganizationRequestDetailsScreenPreview(){

    OrganizationRequestDetailsScreen(rememberNavController())

}