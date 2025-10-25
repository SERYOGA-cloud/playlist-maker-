package com.example.playlistmaker

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.switchmaterial.SwitchMaterial

class SettingsActivity : AppCompatActivity() {

    private val prefs by lazy { getSharedPreferences(PREFS_NAME, MODE_PRIVATE) }
    private var isShareActive = false
    private var isSupportActive = false
    private var isTermsActive = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.settings_title)
        }
        if (toolbar.navigationIcon == null) {
            toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_24)
        }
        toolbar.navigationIcon?.setTint(getColorCompat(R.color.icon_active))
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        val switch = findViewById<SwitchMaterial>(R.id.switchTheme)
        switch.isChecked = prefs.getBoolean(KEY_DARK_THEME, false)
        switch.setOnCheckedChangeListener { _, checked ->
            AppCompatDelegate.setDefaultNightMode(
                if (checked) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
            prefs.edit().putBoolean(KEY_DARK_THEME, checked).apply()
        }

        val shareRow = findViewById<View>(R.id.rowShare)
        val shareIcon = findViewById<ImageView>(R.id.iconShare)
        shareIcon.setColorFilter(getColorCompat(R.color.icon_inactive))
        shareRow.setOnClickListener {
            isShareActive = !isShareActive
            shareIcon.setColorFilter(
                getColorCompat(if (isShareActive) R.color.icon_active else R.color.icon_inactive)
            )
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text))
            }
            startActivity(Intent.createChooser(intent, null))
        }

        val supportRow = findViewById<View>(R.id.rowSupport)
        val supportIcon = findViewById<ImageView>(R.id.iconSupport)
        supportIcon.setColorFilter(getColorCompat(R.color.icon_inactive))
        supportRow.setOnClickListener {
            isSupportActive = !isSupportActive
            supportIcon.setColorFilter(
                getColorCompat(if (isSupportActive) R.color.icon_active else R.color.icon_inactive)
            )
            val intent = Intent(
                Intent.ACTION_SENDTO,
                Uri.parse("mailto:${getString(R.string.support_email)}")
            )
            startActivity(intent)
        }

        val termsRow = findViewById<View>(R.id.rowTerms)
        val termsIcon = findViewById<ImageView>(R.id.iconTerms)
        termsIcon.setColorFilter(getColorCompat(R.color.icon_inactive))
        termsRow.setOnClickListener {
            isTermsActive = !isTermsActive
            termsIcon.setColorFilter(
                getColorCompat(if (isTermsActive) R.color.icon_active else R.color.icon_inactive)
            )
            startActivity(Intent(this, TermsActivity::class.java))
        }
    }

    companion object {
        const val PREFS_NAME = "settings"
        const val KEY_DARK_THEME = "night"
    }

    private fun getColorCompat(colorRes: Int): Int {
        return ContextCompat.getColor(this, colorRes)
    }
}
