package com.example.jetpackcomposeauthui

import EditProfileScreen
import HomePageScreen
import SignupScreen
import WeatherScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposeauthui.data.api.RetrofitClient.apiService
import com.example.jetpackcomposeauthui.data.models.VisionViewModel
import com.example.jetpackcomposeauthui.data.models.WeatherViewModel
import com.example.jetpackcomposeauthui.ui.screens.VisionScreen
import com.example.jetpackcomposeauthui.ui.theme.JetpackComposeAuthUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeAuthUITheme {
                /// Let just add navigation so users can go from one screen to another
                NavigationView()
            }
        }
    }
}
@Composable
fun NavigationView() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("signup") { SignupScreen(navController) }
        composable("homepage") { HomePageScreen(navController) }
        composable("weather") {
            val weatherViewModel: WeatherViewModel = viewModel()
            WeatherScreen(
                viewModel = weatherViewModel,
                onBackClick = { navController.navigateUp() }
            )
        }
        composable("editprofile")
        {
            EditProfileScreen(navController = navController)
        }
        composable("crop_health") {
            val visionViewModel: VisionViewModel = viewModel()
            VisionScreen(navController, visionViewModel)
        }



    }
}