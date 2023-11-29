package com.example.movieapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.ui.screen.about.AboutScreen
import com.example.movieapp.ui.screen.detail.DetailScreen
import com.example.movieapp.ui.screen.main.MainScreen
import com.example.movieapp.ui.screen.splashscreen.SplashScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MovieApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController,
        startDestination = "SplashScreen",
        modifier = modifier
    ) {
        composable("SplashScreen") {
            SplashScreen(
                navController = navController
            )
        }
        composable(
            route = "MainScreen",
            content = {
                MainScreen(
                    navController = navController
                )
            }
        )

        composable(
            route = "DetailScreen/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.StringType }),
            content = { backStackEntry ->
                val arguments = requireNotNull(backStackEntry.arguments)
                val movieId = arguments.getString("movieId") ?: ""

                DetailScreen(
                    movieId = movieId,
                    navController = navController
                )
            }
        )

        composable(
            route = "AboutScreen",
            content = {
                AboutScreen(
                    navController = navController
                )
            }
        )
    }
}