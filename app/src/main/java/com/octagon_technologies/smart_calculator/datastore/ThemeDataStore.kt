package com.octagon_technologies.smart_calculator.datastore

import android.content.Context
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import com.octagon_technologies.smart_calculator.model.Theme
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ThemeDataStore @Inject constructor(@ApplicationContext var context: Context) {
    private val dataStore by lazy { context.createDataStore("settings") }
    private val themeKey = preferencesKey<Int>("theme")

    suspend fun saveTheme(theme: Theme) = withContext(Dispatchers.IO) {
        dataStore.edit { pref ->
            pref[themeKey] = convertThemeToKey(theme)
        }
    }

    fun fetchTheme() = dataStore.data
        .map { pref ->
            val theme = pref[themeKey]
            convertKeyToTheme(theme)
        }
        .catch { emit(Theme.Light) }


    fun convertThemeToKey(theme: Theme?) = if (theme == Theme.Dark) 1 else 0
    fun convertKeyToTheme(key: Int?) = if (key == 1) Theme.Dark else Theme.Light
}