package com.alimmanurung.belajarrecyclerview.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.alimmanurung.belajarrecyclerview.R
import com.alimmanurung.belajarrecyclerview.adapter.ListSurahAdapter
import com.alimmanurung.belajarrecyclerview.databinding.ActivityMainBinding
import com.alimmanurung.belajarrecyclerview.model.Surah
import com.alimmanurung.belajarrecyclerview.ui.alfatihah.Alfatihah1Activity

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

        //menjaga data saat terjadi perubahan configurasi layar
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

                setActionBarTitle(title)
                if (stateList != null) {
                    list.addAll(stateList)
                    showRecyclerList()
                }
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

        //ini
        val dataAyat1 = resources.getStringArray(R.array.data_ayat1)
        val dataAyat2 = resources.getStringArray(R.array.data_ayat2)
        val dataAyat3 = resources.getStringArray(R.array.data_ayat3)
        val dataAyat4 = resources.getStringArray(R.array.data_ayat4)
        val dataAyat5 = resources.getStringArray(R.array.data_ayat5)
        val dataAyat6 = resources.getStringArray(R.array.data_ayat6)
        val dataAyat7 = resources.getStringArray(R.array.data_ayat7)

        val listSurah = ArrayList<Surah>()
        for (position in dataSurahName.indices) {
            val surah = Surah(
                dataSurahNumber[position],
                dataSurahName[position],
                dataJlhAyat[position],

                    //ini
                    dataAyat1[position],
                    dataAyat2[position],
                    dataAyat3[position],
                    dataAyat4[position],
                    dataAyat5[position],
                    dataAyat6[position],
                    dataAyat7[position]
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
        val alFatihah1Intent = Intent(this@MainActivity, Alfatihah1Activity::class.java)
        alFatihah1Intent.putExtra(Alfatihah1Activity.EXTRA_AYAT, surah)
        startActivity(alFatihah1Intent)
    }
}