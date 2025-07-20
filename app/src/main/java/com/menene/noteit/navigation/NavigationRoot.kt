package com.menene.noteit.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.menene.noteit.presentation.HomeScreenUi
import com.menene.noteit.presentation.LandingScreenUi

@Composable
fun NavigationRoot(
    hasSeenLanding: Boolean? = null,
) {
    val backstack = rememberNavBackStack(if (hasSeenLanding!!) HomeScreen else LandingScreen)

    NavDisplay(
        backStack = backstack,
        entryDecorators = listOf(
            rememberSceneSetupNavEntryDecorator(),
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = { key ->
            when (key) {
                LandingScreen -> {
                    NavEntry(key = key) {
                        LandingScreenUi(
                            onStartClick = {
                                backstack.clear()
                                backstack.add(HomeScreen)
                            }
                        )
                    }
                }

                HomeScreen -> {
                    NavEntry(key = key) {
                        HomeScreenUi()
                    }
                }
                else -> throw IllegalArgumentException("Unknown screen: $key")
            }
        }
    )
}