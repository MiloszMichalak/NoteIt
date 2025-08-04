package com.menene.noteit.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
    val hasSeen by dataStoreManager.hasSeen.collectAsStateWithLifecycle(null)
    val scope = rememberCoroutineScope()

    if (hasSeen == null) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }


    val backstack = rememberNavBackStack(
        if (hasSeen == true) HomeScreen else LandingScreen
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