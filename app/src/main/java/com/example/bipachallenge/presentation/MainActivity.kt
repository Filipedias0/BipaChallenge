package com.example.bipachallenge.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.bipachallenge.presentation.feature.nodeRankings.MainScreen
import com.example.bipachallenge.ui.theme.BipaChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BipaChallengeTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main_screen") {
        composable("main_screen") {
            MainScreen(navController)
        }
//        composable(
//            "details_screen/{publicKey}",
//            arguments = listOf(navArgument("publicKey") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val publicKey = backStackEntry.arguments?.getString("publicKey")
//            DetailsScreen(publicKey = publicKey)
//        }
    }
}
