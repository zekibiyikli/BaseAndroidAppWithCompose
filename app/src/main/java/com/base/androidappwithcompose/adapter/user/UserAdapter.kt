package com.base.androidappwithcompose.adapter.user

import android.content.Context
import com.base.androidappwithcompose.R
import com.base.androidappwithcompose.core.BaseAdapter
import com.base.androidappwithcompose.databinding.ItemUserBinding
import com.base.androidappwithcompose.model.UserModel

class UserAdapter(
    private var itemList: MutableList<UserModel>,
    onItemClickListener: ((model: UserModel?) -> Unit)? = null,
    onItemLongClickListener: ((model: UserModel?) -> Unit)? = null,
    context: Context
) : BaseAdapter<UserModel, ItemUserBinding, UserViewHolder>(
    R.layout.item_user,
    ItemUserBinding::class,
    UserViewHolder::class,
    itemList,
    onItemClickListener,
    onItemLongClickListener,
    context
) {
    fun setData(items: List<UserModel>) {
        itemList = items.toMutableList()
        notifyItemChanged(itemList.size - 1)
    }

    fun addData(list: List<UserModel>) {
        val oldSize = itemList.size
        itemList.addAll(list)
        val newSize = itemList.size
        notifyItemRangeChanged(oldSize, newSize)
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