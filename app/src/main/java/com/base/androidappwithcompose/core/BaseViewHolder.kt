package com.base.androidappwithcompose.core

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<Model : BaseModel>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    abstract fun bind(
        model: Model?,
        onItemClickListener: ((Model?) -> Unit?)? = null,
        onItemLongClickListener: ((Model?)-> Unit?)? = null,
        context: Context,
        position:Int
    )
}