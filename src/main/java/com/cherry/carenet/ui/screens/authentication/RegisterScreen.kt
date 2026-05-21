package com.cherry.carenet.ui.screens.authentication

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.cherry.carenet.navigation.ROUTE_HOME
import com.cherry.carenet.navigation.ROUTE_LOGIN
import com.cherry.carenet.ui.theme.SoftPowderBlue
import com.cherry.carenet.viewmodel.AuthViewModel


@Composable
fun RegisterScreen(navController: NavController){
    val authViewModel: AuthViewModel = viewModel()
    var name by remember { mutableStateOf("") }
   var email by remember { mutableStateOf("") }
   var homeaddress by remember { mutableStateOf("") }
   var phone by remember { mutableStateOf("") }
   var password by remember { mutableStateOf("") }
   var confirmpassword by remember { mutableStateOf("") }
   var selectRole by remember { mutableStateOf("") }



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
          modifier = Modifier
              .fillMaxSize()
              .padding(20.dp),
          horizontalAlignment = Alignment.CenterHorizontally
      ) {
          Spacer(modifier = Modifier.height(40.dp))

          Text(
              text = "Create an Account",
              fontSize = 26.sp,
              fontWeight = FontWeight.Bold,
              color = Color.Black


          )

          Spacer(modifier = Modifier.height(10.dp))

          Text(
              text = "Become a member and help your community",
              fontSize = 14.sp,
              color =Color.Black
          )

          Spacer(modifier = Modifier.height(30.dp))

          //FORM CARD

          Card(
              shape = RoundedCornerShape(20.dp),
              elevation = CardDefaults.cardElevation(8.dp),
              colors = CardDefaults.cardColors(containerColor = Color.White),
              modifier = Modifier.fillMaxWidth()
          ) {
              Column(
                  modifier = Modifier.padding(16.dp)
              ) {
                  //NAME

                  OutlinedTextField(
                      value = name,
                      onValueChange = {name=it},
                      label = {Text(text = "Enter Your Full Name")},
                      modifier = Modifier.fillMaxWidth(),
                      leadingIcon = {
                          Icon(imageVector = Icons.Default.Person, contentDescription = "")

                      },
                      colors = OutlinedTextFieldDefaults.colors(
                          focusedBorderColor = SoftPowderBlue,
                          unfocusedBorderColor = SoftPowderBlue

                      )




                  )
Spacer(modifier = Modifier.height(12.dp))

//EMAIL
                  OutlinedTextField(
                      value = email,
                      onValueChange = {email=it},
                      label = {Text(text = "Input Your Email Here")},
                      modifier = Modifier.fillMaxWidth(),
                      leadingIcon = {Icon(imageVector = Icons.Default.Email, contentDescription = "")},
                      colors = OutlinedTextFieldDefaults.colors(
                          focusedBorderColor = SoftPowderBlue,
                          unfocusedBorderColor = SoftPowderBlue




                      )




                  )
                  Spacer(modifier = Modifier.height(12.dp))

//PHONE
                  OutlinedTextField(
                      value = phone,
                      onValueChange = {phone=it},
                      label = {Text(text = "Input Your Phone Number Here")},
                      modifier = Modifier.fillMaxWidth(),
                      leadingIcon = {Icon(imageVector = Icons.Default.Phone, contentDescription = "")},
                      colors = OutlinedTextFieldDefaults.colors(
                          focusedBorderColor = SoftPowderBlue,
                          unfocusedBorderColor = SoftPowderBlue




                      )




                  )
                  Spacer(modifier = Modifier.height(12.dp))


//PHONE
                  OutlinedTextField(
                      value = homeaddress,
                      onValueChange = {homeaddress=it},
                      label = {Text(text = "Input Your Home Address Here")},
                      modifier = Modifier.fillMaxWidth(),
                      leadingIcon = {Icon(imageVector = Icons.Default.Mail, contentDescription = "")},
                      colors = OutlinedTextFieldDefaults.colors(
                          focusedBorderColor = SoftPowderBlue,
                          unfocusedBorderColor = SoftPowderBlue




                      )




                  )
                  Spacer(modifier = Modifier.height(12.dp))



//PHONE
                  OutlinedTextField(
                      value = password,
                      onValueChange = {password=it},
                      label = {Text(text = "Set Your Password")},
                      modifier = Modifier.fillMaxWidth(),
                      leadingIcon = {Icon(imageVector = Icons.Default.Lock, contentDescription = "")},
                      colors = OutlinedTextFieldDefaults.colors(
                          focusedBorderColor = SoftPowderBlue,
                          unfocusedBorderColor = SoftPowderBlue




                      )




                  )
                  Spacer(modifier = Modifier.height(12.dp))



//CONFIRM PASSWORD
                  OutlinedTextField(
                      value = confirmpassword,
                      onValueChange = {confirmpassword=it},
                      label = {Text(text = "Repeat Your Password")},
                      modifier = Modifier.fillMaxWidth(),
                      leadingIcon = {Icon(imageVector = Icons.Default.Password, contentDescription = "")},
                      colors = OutlinedTextFieldDefaults.colors(
                          focusedBorderColor = SoftPowderBlue,
                          unfocusedBorderColor = SoftPowderBlue




                      )




                  )
                  Spacer(modifier = Modifier.height(20.dp))
//REGISTER BUTTON

                  Button(
                      onClick = {

                          if (password == confirmpassword) {

                              authViewModel.registerUser(
                                  name = name,
                                  email = email,
                                  password = password,
                                  address = homeaddress,   // ✅ FIXED
                                  role = selectRole
                              ) { success, error ->

                                  if (success) {
                                      navController.navigate(ROUTE_HOME) {
                                          popUpTo(ROUTE_HOME){inclusive=true}
                                      }
                                  } else {
                                      println("REGISTER ERROR: $error")
                                  }
                              }
                          } else {
                              println("PASSWORDS DO NOT MATCH")
                          }
                      },
                      modifier = Modifier.fillMaxWidth(),
                      shape = RoundedCornerShape(12.dp),
                      colors = ButtonDefaults.buttonColors(SoftPowderBlue)
                  ) {
                      Text("Create Account", color = Color.White)
                  }

                  Spacer(modifier = Modifier.height(10.dp))

                  TextButton(
                      onClick = {
                          navController.navigate(ROUTE_LOGIN)
                      }
                  ) {
                      Text("Already have an account? Login")
                  }


              }//column





          }//card




      }


    }

}
@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview(){

    RegisterScreen(rememberNavController())

}