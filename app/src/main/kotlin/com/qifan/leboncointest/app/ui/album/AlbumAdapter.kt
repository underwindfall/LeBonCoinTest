package com.qifan.leboncointest.app.ui.album

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.qifan.leboncointest.R
import com.qifan.leboncointest.domain.model.AlbumModel
import com.squareup.picasso.Picasso

class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {
    private var albums = mutableListOf<AlbumModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(albums[position]) {
            Picasso.get()
                .load(thumbnailUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.coverImageView)

            holder.albumTextView.text = "Album: $albumId"
            holder.albumIdTextView.text = id.toString()
            holder.titleTextView.text = title
        }
    }

    override fun getItemCount(): Int = albums.size


    fun setData(data: List<AlbumModel>) {
        albums.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val coverImageView: AppCompatImageView = itemView.findViewById(R.id.item_thumbnail)
        internal val albumTextView: AppCompatTextView = itemView.findViewById(R.id.item_id)
        internal val titleTextView: AppCompatTextView = itemView.findViewById(R.id.item_title)
        internal val albumIdTextView: AppCompatTextView = itemView.findViewById(R.id.item_album_id)
    }
}
