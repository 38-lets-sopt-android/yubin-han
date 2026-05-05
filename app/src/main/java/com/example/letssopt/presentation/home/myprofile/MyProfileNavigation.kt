package com.example.letssopt.presentation.home.myprofile

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.letssopt.navigation.Route
import com.example.letssopt.presentation.home.myprofile.userlist.navigateToUserList
import kotlinx.serialization.Serializable

fun NavController.navigateToMyProfile(
    navOptions: NavOptions? = null,
) = navigate(MyProfile, navOptions)

fun NavGraphBuilder.myProfileGraph(
    navController: NavController,
    innerPadding: PaddingValues,
) {
    composable<MyProfile> {
        MyProfileRoute(
            paddingValues = innerPadding,
            navigateToUserList = navController::navigateToUserList,
        )
    }
}

@Serializable
data object MyProfile : Route
