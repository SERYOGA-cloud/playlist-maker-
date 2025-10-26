package com.example.playlistmaker

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.materialswitch.MaterialSwitch

class SettingsActivity : AppCompatActivity() {

    private val prefs by lazy { getSharedPreferences(PREFS_NAME, MODE_PRIVATE) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_24)
            title = getString(R.string.settings_title)
        }
        toolbar.navigationIcon?.setTint(ContextCompat.getColor(this, R.color.text_primary_dark))
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        val switch = findViewById<MaterialSwitch>(R.id.switchTheme)
        switch.isChecked = prefs.getBoolean(KEY_DARK_THEME, false)
        switch.setOnCheckedChangeListener { _, checked ->
            AppCompatDelegate.setDefaultNightMode(
                if (checked) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
            prefs.edit().putBoolean(KEY_DARK_THEME, checked).apply()
        }

        val shareButton = findViewById<MaterialButton>(R.id.buttonShare)
        shareButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text))
            }
            startActivity(Intent.createChooser(intent, null))
        }

        val supportButton = findViewById<MaterialButton>(R.id.buttonSupport)
        supportButton.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_SENDTO,
                Uri.parse("mailto:${getString(R.string.support_email)}")
            )
            startActivity(intent)
        }

        val termsButton = findViewById<MaterialButton>(R.id.buttonTerms)
        termsButton.setOnClickListener {
            startActivity(Intent(this, TermsActivity::class.java))
        }
    }

    companion object {
        const val PREFS_NAME = "settings"
        const val KEY_DARK_THEME = "night"
    }

}
