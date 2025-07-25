package com.menene.noteit.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object LandingScreen : NavKey

@Serializable
data object HomeScreen : NavKey

@Serializable
data object AddNoteScreen : NavKey