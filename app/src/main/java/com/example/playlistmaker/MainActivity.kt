package com.example.playlistmaker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchButton: View = findViewById(R.id.buttonSearch)
        val libraryButton: View = findViewById(R.id.buttonLibrary)
        val settingsButton: View = findViewById(R.id.buttonSettings)

        searchButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.btn_search),
                    Toast.LENGTH_SHORT
                ).show()

                startActivity(Intent(this@MainActivity, SearchActivity::class.java))
            }
        })

        libraryButton.setOnClickListener {
            Toast.makeText(
                this@MainActivity,
                getString(R.string.btn_library),
                Toast.LENGTH_SHORT
            ).show()

            startActivity(Intent(this@MainActivity, LibraryActivity::class.java))
        }

        settingsButton.setOnClickListener(this::openSettings)
    }

    private fun openSettings(@Suppress("UNUSED_PARAMETER") view: View) {
        Toast.makeText(
            this,
            getString(R.string.btn_settings),
            Toast.LENGTH_SHORT
        ).show()

        startActivity(Intent(this, SettingsActivity::class.java))
    }
}
