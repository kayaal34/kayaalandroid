package com.example.kayaalandroid.ui.settings

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// ✅ DataStore'yi Context'e bağla
val Context.dataStore by preferencesDataStore(name = "filter_preferences")

class FilterPreferences(private val context: Context) {

    // ✅ Anahtar tanımları
    companion object {
        val GENRE_KEY = stringPreferencesKey("genre")
        val MIN_RATING_KEY = intPreferencesKey("min_rating")
        val SEARCH_TEXT_KEY = stringPreferencesKey("search_text")
    }

    // ✅ Filtreleri kaydet
    suspend fun saveFilters(genre: String, minRating: Int, searchText: String) {
        context.dataStore.edit { preferences ->
            preferences[GENRE_KEY] = genre
            preferences[MIN_RATING_KEY] = minRating
            preferences[SEARCH_TEXT_KEY] = searchText
        }
    }

    // ✅ Filtreleri oku
    val filtersFlow: Flow<FilterState> = context.dataStore.data.map { preferences ->
        val genre = preferences[GENRE_KEY] ?: ""
        val minRating = preferences[MIN_RATING_KEY] ?: 0
        val searchText = preferences[SEARCH_TEXT_KEY] ?: ""
        FilterState(genre, minRating, searchText)
    }
}

// ✅ Durumu temsil eden veri sınıfı
data class FilterState(
    val genre: String,
    val minRating: Int,
    val searchText: String
)
