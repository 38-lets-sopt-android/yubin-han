package com.example.letssopt.presentation.onboarding.signup

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
import com.example.letssopt.presentation.onboarding.login.navigation.navigateToLogin
import kotlinx.serialization.Serializable

fun NavController.navigateToSignUp(
    navOptions: NavOptions? = null
) = navigate(SignUp, navOptions)

fun NavGraphBuilder.signUpGraph(
    navController: NavController,
    innerPadding: PaddingValues,
) {
    composable<SignUp> { backStackEntry ->
        val viewModel: SignUpViewModel = viewModel(backStackEntry)
        SignUpRoute(
            viewModel = viewModel,
            navigateToLogin = { navController.navigateToLogin(clearBackStackNavOptions()) },
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Serializable
data object SignUp : Route
