package com.alimmanurung.belajarrecyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alimmanurung.belajarrecyclerview.databinding.ItemRowSurahBinding
import com.alimmanurung.belajarrecyclerview.model.Surah

class ListSurahAdapter(private val listSurah: ArrayList<Surah>) : RecyclerView.Adapter<ListSurahAdapter.ListViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowSurahBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listSurah[position])
    }

    override fun getItemCount(): Int = listSurah.size

    inner class ListViewHolder(private val binding: ItemRowSurahBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(surah: Surah) {
            with(binding) {
                tvNumberSurah.text = surah.idSurah
                tvSurahName.text = surah.nameSurah
                tvJlhAyat.text = surah.jlhAyat

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(surah)}
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(surah: Surah)
    }
}