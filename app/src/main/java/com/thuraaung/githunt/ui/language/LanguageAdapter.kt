package com.thuraaung.githunt.ui.language

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.thuraaung.githunt.R
import com.thuraaung.githunt.databinding.LayoutLanguageItemBinding
import com.thuraaung.githunt.model.ModelLanguage


class LanguageAdapter : RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {

    private var languageList = mutableListOf<ModelLanguage>()
    var languageClickListener : ((ModelLanguage) -> Unit)? = null

    inner class LanguageViewHolder(private val binding: LayoutLanguageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                languageClickListener?.invoke(languageList[adapterPosition])
            }
        }

        fun binding(language: ModelLanguage) {
            binding.language = language
            binding.executePendingBindings()
        }
    }

    fun updateItems(languages : List<ModelLanguage>) {
        languageList.clear()
        languageList.addAll(languages)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<LayoutLanguageItemBinding>(
            layoutInflater,
            R.layout.layout_language_item,
            parent,
            false
        )
        return LanguageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return languageList.size
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.binding(languageList[position])
    }
}

