package com.alimmanurung.belajarrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alimmanurung.belajarrecyclerview.ViewModel.MainViewModel
import com.alimmanurung.belajarrecyclerview.adapter.SurahAdapter
import com.alimmanurung.belajarrecyclerview.databinding.ActivityMainBinding
import com.alimmanurung.belajarrecyclerview.model.SurahItems

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: SurahAdapter
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private var title = "Pilihan Surah"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = SurahAdapter()
        adapter.notifyDataSetChanged()

        binding.rvSurah.layoutManager = LinearLayoutManager(this)
        binding.rvSurah.adapter = adapter

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        setActionBarTitle(title)
        showLoading(true)
        mainViewModel.setListSurah()


        mainViewModel.getSurahs().observe(this, { surahItems ->
            if (surahItems != null) {
                adapter.setData(surahItems)
                showLoading(false)

                adapter.setOnItemClickCallback(object : SurahAdapter.OnItemClickCallback{
                    override fun onItemClicked(surahItems: SurahItems) {
                        showSelectedSurah(surahItems)
                    }
                })
            }

        })

    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }

    private fun showSelectedSurah(surahItems: SurahItems) {
        Toast.makeText(this, "Kamu memilih ${surahItems.nameSurah}", Toast.LENGTH_SHORT).show()
        val googleSpeechIntent = Intent(this@MainActivity, GoogleSpeechActivity::class.java)
//        googleSpeechIntent.putExtra(googleSpeechIntent.EXTRA_AYAT, surah)
        startActivity(googleSpeechIntent)
    }

}
