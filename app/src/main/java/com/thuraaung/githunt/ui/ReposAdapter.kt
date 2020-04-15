package com.thuraaung.githunt.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thuraaung.githunt.R
import com.thuraaung.githunt.model.ModelTrendingRepo


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

        fun bind(repo : ModelTrendingRepo) {

        }
    }
}