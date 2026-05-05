package com.example.letssopt.presentation.home.myprofile.userlist
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.letssopt.navigation.Route
import kotlinx.serialization.Serializable


fun NavController.navigateToUserList(
    navOptions: NavOptions? = null,
) = navigate(UserList, navOptions)

fun NavGraphBuilder.userListGraph(
    navController: NavController,
    innerPadding: PaddingValues,
) {
    composable<UserList> {
        UserListRoute(
            paddingValues = innerPadding,
        )
    }
}
@Serializable
data object UserList : Route
