package com.base.androidappwithcompose.adapter.user2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.base.androidappwithcompose.databinding.ItemUserBinding
import com.base.androidappwithcompose.model.UserModel

class User2Adapter(
    private val onItemClickListener: ((UserModel?) -> Unit)? = null,
    private val onItemLongClickListener: ((UserModel?) -> Unit)? = null
) : PagingDataAdapter<UserModel, User2ViewHolder>(UserDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): User2ViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return User2ViewHolder(binding, onItemClickListener, onItemLongClickListener)
    }

    override fun onBindViewHolder(holder: User2ViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model)
    }

    companion object UserDiffUtil : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem.login.uuid == newItem.login.uuid
        }

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem == newItem
        }
    }
}
