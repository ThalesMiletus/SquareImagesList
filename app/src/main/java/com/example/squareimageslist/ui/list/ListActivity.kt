package com.example.squareimageslist.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.squareimageslist.R
import com.example.squareimageslist.constant.RetrofitStatus
import com.example.squareimageslist.databinding.ActivityListBinding
import com.example.squareimageslist.model.response.Album
import com.example.squareimageslist.ui.list.adapter.AlbumListAdapter
import com.example.squareimageslist.ui.list.adapter.AlbumListListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListActivity : AppCompatActivity(), AlbumListListener {

    private val listViewModel: ListViewModel by viewModels()
    private lateinit var activityListBinding: ActivityListBinding

    @Inject
    lateinit var albumListAdapter: AlbumListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityListBinding = DataBindingUtil.setContentView(this, R.layout.activity_list)
        initObservables()
        initAlbumsRv()
        initSwipeRefreshLayout()
    }

    private fun initObservables() {
        listViewModel.albums.observe(this, { response ->
            when (response.status) {
                RetrofitStatus.SUCCESS -> response.albums?.let { albums ->
                    albumListAdapter.setAlbums(albums, this)
                    activityListBinding.textError.visibility = View.GONE
                    activityListBinding.recyclerview.visibility = View.VISIBLE
                }
                else -> {
                    activityListBinding.textError.text = resources.getString(R.string.text_error)
                    activityListBinding.recyclerview.visibility = View.GONE
                    activityListBinding.textError.visibility = View.VISIBLE
                }
            }
            activityListBinding.swipeRefreshLayout.isRefreshing = false
        })
    }

    private fun initAlbumsRv() {
        activityListBinding.recyclerview.apply {
            adapter = albumListAdapter
            setHasFixedSize(true)
        }
    }

    private fun initSwipeRefreshLayout() {
        activityListBinding.swipeRefreshLayout.setOnRefreshListener { listViewModel.getAlbums() }
        activityListBinding.swipeRefreshLayout.isRefreshing = true
    }

    override fun onItemClick(album: Album) {
        Toast.makeText(this, "Album selected : ${album.id}", Toast.LENGTH_SHORT).show()
    }
}