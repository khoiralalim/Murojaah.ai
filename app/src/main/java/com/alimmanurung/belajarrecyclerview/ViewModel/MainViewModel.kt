package com.alimmanurung.belajarrecyclerview.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alimmanurung.belajarrecyclerview.MainActivity
import com.alimmanurung.belajarrecyclerview.model.SurahItems
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception

class MainViewModel : ViewModel() {

    private val listSurahs = MutableLiveData<ArrayList<SurahItems>>()

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    fun setListSurah() {
        val client = AsyncHttpClient()
        val url = "https://api.quran.sutanlab.id/surah"

        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                //parsing json
                val listItems = ArrayList<SurahItems>()

                val result = String(responseBody)
                Log.d(TAG, result)
                try {
                    val responseObject = JSONObject(result)
                    val data = responseObject.getJSONArray("data")

                    for (i in 0 until data.length()) {
                        val surah = data.getJSONObject(i)
                        val surahItems = SurahItems()

                        surahItems.idSurah = surah.getString("number")
                        val jumlahAyat = surah.getString("numberOfVerses")
                        surahItems.jlhAyat = "$jumlahAyat Ayat"

                        val nameObject = surah.getJSONObject("name")
                        val transliterationObject = nameObject.getJSONObject("transliteration")
                        surahItems.nameSurah = transliterationObject.getString("id")

                        listItems.add(surahItems)
                    }

                    //set data ke adapter
                    listSurahs.postValue(listItems)
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }

            }
            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                Log.d("onFailure", error.message.toString())
                // Jika koneksi gagal
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
//                Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })

    }

    fun getSurahs(): LiveData<ArrayList<SurahItems>> {
        return listSurahs
    }
}