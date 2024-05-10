package org.d3if3148.asses1.navigation

import org.d3if3148.asses1.ui.screen.KEY_ID_EXPENSES

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object FormBaru: Screen("detailScreen")
    data object FormUbah: Screen("detailScreen/{$KEY_ID_EXPENSES}"){
        fun withId (id: Long) = "detailScreen/$id"
    }
    data object About: Screen("aboutScreen")
}