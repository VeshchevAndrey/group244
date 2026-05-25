package com.example.application244

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

val Context.profileDataStore by preferencesDataStore(name = "user_profile")

class ProfileRepository(private val context: Context) {
    private val PROFILE_KEY = stringPreferencesKey(name = "profile")

    suspend fun saveProfileData(profile: UserProfile) {
        context.profileDataStore.edit { preferences ->
            val json = Json.encodeToString(value = profile)
            preferences[PROFILE_KEY] = json
        }
    }

    val getProfile = context.profileDataStore.data.map { preferences ->
        val json = preferences[PROFILE_KEY] ?: ""
        if (json.isBlank()) UserProfile()
        else try {
            Json.decodeFromString<UserProfile>(json)
        } catch (e: Exception) {
            UserProfile()
        }
    }
}