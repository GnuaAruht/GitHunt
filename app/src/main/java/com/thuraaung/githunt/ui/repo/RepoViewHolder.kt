package com.thuraaung.githunt.ui.repo

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.thuraaung.githunt.R
import com.thuraaung.githunt.base.BaseViewHolder
import com.thuraaung.githunt.model.ModelTrendingRepo
import com.thuraaung.githunt.utils.loadImage


class RepoViewHolder(view : View) : BaseViewHolder<ModelTrendingRepo>(view) {

    private val tvRepoName : TextView = view.findViewById(R.id.tv_repo_name)
    private val tvAuthor : TextView = view.findViewById(R.id.tv_author)
    private val imgAvatar : ImageView = view.findViewById(R.id.img_avator)

    override fun bind(item: ModelTrendingRepo) {
        tvRepoName.text = item.name
        tvAuthor.text = item.author
        imgAvatar.loadImage(item.avatar)
    }
}