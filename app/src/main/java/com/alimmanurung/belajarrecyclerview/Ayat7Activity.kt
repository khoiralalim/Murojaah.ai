package com.alimmanurung.belajarrecyclerview

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alimmanurung.belajarrecyclerview.databinding.ActivityAyat7Binding
import java.util.*

class Ayat7Activity : AppCompatActivity() {
    private val RQ_SPEECH_REC = 102
    private var title = "Murojaah Practice Ayat 7"

    private lateinit var binding: ActivityAyat7Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAyat7Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setActionBarTitle(title)

        binding.btnSpeechToText.setOnClickListener {
            askSpeechInput()
        }
    }

    @SuppressLint("DefaultLocale")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RQ_SPEECH_REC && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val hasil = result?.get(0).toString().toLowerCase()
            binding.tvTextSpeech.text = result?.get(0).toString()
            if (hasil == "sirotol ladzina an'amta 'alaihim khairil magdubi 'alaihim waladholin"){
                binding.tvTextSpeech.text = "Benar"
            }
            else {
                binding.tvTextSpeech.text = "Belum Tepat, Silahkan Dicoba Lagi"
            }
        }
    }

    private fun askSpeechInput() {
        if (!SpeechRecognizer.isRecognitionAvailable(this)) {
            Toast.makeText(this, "Speech recognition is not available", Toast.LENGTH_SHORT).show()
        } else {
            val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something!")
            startActivityForResult(i, RQ_SPEECH_REC)
        }
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