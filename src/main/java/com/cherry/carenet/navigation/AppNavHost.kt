package com.cherry.carenet.navigation

//import HelpDetailsScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.Navigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cherry.carenet.ui.screens.authentication.AppCheckScreen
import com.cherry.carenet.ui.screens.authentication.LoginScreen
import com.cherry.carenet.ui.screens.authentication.PermissionsScreen
import com.cherry.carenet.ui.screens.authentication.RegisterScreen
import com.cherry.carenet.ui.screens.authentication.RoleSelectionScreen
import com.cherry.carenet.ui.screens.authentication.SplashScreen
import com.cherry.carenet.ui.screens.communication.ChatScreen
import com.cherry.carenet.ui.screens.maincorenavigation.CreateHelpRequestScreen
import com.cherry.carenet.ui.screens.maincorenavigation.HomeScreen

import com.cherry.carenet.ui.screens.onboarding.OnboardingScreen1
//import com.cherry.carenet.ui.screens.organizationmodule.OrganizationRequestDetailsScreen
import com.cherry.carenet.ui.screens.requestmanagement.HelpDetailsScreen
import java.util.jar.Attributes
import androidx.navigation.navArgument
import com.cherry.carenet.ui.screens.communication.ChatListScreen
import com.cherry.carenet.ui.screens.maincorenavigation.ProfileScreen
import com.cherry.carenet.ui.screens.notifications.NotificationsScreen
import com.cherry.carenet.ui.screens.organization.EmergencyMonitorScreen
import com.cherry.carenet.ui.screens.organization.OrganizationAnalyticsScreen
import com.cherry.carenet.ui.screens.organizationmodule.OrganizationRequestDetailsScreen
import com.cherry.carenet.ui.screens.requestmanagement.ReceiveRequestScreen
import com.cherry.carenet.ui.screens.settingsandsupport.SettingsScreen
import com.cherry.carenet.ui.screens.trustandidentity.BlockedUsersScreen

//import com.cherry.carenet.ui.screens.organization.OrganizationRequestDetailsScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SPLASH
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUTE_HOME) {
            HomeScreen(navController)
        }
        composable(ROUTE_SPLASH) {
            SplashScreen(navController)
        }
        composable(ROUTE_APPCHECK) {
            AppCheckScreen(navController)
        }
        composable(ROUTE_REGISTER) {
            RegisterScreen(navController)
        }
        composable(ROUTE_LOGIN) {
            LoginScreen(navController)
        }
        composable(ROUTE_PERMISSIONS) {
            PermissionsScreen(navController)
        }
        composable(ROUTE_ROLE_SELECTION) {
            RoleSelectionScreen(navController)
        }
        composable(ROUTE_REQUEST_HELP) {
            CreateHelpRequestScreen(navController)
        }

        composable("$ROUTE_HELP_DETAILS/{requestId}",
            arguments = listOf(navArgument("requestId") { type = NavType.StringType })
        ) { backStack ->

            val requestId = backStack.arguments?.getString("requestId") ?: ""

            HelpDetailsScreen(navController, requestId)


        }
        //composable(
          //  "chat/{roomId}/{userName}"
        //) { backStack ->

          //  val roomId =
            //    backStack.arguments?.getString("roomId") ?: ""

            //val userName =
              //  backStack.arguments?.getString("userName") ?: ""
            //ChatScreen(
              //  navController = navController,
                //userName = userName,
               // roomId = roomId
           // )
        //}



        composable(
            "$ROUTE_CHAT/{roomId}/{userName}"
        ) { backStack ->

            val roomId =
                backStack.arguments?.getString("roomId") ?: ""

            val userName =
                backStack.arguments?.getString("userName") ?: ""

            ChatScreen(
                navController = navController,
                userName = userName,
                roomId = roomId
            )
        }



        composable(
            "$ROUTE_ORGANIZATION_REQUEST_DETAILS/{requestId}",
            arguments = listOf(
                navArgument("requestId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val requestId =
                backStackEntry.arguments
                    ?.getString("requestId") ?: ""

            OrganizationRequestDetailsScreen(
                navController = navController,
                requestId = requestId
            )
        }


        composable(ROUTE_NOTIFICATIONS) {

            NotificationsScreen(navController)
        }

        composable(ROUTE_ORGANIZATION_ANALYTICS) {

            OrganizationAnalyticsScreen(navController)
        }

        composable(ROUTE_RECEIVE_REQUEST) {

            ReceiveRequestScreen(navController)
        }

        composable(ROUTE_PROFILE) {

            ProfileScreen(navController)
        }

        composable(ROUTE_BLOCKED_USERS) {

            BlockedUsersScreen(navController)
        }

        composable(ROUTE_EMERGENCY_MONITOR) {

            EmergencyMonitorScreen(navController)
        }


        composable(ROUTE_CHAT_LIST) {
            ChatListScreen(navController)


        }
        composable(ROUTE_ONBOARDINGSCREEN1) {
            OnboardingScreen1(navController)


        }


        composable(ROUTE_SETTINGS) {
            SettingsScreen(navController)
        }

















    }
}