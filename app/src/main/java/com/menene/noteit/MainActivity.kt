package com.menene.noteit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.menene.noteit.navigation.NavigationRoot
import com.menene.noteit.presentation.ui.theme.NoteItTheme
import com.menene.noteit.util.DataStoreManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val splashScreen = installSplashScreen()
        var keepSplashScreen = true

        splashScreen.setKeepOnScreenCondition { keepSplashScreen }

        setContent {
            NoteItTheme {
                val dataStoreManager: DataStoreManager = koinInject()
                val hasSeenLanding = dataStoreManager.hasSeen.collectAsStateWithLifecycle(null)
                LaunchedEffect(hasSeenLanding) {
                    keepSplashScreen = false
                }

                if (hasSeenLanding.value != null) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                        ) {
                            NavigationRoot(hasSeenLanding.value)
                        }
                    }
                }
            }
        }
    }
}
