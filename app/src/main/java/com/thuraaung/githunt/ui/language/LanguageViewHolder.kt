package com.thuraaung.githunt.ui.language

import android.view.View
import android.widget.TextView
import com.thuraaung.githunt.R
import com.thuraaung.githunt.base.BaseViewHolder
import com.thuraaung.githunt.model.ModelLanguage


class LanguageViewHolder(view : View) : BaseViewHolder<ModelLanguage>(view) {

    private val tvName : TextView = view.findViewById(R.id.tv_language)

    override fun bind(item: ModelLanguage) {
        tvName.text = item.name
    }
}