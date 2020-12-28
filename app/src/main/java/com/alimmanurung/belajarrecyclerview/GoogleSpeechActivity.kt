package com.alimmanurung.belajarrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class GoogleSpeechActivity : AppCompatActivity() {
    private var title = "Murojaah Practice"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_speech)

        setActionBarTitle(title)
    }

    private fun setActionBarTitle(title: String?) {
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}