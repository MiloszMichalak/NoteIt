package com.menene.noteit.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(
    private val context: Context
) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
        private val HASSEEN = booleanPreferencesKey("hasSeenLandingScreen")
    }

    val hasSeen: Flow<Boolean> =
        context.dataStore.data.map { preferences ->
            preferences[HASSEEN] ?: false
        }


    suspend fun setHasSeenLandingScreen(hasSeen: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[HASSEEN] = hasSeen
        }
    }
}
