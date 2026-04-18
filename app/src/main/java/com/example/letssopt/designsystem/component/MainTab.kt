package com.example.letssopt.designsystem.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.letssopt.R
import com.example.letssopt.navigation.Main
import com.example.letssopt.navigation.MainTabRoute
import com.example.letssopt.navigation.Purchase
import com.example.letssopt.navigation.Route
import com.example.letssopt.navigation.Search
import com.example.letssopt.navigation.Storage
import com.example.letssopt.navigation.Webtoon

//TODO 네비게이션 구현시 unselected ic 등록 및 변경
enum class MainTab(
    @DrawableRes val selectedIconRes: Int,
    @DrawableRes val unselectedIconRes: Int,
    @StringRes val titleRes: Int,
    val route: MainTabRoute,
) {
    MAIN(
        selectedIconRes = R.drawable.ic_watcha_selected,
        unselectedIconRes = R.drawable.ic_watcha_unselected,
        titleRes = R.string.main,
        route = Main,
    ),
    PURCHASE(
        selectedIconRes = R.drawable.ic_purchase_unselected,
        unselectedIconRes = R.drawable.ic_purchase_unselected,
        titleRes = R.string.purchase,
        route = Purchase,
    ),
    WEBTOON(
        selectedIconRes = R.drawable.ic_webtoon_unselected,
        unselectedIconRes = R.drawable.ic_webtoon_unselected,
        titleRes = R.string.webtoon,
        route = Webtoon,
    ),
    SEARCH(
        selectedIconRes = R.drawable.ic_search_unselected,
        unselectedIconRes = R.drawable.ic_search_unselected,
        titleRes = R.string.search,
        route = Search,
    ),
    STORAGE(
        selectedIconRes = R.drawable.ic_storage_unselected,
        unselectedIconRes = R.drawable.ic_storage_unselected,
        titleRes = R.string.storage,
        route = Storage,
    );

    companion object {
        fun find(predicate: (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        fun contains(predicate: (Route) -> Boolean): Boolean {
            return entries.any { predicate(it.route) }
        }
    }
}

