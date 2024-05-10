package org.d3if3148.asses1.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object FormBaru: Screen("detailScreen")
    data object About: Screen("aboutScreen")
}