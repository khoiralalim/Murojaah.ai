package com.alimmanurung.belajarrecyclerview.ui.alfatihah

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alimmanurung.belajarrecyclerview.databinding.ActivityAlfatihah1Binding
import com.alimmanurung.belajarrecyclerview.model.Surah
import java.util.*

class Alfatihah1Activity : AppCompatActivity() {
    private val RQ_SPEECH_REC = 102
    private var title = "Murojaah Practice"

    private lateinit var binding: ActivityAlfatihah1Binding

    companion object {
        const val EXTRA_AYAT = "extra_ayat"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlfatihah1Binding.inflate(layoutInflater)
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

            binding.tvTextSpeech.text = result?.get(0).toString() //tampilkan ucapan (speech to text)

            //Algo Rabin Karp
            val ucapan = result?.get(0).toString().toLowerCase().filter { it in 'a'..'z' } //preprocessing
            val Kgram = ucapan.windowed(size = 3, step = 1) //kgram(3)

            //membuat hashing list (data diambil dari looping)
            val hashingUcapanList = mutableListOf<Double>()
            val hashingPattern1List = mutableListOf<Double>()
            val hashingPattern2List = mutableListOf<Double>()
            val hashingPattern3List = mutableListOf<Double>()
            val hashingPattern4List = mutableListOf<Double>()
            val hashingPattern5List = mutableListOf<Double>()
            val hashingPattern6List = mutableListOf<Double>()
            val hashingPattern7List = mutableListOf<Double>()

            //hashing
            val jlhKgram = Kgram.size //menghitung jumlah kgram(3)
            for (i in 0 until jlhKgram) {
                val hashingUcapan = ((Kgram[i][0].toInt()*(Math.pow(7.0, 2.0))) + (Kgram[i][1].toInt() * (Math.pow(7.0, 1.0))) +
                        (Kgram[i][2].toInt() * (Math.pow(7.0, 0.0))) % 10007) //rumus hashing
                hashingUcapanList.add(hashingUcapan) //menambahkan nilai ke hashingUcapanList
            }

            val ayat = intent.getParcelableExtra<Surah>(EXTRA_AYAT) as Surah //mengambil data Surah (parcelable)dari main activity
            //Pattern Ayat1
            val patternAyat1 = ayat.ayat1.toLowerCase().filter { it in 'a'..'z' } //preprocessing ayat1 (pattern)
            val kgramPatternAyat1 = patternAyat1.windowed(size = 3, step = 1) //kgram(3) ayat1
            val jlhKgramPattern1 = kgramPatternAyat1.size //menghitung jumlah kgram(3) ayat1
            for (i in 0 until jlhKgramPattern1) {
                val hashingPattern1 = ((kgramPatternAyat1[i][0].toInt() * (Math.pow(7.0, 2.0))) + (kgramPatternAyat1[i][1].toInt() * (Math.pow(7.0, 1.0))) +
                        (kgramPatternAyat1[i][2].toInt() * (Math.pow(7.0, 0.0))) % 10007) //rumus hashing
                hashingPattern1List.add(hashingPattern1) //menambahkan nilai ke hashingPattern1List
            }
            //Pattern Ayat2
            val patternAyat2 = ayat.ayat2.toLowerCase().filter { it in 'a'..'z' } //preprocessing ayat2 (pattern)
            val kgramPatternAyat2 = patternAyat2.windowed(size = 3, step = 1) //kgram(3) ayat2
            val jlhKgramPattern2 = kgramPatternAyat2.size //menghitung jumlah kgram(3) ayat2
            for (i in 0 until jlhKgramPattern2) {
                val hashingPattern2 = ((kgramPatternAyat2[i][0].toInt() * (Math.pow(7.0, 2.0))) + (kgramPatternAyat2[i][1].toInt() * (Math.pow(7.0, 1.0))) +
                        (kgramPatternAyat2[i][2].toInt() * (Math.pow(7.0, 0.0))) % 10007) //rumus hashing
                hashingPattern2List.add(hashingPattern2) //menambahkan nilai ke hashingPattern2List
            }
            //Pattern Ayat3
            val patternAyat3 = ayat.ayat3.toLowerCase().filter { it in 'a'..'z' } //preprocessing ayat3 (pattern)
            val kgramPatternAyat3 = patternAyat3.windowed(size = 3, step = 1) //kgram(3) ayat3
            val jlhKgramPattern3 = kgramPatternAyat3.size //menghitung jumlah kgram(3) ayat3
            for (i in 0 until jlhKgramPattern3) {
                val hashingPattern3 = ((kgramPatternAyat3[i][0].toInt() * (Math.pow(7.0, 2.0))) + (kgramPatternAyat3[i][1].toInt() * (Math.pow(7.0, 1.0))) +
                        (kgramPatternAyat3[i][2].toInt() * (Math.pow(7.0, 0.0))) % 10007) //rumus hashing
                hashingPattern3List.add(hashingPattern3) //menambahkan nilai ke hashingPattern3List
            }
            //Pattern Ayat4
            val patternAyat4 = ayat.ayat4.toLowerCase().filter { it in 'a'..'z' } //preprocessing ayat4 (pattern)
            val kgramPatternAyat4 = patternAyat4.windowed(size = 3, step = 1) //kgram(3) ayat4
            val jlhKgramPattern4 = kgramPatternAyat4.size //menghitung jumlah kgram(3) ayat4
            for (i in 0 until jlhKgramPattern4) {
                val hashingPattern4 = ((kgramPatternAyat4[i][0].toInt() * (Math.pow(7.0, 2.0))) + (kgramPatternAyat4[i][1].toInt() * (Math.pow(7.0, 1.0))) +
                        (kgramPatternAyat4[i][2].toInt() * (Math.pow(7.0, 0.0))) % 10007) //rumus hashing
                hashingPattern4List.add(hashingPattern4) //menambahkan nilai ke hashingPattern4List
            }
            //Pattern Ayat5
            val patternAyat5 = ayat.ayat5.toLowerCase().filter { it in 'a'..'z' } //preprocessing ayat5 (pattern)
            val kgramPatternAyat5 = patternAyat5.windowed(size = 3, step = 1) //kgram(3) ayat5
            val jlhKgramPattern5 = kgramPatternAyat5.size //menghitung jumlah kgram(3) ayat5
            for (i in 0 until jlhKgramPattern5) {
                val hashingPattern5 = ((kgramPatternAyat5[i][0].toInt() * (Math.pow(7.0, 2.0))) + (kgramPatternAyat5[i][1].toInt() * (Math.pow(7.0, 1.0))) +
                        (kgramPatternAyat5[i][2].toInt() * (Math.pow(7.0, 0.0))) % 10007) //rumus hashing
                hashingPattern5List.add(hashingPattern5) //menambahkan nilai ke hashingPattern5List
            }
            //Pattern Ayat6
            val patternAyat6 = ayat.ayat6.toLowerCase().filter { it in 'a'..'z' } //preprocessing ayat6 (pattern)
            val kgramPatternAyat6 = patternAyat6.windowed(size = 3, step = 1) //kgram(3) ayat6
            val jlhKgramPattern6 = kgramPatternAyat6.size //menghitung jumlah kgram(3) ayat6
            for (i in 0 until jlhKgramPattern6) {
                val hashingPattern6 = ((kgramPatternAyat6[i][0].toInt() * (Math.pow(7.0, 2.0))) + (kgramPatternAyat6[i][1].toInt() * (Math.pow(7.0, 1.0))) +
                        (kgramPatternAyat6[i][2].toInt() * (Math.pow(7.0, 0.0))) % 10007) //rumus hashing
                hashingPattern6List.add(hashingPattern6) //menambahkan nilai ke hashingPattern6List
            }
            //Pattern Ayat7
            val patternAyat7 = ayat.ayat7.toLowerCase().filter { it in 'a'..'z' } //preprocessing ayat7 (pattern)
            val kgramPatternAyat7 = patternAyat7.windowed(size = 3, step = 1) //kgram(3) ayat7
            val jlhKgramPattern7 = kgramPatternAyat7.size //menghitung jumlah kgram(3) ayat7
            for (i in 0 until jlhKgramPattern7) {
                val hashingPattern7 = ((kgramPatternAyat7[i][0].toInt() * (Math.pow(7.0, 2.0))) + (kgramPatternAyat7[i][1].toInt() * (Math.pow(7.0, 1.0))) +
                        (kgramPatternAyat7[i][2].toInt() * (Math.pow(7.0, 0.0))) % 10007) //rumus hashing
                hashingPattern7List.add(hashingPattern7) //menambahkan nilai ke hashingPattern7List
            }


            if (ucapan == patternAyat1) {
                var countSameHashing = 0
                for (hashUcapan in hashingUcapanList) {
                    for (hashPattern1 in hashingPattern1List) {
                        if (hashUcapan == hashPattern1) {
                            binding.tvTextSpeech.text =
                                "Benar\n" + "Q.S. " + ayat.nameSurah + " Ayat ke-1"
                            binding.btnNext.visibility = View.VISIBLE
                            binding.btnNext.setOnClickListener {
                                binding.tvTextSpeech.text = ""
                                binding.tvSimilarity.text = ""
                            }
                            countSameHashing++
                            break
                        }
                    }
                }
                val similarity = (2.0 * countSameHashing) / (hashingUcapanList.size + hashingPattern1List.size) * 100
                binding.tvSimilarity.text = similarity.toFloat().toString() + "%"
            } else if (ucapan == patternAyat2) {
                var countSameHashing = 0
                for (hashUcapan in hashingUcapanList) {
                    for (hashPattern2 in hashingPattern2List) {
                        if (hashUcapan == hashPattern2) {
                            binding.tvTextSpeech.text =
                                "Benar\n" + "Q.S. " + ayat.nameSurah + " Ayat ke-2"
                            binding.btnNext.visibility = View.VISIBLE
                            binding.btnNext.setOnClickListener {
                                binding.tvTextSpeech.text = ""
                                binding.tvSimilarity.text = ""
                            }
                            countSameHashing++
                            break
                        }
                    }
                }
                val similarity = (2.0 * countSameHashing) / (hashingUcapanList.size + hashingPattern2List.size) * 100
                binding.tvSimilarity.text = similarity.toFloat().toString() + "%"
            } else if (ucapan == patternAyat3) {
                var countSameHashing = 0
                for (hashUcapan in hashingUcapanList) {
                    for (hashPattern3 in hashingPattern3List) {
                        if (hashUcapan == hashPattern3) {
                            binding.tvTextSpeech.text =
                                "Benar\n" + "Q.S. " + ayat.nameSurah + " Ayat ke-3"
                            binding.btnNext.visibility = View.VISIBLE
                            binding.btnNext.setOnClickListener {
                                binding.tvTextSpeech.text = ""
                                binding.tvSimilarity.text = ""
                            }
                            countSameHashing++
                            break
                        }
                    }
                }
                val similarity = (2.0 * countSameHashing) / (hashingUcapanList.size + hashingPattern3List.size) * 100
                binding.tvSimilarity.text = similarity.toFloat().toString() + "%"
            } else if (ucapan == patternAyat4) {
                var countSameHashing = 0
                for (hashUcapan in hashingUcapanList) {
                    for (hashPattern4 in hashingPattern4List) {
                        if (hashUcapan == hashPattern4) {
                            binding.tvTextSpeech.text =
                                "Benar\n" + "Q.S. " + ayat.nameSurah + " Ayat ke-4"
                            binding.btnNext.visibility = View.VISIBLE
                            binding.btnNext.setOnClickListener {
                                binding.tvTextSpeech.text = ""
                                binding.tvSimilarity.text = ""
                            }
                            countSameHashing++
                            break
                        }
                    }
                }
                val similarity = (2.0 * countSameHashing) / (hashingUcapanList.size + hashingPattern4List.size) * 100
                binding.tvSimilarity.text = similarity.toFloat().toString() + "%"
            } else if (ucapan == patternAyat5) {
                var countSameHashing = 0
                for (hashUcapan in hashingUcapanList) {
                    for (hashPattern5 in hashingPattern5List) {
                        if (hashUcapan == hashPattern5) {
                            binding.tvTextSpeech.text =
                                "Benar\n" + "Q.S. " + ayat.nameSurah + " Ayat ke-5"
                            binding.btnNext.visibility = View.VISIBLE
                            binding.btnNext.setOnClickListener {
                                binding.tvTextSpeech.text = ""
                                binding.tvSimilarity.text = ""
                            }
                            countSameHashing++
                            break
                        }
                    }
                }
                val similarity = (2.0 * countSameHashing) / (hashingUcapanList.size + hashingPattern5List.size) * 100
                binding.tvSimilarity.text = similarity.toFloat().toString() + "%"
            } else if (ucapan == patternAyat6) {
                var countSameHashing = 0
                for (hashUcapan in hashingUcapanList) {
                    for (hashPattern6 in hashingPattern6List) {
                        if (hashUcapan == hashPattern6) {
                            binding.tvTextSpeech.text =
                                "Benar\n" + "Q.S. " + ayat.nameSurah + " Ayat ke-6"
                            binding.btnNext.visibility = View.VISIBLE
                            binding.btnNext.setOnClickListener {
                                binding.tvTextSpeech.text = ""
                                binding.tvSimilarity.text = ""
                            }
                            countSameHashing++
                            break
                        }
                    }
                }
                val similarity = (2.0 * countSameHashing) / (hashingUcapanList.size + hashingPattern6List.size) * 100
                binding.tvSimilarity.text = similarity.toFloat().toString() + "%"
            } else if (ucapan == patternAyat7) {
                var countSameHashing = 0
                for (hashUcapan in hashingUcapanList) {
                    for (hashPattern7 in hashingPattern7List) {
                        if (hashUcapan == hashPattern7) {
                            binding.tvTextSpeech.text =
                                "Benar\n" + "Q.S. " + ayat.nameSurah + " Ayat ke-7"
                            binding.btnNext.visibility = View.VISIBLE
                            binding.btnNext.setOnClickListener {
                                binding.tvTextSpeech.text = ""
                                binding.tvSimilarity.text = ""
                            }
                            countSameHashing++
                            break
                        }
                    }
                }
                val similarity = (2.0 * countSameHashing) / (hashingUcapanList.size + hashingPattern7List.size) * 100
                binding.tvSimilarity.text = similarity.toFloat().toString() + "%"
            } else {
                var countSameHashing = 0
                for (hashUcapan in hashingUcapanList) {
                    for (hashPattern1 in hashingPattern1List) {
                        if (hashUcapan == hashPattern1) {
                            countSameHashing++
                            break
                        }
                    }
                }
                val similarity = (2.0 * countSameHashing) / (hashingUcapanList.size + hashingPattern1List.size) * 100
                binding.tvSimilarity.text = similarity.toFloat().toString() + "%"

                binding.tvTextSpeech.text = "Anda Mengucapkan " + ucapan + " \n" +
                        "belum Tepat, Silahkan Dicoba Lagi yaa\n"
//                         + patternAyat1 + " " + patternAyat2 + " " + patternAyat3 + " " + patternAyat4 + " " + patternAyat5 + " " + patternAyat6 + " " + patternAyat7 + " "
                binding.btnNext.visibility = View.INVISIBLE
            }

//
//            if (ucapan == ayat.ayat1) {
////                binding.tvTextSpeech.text = "Benar\n" + "Q.S. " + ayat.nameSurah + " Ayat ke-1" +
////                        "\nkgram = " + Kgram
////                binding.btnNext.visibility = View.VISIBLE
////                binding.btnNext.setOnClickListener {
////                    binding.tvTextSpeech.text = ""
////                    }
//            } else if (ucapan == ayat.ayat2) {
//                binding.tvTextSpeech.text = "Benar\n" + "Q.S. " + ayat.nameSurah + " Ayat ke-2" +
//                        "\nkgram = " + Kgram
//                binding.btnNext.visibility = View.VISIBLE
//                binding.btnNext.setOnClickListener {
//                    binding.tvTextSpeech.text = ""
//                }
//            } else if (ucapan == ayat.ayat3) {
//                binding.tvTextSpeech.text = "Benar\n" + "Q.S. " + ayat.nameSurah + " Ayat ke-3" +
//                        "\nkgram = " + Kgram
//                binding.btnNext.visibility = View.VISIBLE
//                binding.btnNext.setOnClickListener {
//                    binding.tvTextSpeech.text = ""
//                }
//            } else if (ucapan == ayat.ayat4) {
//                binding.tvTextSpeech.text = "Benar\n" + "Q.S. " + ayat.nameSurah + " Ayat ke-4" +
//                        "\nkgram = " + Kgram
//                binding.btnNext.visibility = View.VISIBLE
//                binding.btnNext.setOnClickListener {
//                    binding.tvTextSpeech.text = ""
//                }
//            } else if (ucapan == ayat.ayat5) {
//                binding.tvTextSpeech.text = "Benar\n" + "Q.S. " + ayat.nameSurah + " Ayat ke-5" +
//                        "\nkgram = " + Kgram
//                binding.btnNext.visibility = View.VISIBLE
//                binding.btnNext.setOnClickListener {
//                    binding.tvTextSpeech.text = ""
//                }
//            } else if (ucapan == ayat.ayat6) {
//                binding.tvTextSpeech.text = "Benar\n" + "Q.S. " + ayat.nameSurah + " Ayat ke-6" +
//                        "\nkgram = " + Kgram
//                binding.btnNext.visibility = View.VISIBLE
//                binding.btnNext.setOnClickListener {
//                    binding.tvTextSpeech.text = ""
//                }
//            } else if (ucapan == ayat.ayat7) {
//                binding.tvTextSpeech.text = "Benar\n" + "Q.S. " + ayat.nameSurah + " Ayat ke-7" +
//                        "\nkgram = " + Kgram
//                binding.btnNext.visibility = View.VISIBLE
//                binding.btnNext.setOnClickListener {
//                    binding.tvTextSpeech.text = ""
//                }
//            } else {
//                binding.tvTextSpeech.text = "Anda Mengucapkan " + ucapan + " \n" +
//                        "belum Tepat, Silahkan Dicoba Lagi yaa\n"
//
////                        + ayat.ayat1 + " " + ayat.ayat2 + " " + ayat.ayat3 + " " + ayat.ayat4 + " " + ayat.ayat5 + " " + ayat.ayat6 + " " + ayat.ayat7 + " "
//                binding.btnNext.visibility = View.INVISIBLE
//            }
        }

//                            val nextAyatIntent = Intent(this@Alfatihah1Activity, Alfatihah2Activity::class.java)
//                            nextAyatIntent.putExtra(Alfatihah2Activity.EXTRA_AYAT, surah)
//                            startActivity(nextAyatIntent)
//                    }

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