package com.example.pexelswallpaper.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pexelswallpaper.R
import com.example.pexelswallpaper.databinding.WallpaperLayoutBinding
import com.example.pexelswallpaper.domain.models.Photo
import com.example.pexelswallpaper.presentation.ui.fragments.HomeFragmentDirections
import com.example.pexelswallpaper.presentation.ui.fragments.SearchFragmentDirections

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(){
    private val callback = object : DiffUtil.ItemCallback<Photo>(){
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.wallpaper_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val wallpaperPosition = differ.currentList[position]
        holder.apply {
            image.load(wallpaperPosition.src.portrait)
            itemView.setOnClickListener {
                val direction = SearchFragmentDirections.actionSearchFragmentToWallpaperViewFragment(wallpaperPosition.src.portrait)
                it.findNavController().navigate(direction)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val binding = WallpaperLayoutBinding.bind(itemView)
        val image = binding.imgWallpaper
    }
}