package com.cherry.carenet.ui.screens.communication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.cherry.carenet.navigation.ROUTE_CHAT
import com.cherry.carenet.ui.theme.SoftPowderBlue
import com.cherry.carenet.viewmodel.BlockedUsersViewModel
import com.cherry.carenet.viewmodel.ChatRoomViewModel
import com.google.firebase.auth.FirebaseAuth
@Composable
fun ChatItem(
    name: String,
    lastMessage: String,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() },

        shape = RoundedCornerShape(20.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // PROFILE CIRCLE
            Box(
                modifier = Modifier
                    .size(58.dp)
                    .clip(CircleShape)
                    .background(SoftPowderBlue.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = SoftPowderBlue,
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {

                Text(
                    text = name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = if (lastMessage.isEmpty())
                        "Start a conversation..."
                    else
                        lastMessage,

                    fontSize = 13.sp,
                    color = Color.Gray,
                    maxLines = 1
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatListScreen(navController: NavController) {

    val viewModel: ChatRoomViewModel = viewModel()
    val blockedViewModel: BlockedUsersViewModel = viewModel()

    val currentUserId =
        FirebaseAuth.getInstance().currentUser?.uid ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        SoftPowderBlue,
                        Color.White
                    )
                )
            )  ) {

        // 🔵 TOP APP BAR (CLEAN)
        TopAppBar(
            title = {
                Text(
                    text = "Messages",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            },

            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = SoftPowderBlue
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        // CONTENT AREA
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {

            // EMPTY STATE
            if (viewModel.rooms.isEmpty()) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No chats yet",
                        color = Color.Gray,
                        fontSize = 16.sp
                    )
                }
                return
            }

            // CHAT LIST
            viewModel.rooms.forEach { room ->

                val otherUserName =
                    if (room.user1Id == currentUserId)
                        room.user2Name
                    else
                        room.user1Name

                val otherUserId =
                    if (room.user1Id == currentUserId)
                        room.user2Id
                    else
                        room.user1Id

                // BLOCK FILTER
                if (blockedViewModel.blockedUsers.contains(otherUserId)) return@forEach

                ChatItem(
                    name = otherUserName,
                    lastMessage = room.lastMessage,
                    onClick = {
                        navController.navigate(
                            "$ROUTE_CHAT/${room.roomId}/$otherUserName"
                        )
                    }
                )
            }
        }
    }
}