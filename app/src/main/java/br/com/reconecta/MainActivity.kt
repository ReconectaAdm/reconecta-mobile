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
import br.com.reconecta.screens.EScreenNames
import br.com.reconecta.screens.HomeEstablishmentScreen
import br.com.reconecta.screens.HomeScreen
import br.com.reconecta.screens.LoginScreen
import br.com.reconecta.screens.OrganizationDetailsScreen
import br.com.reconecta.screens.OrganizationListScreen
import br.com.reconecta.screens.ResetPasswordScreen
import br.com.reconecta.screens.SchedulingScreen
import br.com.reconecta.screens.SignUpScreen
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
                    color = colorResource(id = R.color.white),
                ) {

                    val navController = rememberAnimatedNavController()
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = EScreenNames.LOGIN.path,
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
                        composable(route = EScreenNames.LOGIN.path) {
                            LoginScreen(navController, applicationContext)
                        }
                        composable(route = EScreenNames.REGISTER.path) {
                            SignUpScreen(navController, applicationContext)
                        }
                        composable(route = EScreenNames.HOME.path) {
                            HomeScreen(navController)
                        }
                        composable(route = EScreenNames.ORGANIZATION_DETAILS.path) {
                            OrganizationDetailsScreen(navController)
                        }
                        composable(route = EScreenNames.HOME_ESTABLISHMENT.path) {
                            HomeEstablishmentScreen(navController)
                        }
                        composable(route = EScreenNames.SCHEDULING.path){
                            SchedulingScreen(navController)
                        }
                        composable(route = EScreenNames.ORGANIZATION_LIST.path){
                            OrganizationListScreen(navController, applicationContext)
                        }
                        composable(route = EScreenNames.RESET_PASSWORD.path){
                            ResetPasswordScreen(navController, applicationContext)
                        }
                    }
                }
            }
        }
    }
}