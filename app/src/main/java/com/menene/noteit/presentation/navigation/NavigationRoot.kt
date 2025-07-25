package com.menene.noteit.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.menene.noteit.presentation.AddNoteScreen
import com.menene.noteit.presentation.HomeScreenUi
import com.menene.noteit.presentation.LandingScreenUi
import com.menene.noteit.presentation.NoteViewModel
import com.menene.noteit.util.DataStoreManager
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun NavigationRoot(
    dataStoreManager: DataStoreManager = koinInject(),
    noteViewModel: NoteViewModel = koinInject()
) {
    val hasSeen by dataStoreManager.hasSeen.collectAsState(true)
    val scope = rememberCoroutineScope()

    val backstack = rememberNavBackStack(
        if (hasSeen) HomeScreen else LandingScreen
    )

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
                                scope.launch {
                                    dataStoreManager.setHasSeenLandingScreen(true)
                                }
                                backstack.clear()
                                backstack.add(HomeScreen)
                            }
                        )
                    }
                }

                HomeScreen -> {
                    NavEntry(key = key) {
                        HomeScreenUi(
                            noteViewModel = noteViewModel,
                            onAddNoteClicked = {
                                backstack.add(AddNoteScreen)
                            }
                        )
                    }
                }

                AddNoteScreen -> {
                    NavEntry(key = key) {
                        AddNoteScreen(
                            noteViewModel = noteViewModel,
                            onNoteAdded = {
                                backstack.removeAt(backstack.size - 1)
                            }
                        )
                    }
                }

                else -> throw IllegalArgumentException("Unknown screen: $key")
            }
        }
    )
}