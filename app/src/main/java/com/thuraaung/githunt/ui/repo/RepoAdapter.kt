package com.thuraaung.githunt.ui.repo

import android.view.LayoutInflater
import android.view.ViewGroup
import com.thuraaung.githunt.R
import com.thuraaung.githunt.base.BaseAdapter
import com.thuraaung.githunt.base.DiffUtilCallback
import com.thuraaung.githunt.model.ModelTrendingRepo


class RepoAdapter : BaseAdapter<ModelTrendingRepo,RepoViewHolder>() {

    override val diffUtilCallback: DiffUtilCallback<ModelTrendingRepo>
        get() = languageUtilCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_repo_item,parent,false)
        return RepoViewHolder(view)
    }
}

private val languageUtilCallback = object : DiffUtilCallback<ModelTrendingRepo>() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItemList[oldItemPosition].url == newItemList[newItemPosition].url
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItemList[oldItemPosition]
        val newItem = newItemList[newItemPosition]
        return oldItem.url == newItem.url
                && oldItem.author == newItem.author
    }
}