package com.thuraaung.githunt.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thuraaung.githunt.R
import com.thuraaung.githunt.model.ModelTrendingRepo
import com.thuraaung.githunt.utils.loadImage


class ReposAdapter : RecyclerView.Adapter<ReposAdapter.RepoViewHolder>() {

    private var repoList = emptyList<ModelTrendingRepo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_repo_item,parent,false)
        return RepoViewHolder(view)
    }

    override fun getItemCount(): Int = repoList.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(repoList[position])
    }

    fun updateList(repos : List<ModelTrendingRepo>) {
        repoList = repos
        notifyDataSetChanged()
    }

    inner class RepoViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        private val tvRepoName : TextView = view.findViewById(R.id.tv_repo_name)
        private val tvAuthor : TextView = view.findViewById(R.id.tv_author)
        private val imgAvator : ImageView = view.findViewById(R.id.img_avator)

        fun bind(repo : ModelTrendingRepo) {

            tvRepoName.text = repo.name
            tvAuthor.text = repo.author
            imgAvator.loadImage(repo.avatar)
        }
    }
}