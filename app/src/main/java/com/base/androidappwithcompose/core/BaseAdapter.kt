package com.base.androidappwithcompose.core

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import kotlin.reflect.KClass

abstract class BaseAdapter<Model : BaseModel, VB : ViewDataBinding, VH : BaseViewHolder<Model>>(
    private val layoutId: Int,
    private val viewBindingType: KClass<VB>,
    private val viewHolderType: KClass<VH>,
    private val itemList: List<Model>,
    private val onItemClickListener: ((Model?) -> Unit?)? = null,
    private val onItemLongClickListener :((Model?) -> Unit?)? = null,
    private val context: Context
) : RecyclerView.Adapter<VH>(), Filterable {

    open val customFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults = FilterResults()
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        viewHolderType
            .javaObjectType
            .getDeclaredConstructor(viewBindingType.javaObjectType)
            .newInstance(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    viewType,
                    parent,
                    false
                )
            )

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(itemList[position], onItemClickListener,onItemLongClickListener, context,position)
    }

    override fun getFilter(): Filter = customFilter

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return layoutId
    }
}