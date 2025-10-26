package com.example.playlistmaker

import android.content.Intent
import android.os.Bundle
import android.view.View
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
                startActivity(Intent(this@MainActivity, SearchActivity::class.java))
            }
        })

        libraryButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, LibraryActivity::class.java))
        }

        settingsButton.setOnClickListener(this::openSettings)
    }

    private fun openSettings(view: View) {
        startActivity(Intent(this, SettingsActivity::class.java))
    }
}
