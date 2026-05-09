package com.cherry.carenet.ui.screens.communication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cherry.carenet.viewmodel.ChatViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ChatScreen(
    navController: NavController,
    userName: String,
    roomId: String,
    viewModel: ChatViewModel = viewModel()
) {

    val auth = FirebaseAuth.getInstance()

    val currentUserId =
        auth.currentUser?.uid ?: ""

    var message by remember {
        mutableStateOf("")
    }



    // ✅ LOAD REAL-TIME MESSAGES
    LaunchedEffect(roomId) {
        viewModel.loadMessages(roomId)
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F3ED))
    ) {

        // ✅ HEADER
        Text(
            text = "Chat with $userName",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))



        // ✅ CHAT AREA
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {

            viewModel.messages.forEach { msg ->

                val isMe =
                    msg.sender == currentUserId

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        if (isMe)
                            Arrangement.End
                        else
                            Arrangement.Start
                ) {

                    Column(
                        modifier = Modifier
                            .padding(6.dp)
                            .background(
                                if (isMe)
                                    Color(0xFFDCF8C6)
                                else
                                    Color.White
                            )
                            .padding(10.dp)
                    ) {

                        Text(
                            if (isMe) "Me" else userName,
                            fontSize = 10.sp,
                            color = Color.Gray
                        )

                        Text(msg.message)
                    }
                }
            }
        }



        // ✅ INPUT AREA
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            TextField(
                value = message,
                onValueChange = {
                    message = it
                },
                modifier = Modifier.weight(1f),
                placeholder = {
                    Text("Type message...")
                }
            )



            Button(
                onClick = {

                    if (message.isNotBlank()) {

                        viewModel.sendMessage(
                            roomId = roomId,
                            senderId = currentUserId,
                            receiverId = userName,
                            text = message
                        )

                        message = ""
                    }
                }
            ) {

                Text("Send")
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ChatScreenPreview(){

    ChatScreen(rememberNavController(),

    userName="Mary",
        roomId = ""
    )

}