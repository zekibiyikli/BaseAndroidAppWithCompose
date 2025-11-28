package com.base.androidappwithcompose.adapter.user3

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import com.base.androidappwithcompose.R
import com.base.androidappwithcompose.core.BaseAdapter
import com.base.androidappwithcompose.databinding.ItemUserBinding
import com.base.androidappwithcompose.model.UserModel
import com.base.androidappwithcompose.util.UserDiffCallback

class User3Adapter(
    private var itemList: MutableList<UserModel>,
    onItemClickListener: ((model: UserModel?) -> Unit)? = null,
    onItemLongClickListener: ((model: UserModel?) -> Unit)? = null,
    context: Context
) : BaseAdapter<UserModel, ItemUserBinding, User3ViewHolder>(
    R.layout.item_user,
    ItemUserBinding::class,
    User3ViewHolder::class,
    itemList,
    onItemClickListener,
    onItemLongClickListener,
    context
) {
    fun setData(items: List<UserModel>) {
        val diffCallback = UserDiffCallback(itemList, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        itemList.clear()
        itemList.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

    fun addData(list: List<UserModel>) {
        val newList = ArrayList(itemList)
        newList.addAll(list)

        val diffCallback = UserDiffCallback(itemList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        itemList.clear()
        itemList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    fun removeData(item: UserModel) {
        itemList.remove(item)
        notifyDataSetChanged()
    }

    fun getData(): MutableList<UserModel> {
        return itemList
    }

    fun removeAll(){
        itemList.clear()
        notifyDataSetChanged()
    }

}