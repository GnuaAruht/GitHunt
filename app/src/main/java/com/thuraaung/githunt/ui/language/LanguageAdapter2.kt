package com.thuraaung.githunt.ui.language

import android.view.LayoutInflater
import android.view.ViewGroup
import com.thuraaung.githunt.R
import com.thuraaung.githunt.base.BaseAdapter
import com.thuraaung.githunt.base.DiffUtilCallback
import com.thuraaung.githunt.model.ModelLanguage
import com.thuraaung.githunt.utils.listen


class LanguageAdapter2(private val itemClickCallback : (item : ModelLanguage) -> Unit) : BaseAdapter<ModelLanguage,LanguageViewHolder>() {

    override val diffUtilCallback: DiffUtilCallback<ModelLanguage>
        get() = languageDiffUtil2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_language_item,parent,false)
        return LanguageViewHolder(view).listen { position ->
            itemClickCallback.invoke(itemList[position])
        }
    }
}


val languageDiffUtil2 = object : DiffUtilCallback<ModelLanguage>() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItemList[oldItemPosition].urlParam == newItemList[newItemPosition].urlParam

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItemList[oldItemPosition]
        val newItem = newItemList[newItemPosition]
        return oldItem.urlParam == newItem.urlParam
                && oldItem.name == newItem.name
    }
}