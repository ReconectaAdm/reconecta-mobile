package br.com.reconecta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import br.com.reconecta.screens.HomeScreen
import br.com.reconecta.screens.LoginScreen
import br.com.reconecta.screens.OrganizationListScreen
import br.com.reconecta.screens.RegisterScreen
import br.com.reconecta.screens.SchedulingScreen
import br.com.reconecta.screens.ScreenNames
import br.com.reconecta.ui.theme.ReconectaTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ReconectaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(id = R.color.reconecta_background),
                ) {
                    val navController = rememberAnimatedNavController()
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = ScreenNames.HOME.path,
                        exitTransition = {
                            slideOutOfContainer(
                                towards = AnimatedContentScope.SlideDirection.End,
                                tween(500)
                            )
                        },
                        enterTransition = {
                            slideIntoContainer(
                                towards = AnimatedContentScope.SlideDirection.Down,
                                animationSpec = tween(500)
                            )
                        }
                    ) {
                        composable(route = ScreenNames.LOGIN.path) {
                            LoginScreen(navController)
                        }
                        composable(route = ScreenNames.REGISTER.path) {
                            RegisterScreen(navController)
                        }
                        composable(route = ScreenNames.HOME.path) {
                            HomeScreen(navController)
                        }
                        composable(route = ScreenNames.SCHEDULING.path){
                            SchedulingScreen(navController)
                        }
                        composable(route = ScreenNames.ORGANIZATIONLIST.path){
                            OrganizationListScreen(navController)
                        }
                    }
                }
            }
        }
    }
}