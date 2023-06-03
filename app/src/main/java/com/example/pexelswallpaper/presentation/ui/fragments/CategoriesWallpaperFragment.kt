package com.example.pexelswallpaper.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pexelswallpaper.R
import com.example.pexelswallpaper.databinding.FragmentCategoriesWallpaperBinding
import com.example.pexelswallpaper.presentation.ui.adapters.CategoriesAdapter
import com.example.pexelswallpaper.presentation.ui.adapters.CategoryWallpaperAdapter
import com.example.pexelswallpaper.presentation.ui.adapters.WallpaperAdapter
import com.example.pexelswallpaper.presentation.viewmodels.GetWallPaperViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoriesWallpaperFragment : Fragment() {
    private lateinit var categoriesWallpaperBinding: FragmentCategoriesWallpaperBinding
    private lateinit var categoryAdapter:CategoryWallpaperAdapter
    private val args by navArgs<CategoriesWallpaperFragmentArgs>()
    private lateinit var query: String
    private val getWallPaperViewModel by viewModels<GetWallPaperViewModel>()
    private val perPage = 50

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_categories_wallpaper, container, false)
        categoriesWallpaperBinding = FragmentCategoriesWallpaperBinding.bind(view)
        categoryAdapter = CategoryWallpaperAdapter()
        query = args.query
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        callApi()
        setUpRecyclerView()

    }

    private fun callApi() {
        getWallPaperViewModel.getWallpaper(query,perPage)
        CoroutineScope(Dispatchers.Main).launch { 
            getWallPaperViewModel.wallpaperListValue.collect{
                when{
                    it.isLoading->{
                        categoriesWallpaperBinding.categoryProgressbar.visibility = View.VISIBLE
                    }
                    it.wallPaperList !=null ->{
                        categoriesWallpaperBinding.categoryProgressbar.visibility = View.GONE
                        categoryAdapter.differ.submitList(it.wallPaperList!!.photos)
                    }
                    it.errorMessage.isNotBlank()->{
                        categoriesWallpaperBinding.categoryProgressbar.visibility = View.GONE
                        Toast.makeText(requireContext(), "${it.errorMessage}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setUpRecyclerView(){
        categoriesWallpaperBinding.rcvCategoryWallpaper.apply {
            adapter = categoryAdapter
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        }
    }


}