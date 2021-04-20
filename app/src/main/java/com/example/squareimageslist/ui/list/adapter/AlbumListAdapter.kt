package com.example.squareimageslist.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.squareimageslist.R
import com.example.squareimageslist.databinding.AlbumItemActivityListBinding
import com.example.squareimageslist.model.response.Album

class AlbumListAdapter : RecyclerView.Adapter<AlbumListAdapter.ViewHolder>() {

    private val albums = mutableListOf<Album>()
    private var albumListener: AlbumListListener? = null

    fun setAlbums(albums: List<Album>, albumListListener: AlbumListListener) {
        this.albumListener = albumListListener
        if (this.albums != albums) {
            this.albums.clear()
            this.albums.addAll(albums)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.album_item_activity_list,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(albums[position])

    inner class ViewHolder(@NonNull val albumItemActivityListBinding: AlbumItemActivityListBinding) :
        RecyclerView.ViewHolder(albumItemActivityListBinding.root) {

        fun bind(album: Album) {
            albumItemActivityListBinding.apply {
                this.album = album
                this.row.setOnClickListener {
                    albumListener?.onItemClick(album)
                }
            }
        }
    }
}