package dev.sobhy.retrofitexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.sobhy.retrofitexample.ui.screens.home.HomeScreen
import dev.sobhy.retrofitexample.ui.screens.home.HomeViewModel
import dev.sobhy.retrofitexample.ui.screens.savednotes.SavedScreen
import dev.sobhy.retrofitexample.ui.screens.savednotes.SavedViewModel
import dev.sobhy.retrofitexample.ui.theme.RetrofitExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitExampleTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home"){
                    composable("home"){
                        val homeViewModel: HomeViewModel by viewModels()
                        HomeScreen(viewModel = homeViewModel, navController)
                    }
                    composable("saved"){
                        val savedViewModel: SavedViewModel by viewModels()
                        SavedScreen(savedViewModel)
                    }
                }
            }
        }
    }
}
