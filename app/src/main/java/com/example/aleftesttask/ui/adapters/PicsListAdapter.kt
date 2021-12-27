package com.example.aleftesttask.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aleftesttask.R
import com.example.aleftesttask.network.entity.PicsListResponse
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_pics_row.view.*

class PicsListAdapter(
    private val onClick: (String) -> Unit
): ListAdapter<List<String>, PicsListAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<List<String>>() {
        override fun areItemsTheSame(oldItem: List<String>, newItem: List<String>): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: List<String>, newItem: List<String>): Boolean =
            oldItem == newItem
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_pics_row, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val urls = getItem(position)?.map { s -> s.replace("http:", "https:")}
        if (urls == null || urls.size < 2)
            return

        holder.containerView.run {
            Picasso.get().load(urls[0]).placeholder(R.drawable.ic_no_pictures).into(pic_row_im1)
            pic_row_im1.setOnClickListener { onClick(urls[0]) }
            Picasso.get().load(urls[1]).placeholder(R.drawable.ic_no_pictures).into(pic_row_im2)
            pic_row_im2.setOnClickListener { onClick(urls[1]) }
            if (resources.getBoolean(R.bool.isTablet) && urls.size > 2) {
                Picasso.get().load(urls[2]).placeholder(R.drawable.ic_no_pictures).into(pic_row_im3)
                pic_row_im3?.setOnClickListener { onClick(urls[2]) }
            }
        }
    }

    class ViewHolder(override val containerView: View):
        RecyclerView.ViewHolder(containerView),
        LayoutContainer
}