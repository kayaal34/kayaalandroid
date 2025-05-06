package com.example.kayaalandroid.datastore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_profile")

class ProfilePreferences(private val context: Context) {

    companion object {
        val NAME = stringPreferencesKey("name")
        val RESUME_URL = stringPreferencesKey("resume_url")
        val AVATAR_URI = stringPreferencesKey("avatar_uri")
        val JOB_TITLE = stringPreferencesKey("job_title")
    }

    suspend fun saveProfile(name: String, resumeUrl: String, avatarUri: String) {
        context.dataStore.edit { preferences ->
            preferences[NAME] = name
            preferences[RESUME_URL] = resumeUrl
            preferences[AVATAR_URI] = avatarUri
            preferences[JOB_TITLE] ?: ""

        }
    }

    val profileFlow: Flow<ProfileData> = context.dataStore.data.map { preferences ->
        ProfileData(
            name = preferences[NAME] ?: "",
            resumeUrl = preferences[RESUME_URL] ?: "",
            avatarUri = preferences[AVATAR_URI] ?: "",
            jobTitle = preferences[JOB_TITLE] ?: ""
        )
    }
}
data class ProfileData(
    val name: String,
    val resumeUrl: String,
    val avatarUri: String,
    val jobTitle: String
)