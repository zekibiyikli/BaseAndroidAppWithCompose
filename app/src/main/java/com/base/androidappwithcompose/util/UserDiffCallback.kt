package com.base.androidappwithcompose.util

import androidx.recyclerview.widget.DiffUtil
import com.base.androidappwithcompose.model.UserModel

class UserDiffCallback(
    private val oldList: List<UserModel>,
    private val newList: List<UserModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].login.uuid == newList[newItemPosition].login.uuid
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
