package com.base.androidappwithcompose.adapter.shimmer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.androidappwithcompose.R
import com.facebook.shimmer.ShimmerFrameLayout

class ShimmerAdapter(private val itemCount: Int = 10) : RecyclerView.Adapter<ShimmerAdapter.ShimmerViewHolder>() {

    inner class ShimmerViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShimmerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_shimmer, parent, false)
        return ShimmerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShimmerViewHolder, position: Int) {
        val shimmerLayout = holder.itemView.findViewById<ShimmerFrameLayout>(R.id.shimmer_layout)
        shimmerLayout.startShimmer()
    }

    override fun getItemCount(): Int = itemCount
}
