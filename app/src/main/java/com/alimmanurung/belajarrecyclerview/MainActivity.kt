package com.alimmanurung.belajarrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.alimmanurung.belajarrecyclerview.adapter.ListSurahAdapter
import com.alimmanurung.belajarrecyclerview.databinding.ActivityMainBinding
import com.alimmanurung.belajarrecyclerview.model.Surah

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var title = "Pilihan Surah"
    private val list = ArrayList<Surah>()

    companion object {
        private const val STATE_TITLE = "state_string"
        private const val STATE_LIST = "state_list"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvSurah.setHasFixedSize(true)

        when (savedInstanceState) {
            null -> {
                /*
            Pada saat pertama kali activity dijalankan,
            Ambil data dari method getListSurahs, kemudian tampilkan recyclerviewlist
             */
                setActionBarTitle(title)
                list.addAll(getListSurahs())
                showRecyclerList()

            }
            else -> {
                /*
            Jika terjadi config changes maka ambil data yang dikirimkan dari saveinstancestate
             */
                title = savedInstanceState.getString(STATE_TITLE).toString()
                val stateList = savedInstanceState.getParcelableArrayList<Surah>(STATE_LIST)
            }
        }
    }

    private fun showRecyclerList() {
        binding.rvSurah.layoutManager = LinearLayoutManager(this)
        val listSurahAdapter = ListSurahAdapter(list)
        binding.rvSurah.adapter = listSurahAdapter

        listSurahAdapter.setOnItemClickCallback(object : ListSurahAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Surah) {
                showSelectedSurah(data)
            }
        })
    }

    fun getListSurahs(): ArrayList<Surah> {
        val dataSurahNumber = resources.getStringArray(R.array.data_surahNumber)
        val dataSurahName = resources.getStringArray(R.array.data_surahName)
        val dataJlhAyat = resources.getStringArray(R.array.data_jlhAyat)

        val listSurah = ArrayList<Surah>()
        for (position in dataSurahName.indices) {
            val surah = Surah(
                dataSurahNumber[position],
                dataSurahName[position],
                dataJlhAyat[position]
            )
            listSurah.add(surah)
        }
        return listSurah
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_TITLE, title)
        outState.putParcelableArrayList(STATE_LIST, list)
    }


    private fun setActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }

    private fun showSelectedSurah(surah: Surah) {
        Toast.makeText(this, "Kamu memilih ${surah.nameSurah}", Toast.LENGTH_SHORT).show()
        val googleSpeechIntent = Intent(this@MainActivity, GoogleSpeechActivity::class.java)
//        googleSpeechIntent.putExtra(googleSpeechIntent.EXTRA_AYAT, surah)
        startActivity(googleSpeechIntent)
    }
}
