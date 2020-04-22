package com.thuraaung.githunt.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


abstract class BaseAdapter<T : BaseItem,V : BaseViewHolder<T>>() : RecyclerView.Adapter<V>() {

    protected val itemList = mutableListOf<T>()

    protected abstract val diffUtilCallback : DiffUtilCallback<T>

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: V, position: Int) {
        holder.bind(itemList[position])
    }

    fun updateItems(newItems : List<T>) {
        diffUtilCallback.submitList(itemList,newItems)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        itemList.clear()
        itemList.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }
}