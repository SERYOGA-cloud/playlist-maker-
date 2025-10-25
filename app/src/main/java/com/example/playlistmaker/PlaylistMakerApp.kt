package com.example.playlistmaker

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class PlaylistMakerApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val preferences = getSharedPreferences(SettingsActivity.PREFS_NAME, MODE_PRIVATE)
        val isDark = preferences.getBoolean(SettingsActivity.KEY_DARK_THEME, false)
        AppCompatDelegate.setDefaultNightMode(
            if (isDark) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )
    }
}
