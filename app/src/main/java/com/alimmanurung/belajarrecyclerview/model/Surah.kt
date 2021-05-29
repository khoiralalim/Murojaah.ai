package com.alimmanurung.belajarrecyclerview.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Surah(
    var idSurah: String,
    var nameSurah: String,
    var jlhAyat: String,
    var ayat1: String,
    var ayat2: String,
    var ayat3: String,
    var ayat4: String,
    var ayat5: String,
    var ayat6: String,
    var ayat7: String
) : Parcelable