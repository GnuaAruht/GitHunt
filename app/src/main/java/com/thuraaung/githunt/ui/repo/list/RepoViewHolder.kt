package com.thuraaung.githunt.ui.repo.list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.thuraaung.githunt.R
import com.thuraaung.githunt.base.BaseViewHolder
import com.thuraaung.githunt.model.ModelRepo
import com.thuraaung.githunt.utils.loadImage
import com.thuraaung.githunt.utils.setDrawableBackgroundColor


class RepoViewHolder(view : View) : BaseViewHolder<ModelRepo>(view) {

    private val tvRepoName : TextView = view.findViewById(R.id.tv_repo_name)
    private val tvAuthor : TextView = view.findViewById(R.id.tv_author)
    private val tvLanguage : TextView = view.findViewById(R.id.tvLanguage)
    private val tvStar : TextView = view.findViewById(R.id.tvStar)
    private val tvFork : TextView = view.findViewById(R.id.tvFork)

    private val imgAvatar : ImageView = view.findViewById(R.id.img_avator)

    override fun bind(item: ModelRepo) {
        tvRepoName.text = item.name
        tvAuthor.text = item.author
        tvLanguage.text = item.language
        item.languageColor?.let {
            tvLanguage.setDrawableBackgroundColor(item.languageColor)
        }
        tvStar.text = item.stars.toString()
        tvFork.text = item.forks.toString()
        imgAvatar.loadImage(item.avatar)
    }
}