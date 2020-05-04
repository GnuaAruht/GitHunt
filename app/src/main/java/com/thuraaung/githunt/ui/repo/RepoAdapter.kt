package com.thuraaung.githunt.ui.repo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thuraaung.githunt.R
import com.thuraaung.githunt.model.ModelRepo
import com.thuraaung.githunt.utils.loadImage
import com.thuraaung.githunt.utils.setDrawableBackgroundColor

class RepoAdapter : RecyclerView.Adapter<RepoAdapter.MyViewHolder>() {

    private val itemList = mutableListOf<ModelRepo>()

    inner class MyViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        private val tvRepoName : TextView = view.findViewById(R.id.tv_repo_name)
        private val tvAuthor : TextView = view.findViewById(R.id.tv_author)
        private val tvLanguage : TextView = view.findViewById(R.id.tvLanguage)
        private val tvStar : TextView = view.findViewById(R.id.tvStar)
        private val tvFork : TextView = view.findViewById(R.id.tvFork)

        private val imgAvatar : ImageView = view.findViewById(R.id.img_avator)

        fun bind(item: ModelRepo) {
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

    fun updateItems(newItems : List<ModelRepo>) {
        itemList.clear()
        itemList.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_repo_item,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemList[position])
    }
}