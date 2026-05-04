package com.example.letssopt.presentation.onboarding.login.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.letssopt.core.extension.clearBackStackNavOptions
import com.example.letssopt.navigation.Route
import com.example.letssopt.presentation.home.navigation.navigateToHome
import com.example.letssopt.presentation.onboarding.login.LoginRoute
import com.example.letssopt.presentation.onboarding.login.LoginViewModel
import com.example.letssopt.presentation.onboarding.signup.navigateToSignUp
import kotlinx.serialization.Serializable

fun NavController.navigateToLogin(
    navOptions: NavOptions? = null
) = navigate(Login, navOptions)

fun NavGraphBuilder.loginGraph(
    navController: NavController,
    innerPadding: PaddingValues,
) {
    composable<Login> { backStackEntry ->
        val viewModel: LoginViewModel = viewModel(backStackEntry)
        LoginRoute(
            viewModel = viewModel,
            navigateToSignUp = navController::navigateToSignUp,
            navigateToHome = {
                navController.apply {
                    navigateToHome(navOptions = clearBackStackNavOptions())
                }
            },
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Serializable
data object Login : Route

