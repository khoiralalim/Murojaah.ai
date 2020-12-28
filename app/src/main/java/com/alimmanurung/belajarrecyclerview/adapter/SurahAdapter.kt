package com.alimmanurung.belajarrecyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alimmanurung.belajarrecyclerview.R
import com.alimmanurung.belajarrecyclerview.databinding.ItemRowSurahBinding
import com.alimmanurung.belajarrecyclerview.model.SurahItems

class SurahAdapter : RecyclerView.Adapter<SurahAdapter.SurahViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    private val mData = ArrayList<SurahItems>()

    fun setData(items: ArrayList<SurahItems>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        position: Int
    ): SurahAdapter.SurahViewHolder {
        val binding = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_surah, viewGroup, false)
        return SurahViewHolder(binding)
    }

    override fun onBindViewHolder(surahViewHolder: SurahViewHolder, position: Int) {
        surahViewHolder.bind(mData[position])
    }

    override fun getItemCount(): Int  = mData.size

    inner class SurahViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRowSurahBinding.bind(itemView)
        fun bind(surahItems: SurahItems) {
            with(itemView) {
                binding.tvNumberSurah.text = surahItems.idSurah
                binding.tvSurahName.text = surahItems.nameSurah
                binding.tvJlhAyat.text = surahItems.jlhAyat

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(surahItems)}

            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(surahItems: SurahItems)
    }
}