package com.cherry.carenet.ui.screens.trustandidentity

//import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cherry.carenet.models.BlockedUser
//import com.cherry.carenet.models.BlockedUser
import com.cherry.carenet.ui.theme.SoftPowderBlue
import com.cherry.carenet.ui.theme.SoftTealGreen
import com.cherry.carenet.ui.theme.WarmCream

@Composable
fun BlockedUsersScreen(navController: NavController){

val blockedUsers= remember {
    mutableStateListOf(

        BlockedUser("Mary Koech", "Spam"),
        BlockedUser("Barry Wephukulu","Physical Assault")

        )

}




    Column(modifier = Modifier
        .fillMaxSize()
        . background( brush = Brush.verticalGradient(
            colors = listOf(
                SoftPowderBlue,
                Color.White
            )
        ))
        .padding(16.dp)
    ) {
Text(text = "Blocked Users",
    fontSize = 22.sp,
    fontWeight = FontWeight.Bold,
    )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            items(blockedUsers){
                user->


                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(6.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        // Avatar Circle
                        Box(
                            modifier = Modifier
                                .size(45.dp)
                                .background(SoftPowderBlue, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = user.name.first().toString(),
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        // User Info
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = user.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )

                            Text(
                                text = user.reason,
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }

                        // Unblock Button
                        TextButton(
                            onClick = {
                                blockedUsers.remove(user)
                            } ) {
                            Text(
                                text = "Unblock",
                                color = SoftTealGreen,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
            }//items


        }//lazy column


    }


@Preview(showBackground = true)
@Composable
fun BlockedUsersScreenPreview(){
    BlockedUsersScreen(rememberNavController())

}