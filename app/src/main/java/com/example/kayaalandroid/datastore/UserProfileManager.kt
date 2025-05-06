package com.example.kayaalandroid.datastore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.kayaalandroid.models.UserProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_profile")

class UserProfileManager(private val context: Context) {

    private val fullNameKey = stringPreferencesKey("full_name")
    private val resumeUrlKey = stringPreferencesKey("resume_url")
    private val avatarUriKey = stringPreferencesKey("avatar_uri")

    val userProfileFlow: Flow<UserProfile> = context.dataStore.data.map { prefs ->
        UserProfile(
            fullName = prefs[fullNameKey] ?: "",
            resumeUrl = prefs[resumeUrlKey] ?: "",
            avatarUri = prefs[avatarUriKey] ?: ""
        )
    }

    suspend fun saveProfile(profile: UserProfile) {
        context.dataStore.edit { prefs ->
            prefs[fullNameKey] = profile.fullName
            prefs[resumeUrlKey] = profile.resumeUrl
            prefs[avatarUriKey] = profile.avatarUri
        }
    }
}
