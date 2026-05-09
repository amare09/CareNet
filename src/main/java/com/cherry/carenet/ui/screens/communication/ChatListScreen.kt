package com.cherry.carenet.ui.screens.communication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    onClick: () -> Unit
) {
    androidx.compose.material3.Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = SoftPowderBlue
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(text = name, fontSize = 16.sp)
        }
    }
}
@Composable
fun ChatListScreen(navController: NavController) {

    val viewModel: ChatRoomViewModel = viewModel()
    val blockedViewModel: BlockedUsersViewModel = viewModel()

    val currentUserId =
        FirebaseAuth.getInstance().currentUser?.uid ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F3ED))
            .padding(16.dp)
    ) {

        Text(
            text = "Chats",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = SoftPowderBlue
        )

        Spacer(modifier = Modifier.height(12.dp))

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

            // ❌ Blocked users filter
            if (blockedViewModel.blockedUsers.contains(otherUserId)) {
                return@forEach
            }

            ChatItem(
                name = otherUserName,
                onClick = {

                    navController.navigate(
                        "$ROUTE_CHAT/${room.roomId}/$otherUserName"
                    )
                }
            )

            Text(
                text = room.lastMessage.ifEmpty { "No messages yet" },
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
            )
        }
    }
}