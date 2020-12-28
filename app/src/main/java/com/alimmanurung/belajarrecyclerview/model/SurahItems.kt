package com.alimmanurung.belajarrecyclerview.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class SurahItems (
//    var id: Int = 0
    var idSurah: String? = null,
    var nameSurah: String? = null,
    var jlhAyat: String? = null
) : Parcelable