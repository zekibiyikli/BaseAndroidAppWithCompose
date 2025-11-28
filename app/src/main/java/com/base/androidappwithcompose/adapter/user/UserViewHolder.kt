package com.base.androidappwithcompose.adapter.user

import android.content.Context
import com.base.androidappwithcompose.core.BaseViewHolder
import com.base.androidappwithcompose.databinding.ItemUserBinding
import com.base.androidappwithcompose.ext.loadImage
import com.base.androidappwithcompose.model.UserModel

class UserViewHolder(
    private val binding: ItemUserBinding
) : BaseViewHolder<UserModel>(binding.root) {
    override fun bind(
        model: UserModel?,
        onItemClickListener: ((UserModel?) -> Unit?)?,
        onItemLongClickListener: ((UserModel?) -> Unit?)?,
        context: Context,
        position: Int
    ) {

        model?.let {
            binding.imgUser.loadImage(context,it.picture.medium)
            binding.tvName.text="${it.name.first} ${it.name.last}"
            binding.tvCity.text=it.location.city
        }

        binding.root.setOnClickListener {
            onItemClickListener?.invoke(model)
        }

        binding.root.setOnLongClickListener {
            onItemLongClickListener?.invoke(model)
            true
        }
    }
}