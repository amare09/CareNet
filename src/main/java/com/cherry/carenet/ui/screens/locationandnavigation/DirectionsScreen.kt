package com.cherry.carenet.ui.screens.locationandnavigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun DirectionsScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize()
    ){






    }//main column

}
@Preview(showBackground = true)
@Composable
fun DirectionsScreenPreview(){

    DirectionsScreen(rememberNavController())

}