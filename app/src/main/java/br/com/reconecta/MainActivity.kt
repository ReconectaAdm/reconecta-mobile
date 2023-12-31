package br.com.reconecta

import EditPerfilScreen
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
import br.com.reconecta.screens.EstablishmentCollectScreen
import androidx.navigation.NavType
import androidx.navigation.navArgument
import br.com.reconecta.enums.EScreenNames
import br.com.reconecta.screens.EditScreen
import br.com.reconecta.screens.EditAvailabilityScreen
import br.com.reconecta.screens.EditBankAccountScreen
import br.com.reconecta.screens.EditPasswordScreen
import br.com.reconecta.screens.EditResiduesScreen
import br.com.reconecta.screens.EstablishmentMetricsScreen
import br.com.reconecta.screens.HomeOrganizationScreen
import br.com.reconecta.screens.HomeEstablishmentScreen
import br.com.reconecta.screens.LoginScreen
import br.com.reconecta.screens.OrganizationCollectInProgressScreen
import br.com.reconecta.screens.OrganizationCollectScreen
import br.com.reconecta.screens.OrganizationDetailsScreen
import br.com.reconecta.screens.OrganizationListScreen
import br.com.reconecta.screens.OrganizationMetricsScreen
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
                        composable(route = EScreenNames.HOME_ESTABLISHMENT.path) {
                            HomeEstablishmentScreen(navController, applicationContext)
                        }
                        composable(
                            route = "${EScreenNames.ORGANIZATION_DETAILS.path}/{organizationId}",
                            arguments = listOf(navArgument("organizationId") {
                                type = NavType.IntType
                            })
                        ) {
                            val primitiveValue = it.arguments?.getInt("organizationId", 0)!!
                            OrganizationDetailsScreen(
                                navController,
                                applicationContext,
                                primitiveValue
                            )
                        }
                        composable(route = EScreenNames.HOME_ORGANIZATION.path) {
                            HomeOrganizationScreen(navController)
                        }
                        composable(
                            route = "${EScreenNames.SCHEDULING.path}/{organizationId}?residueIds={residueIds}",
                            arguments = (listOf(
                                navArgument("residueIds") { type = NavType.IntArrayType },
                                navArgument("organizationId") { type = NavType.IntType }
                            )))
                        { it ->
                            val residueIds = it.arguments?.getIntArray("residueIds")!!
                            val organizationId: Int? = it.arguments?.getInt("organizationId", 0)

                            SchedulingScreen(
                                navController,
                                applicationContext,
                                organizationId!!,
                                residueIds.map { residueId -> residueId })
                        }
                        composable(
                            route = "${EScreenNames.ORGANIZATION_LIST.path}/{residueTypeId}",
                            arguments = listOf(navArgument("residueTypeId") {
                                type = NavType.IntType
                            })
                        ) {
                            val residueTypeId = it.arguments?.getInt("residueTypeId", 0)!!
                            OrganizationListScreen(navController, applicationContext, residueTypeId)
                        }
                        composable(
                            route = "${EScreenNames.ORGANIZATION_COLLECT_IN_PROGRESS.path}/{collectId}",
                            arguments = (
                                    listOf(navArgument("collectId") { type = NavType.IntType }))
                        ) {
                            val collectId: Int? = it.arguments?.getInt("collectId", 0)
                            OrganizationCollectInProgressScreen(
                                navController,
                                applicationContext,
                                collectId!!
                            )
                        }
                        composable(route = EScreenNames.RESET_PASSWORD.path) {
                            ResetPasswordScreen(navController, applicationContext)
                        }
                        composable(route = EScreenNames.ESTABLISHMENT_COLLECT.path){
                            EstablishmentCollectScreen(navController, applicationContext)
                        }
                        composable(route = EScreenNames.ORGANIZATION_COLLECT.path) {
                            OrganizationCollectScreen(navController, applicationContext)
                        }
                        composable(route = EScreenNames.ACCOUNT_INFO.path) {
                            EditScreen(applicationContext, navController)
                        }
                        composable(route = EScreenNames.ACCOUNT_INFO_EDIT_PASSWORD.path) {
                            EditPasswordScreen(applicationContext, navController)
                        }
                        composable(route = EScreenNames.ACCOUNT_INFO_EDIT_PERFIL.path) {
                            EditPerfilScreen(applicationContext, navController)
                        }
                        composable(route = EScreenNames.ACCOUNT_INFO_EDIT_WALLET.path) {
                            EditBankAccountScreen(applicationContext, navController)
                        }
                        composable(route = EScreenNames.ACCOUNT_INFO_EDIT_AVAILABILITY.path) {
                            EditAvailabilityScreen(navController, applicationContext)
                        }
                        composable(route = EScreenNames.ACCOUNT_INFO_EDIT_RESIDUES.path) {
                            EditResiduesScreen(applicationContext, navController)
                        }
                        composable(route = EScreenNames.ESTABLISHMENT_METRICS.path){
                            EstablishmentMetricsScreen(applicationContext, navController)
                        }
                        composable(route = EScreenNames.ORGANIZATION_METRICS.path){
                            OrganizationMetricsScreen(applicationContext, navController)
                        }
                    }
                }
            }
        }
    }
}