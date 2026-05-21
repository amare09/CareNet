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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    navController: NavController,
    userName: String,
    roomId: String,
    //receiverId: String,
    viewModel: ChatViewModel = viewModel()
) {

    val auth = FirebaseAuth.getInstance()

    val currentUserId =
        auth.currentUser?.uid
    if (currentUserId==null){
        Text("Not logged in")
        return

    }

    var message by remember {
        mutableStateOf("")
    }

    // ✅ LOAD REAL-TIME MESSAGES
    LaunchedEffect(roomId) {
        viewModel.loadMessages(roomId)
    }

    Scaffold(
        topBar = {

            CenterAlignedTopAppBar(

                title = {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        // ✅ PROFILE ICON
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Column {

                            Text(
                                text = userName,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 16.sp
                            )

                            Text(
                                text = "Online",
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 12.sp
                            )
                        }
                    }
                },

                navigationIcon = {

                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {

                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },

               // actions = {

                    // ✅ THREE DOT MENU ICON
                 //   IconButton(
                   //     onClick = {
                            // later menu actions
                     //   }
                   // ) {

                     //   Icon(
                       //     imageVector = Icons.Default.MoreVert,
                         //   contentDescription = "Menu",
                           // tint = Color.White
                        //)
                    //}
                //},

                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF7FA1C3)
                )
            )
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF7F3ED))
        ) {

            // ✅ CHAT AREA
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {

                val messages = viewModel.messages ?: emptyList()

                messages.forEach { msg ->

                    val isMe =
                        msg.senderId== currentUserId

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
                                if (isMe) "Me" else msg.senderName.ifEmpty { userName },
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

                        val safeMessage = message.trim()

                        if (safeMessage.isNotEmpty()) {

                            val receiverId = roomId.replace(currentUserId, "")

                            viewModel.sendMessage(
                                roomId = roomId,
                                senderId = currentUserId,
                                receiverId = receiverId,
                                text = message
                            )

                            message = ""
                        }
                    }
                )
                {

                    Text("Send")
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ChatScreenPreview(){

    ChatScreen(rememberNavController(),

    userName="Mary",
        roomId = "",

    )

}