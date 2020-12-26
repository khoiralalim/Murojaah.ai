package com.alimmanurung.belajarrecyclerview.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Surah(
    var idSurah: String,
    var nameSurah: String,
    var jlhAyat: String
) : Parcelable
