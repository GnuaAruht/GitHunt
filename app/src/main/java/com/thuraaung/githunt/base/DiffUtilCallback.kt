package com.thuraaung.githunt.base

import androidx.recyclerview.widget.DiffUtil


abstract class DiffUtilCallback<T : BaseItem> : DiffUtil.Callback() {

    protected var oldItemList = emptyList<T>()
    protected var newItemList = emptyList<T>()

    fun submitList(old : List<T>,new : List<T>) {
        oldItemList = old
        newItemList = new
    }

    override fun getOldListSize(): Int = oldItemList.size

    override fun getNewListSize(): Int = newItemList.size

}