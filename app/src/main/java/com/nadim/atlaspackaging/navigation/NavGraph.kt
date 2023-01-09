package com.nadim.atlaspackaging.navigation


import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nadim.atlaspackaging.archive_feature.presentation.ArchiveScreen
import com.nadim.atlaspackaging.daily_production_feature.presentation.DailyProductionScreen
import com.nadim.atlaspackaging.login_feature.presentation.LoginScreen
import com.nadim.atlaspackaging.machine_feature.presentation.MachineScreen
import com.nadim.atlaspackaging.main_feature.presentation.MainScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    appContext: Context
){
    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ){
        composable(
            route = Screen.LoginScreen.route
        ){
            LoginScreen(navController = navController)
        }
        composable(
            route = Screen.MainScreen.route,
        ){
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.MachineScreen.route,
            arguments = listOf(
                navArgument("machine"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ){
            MachineScreen(
                navController = navController,
                machine = it.arguments?.getString("machine")
            )
        }
        composable(
            route = Screen.DailyProductionScreen.route,
            arguments = listOf(
                navArgument("machine"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ){
            DailyProductionScreen(
                navController = navController,
                machine = it.arguments?.getString("machine")
            )
        }
        composable(
            route = Screen.ArchiveScreen.route,
            arguments = listOf(
                navArgument("machine"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ){
            ArchiveScreen(
                navController = navController,
                machine = it.arguments?.getString("machine"),
                appContext = appContext
            )
        }
    }
}