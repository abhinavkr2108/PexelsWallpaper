package com.example.pexelswallpaper.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pexelswallpaper.R
import com.example.pexelswallpaper.databinding.ItemCategoryBinding
import com.example.pexelswallpaper.domain.models.CategoryItem
import com.example.pexelswallpaper.presentation.ui.fragments.CategoriesFragmentDirections
import com.example.pexelswallpaper.presentation.ui.fragments.CategoriesWallpaperFragmentDirections

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>(){

    private val categoryCallback = object : DiffUtil.ItemCallback<CategoryItem>() {
        override fun areItemsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
            return oldItem.text == newItem.text
        }

        override fun areContentsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
            return oldItem == newItem
        }
    }

    val categoryDiffer = AsyncListDiffer(this, categoryCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val categoryPosition = categoryDiffer.currentList[position]
        holder.apply {
            image.load(categoryPosition.imageResource)
            textView.text = categoryPosition.text
            itemView.setOnClickListener {
                val navDirection = CategoriesFragmentDirections.actionCategoriesFragmentToCategoriesWallpaperFragment(categoryPosition.text.lowercase())
                it.findNavController().navigate(navDirection)
            }
        }
    }

    override fun getItemCount(): Int {
        return categoryDiffer.currentList.size
    }

    inner class CategoriesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val binding = ItemCategoryBinding.bind(itemView)
        val image = binding.imgCategory
        val textView = binding.tvItemCategory
    }
}