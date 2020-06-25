package com.thuraaung.githunt.ui.repo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.thuraaung.githunt.R
import com.thuraaung.githunt.databinding.LayoutRepoItemBinding
import com.thuraaung.githunt.model.ModelRepo

class RepoAdapter : RecyclerView.Adapter<RepoAdapter.MyViewHolder>() {

    private val itemList = mutableListOf<ModelRepo>()
    var repoClickListener : ((ModelRepo) -> Unit)? = null

    inner class MyViewHolder(private val binding : LayoutRepoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                repoClickListener?.invoke(itemList[adapterPosition])
            }
        }

        fun bind(repo : ModelRepo) {
            binding.repo = repo
            binding.executePendingBindings()
        }
    }

    fun updateItems(newItems : List<ModelRepo>) {
        itemList.clear()
        itemList.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<LayoutRepoItemBinding>(inflater,R.layout.layout_repo_item,parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemList[position])
    }
}