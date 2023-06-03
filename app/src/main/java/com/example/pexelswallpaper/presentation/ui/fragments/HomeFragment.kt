package com.example.pexelswallpaper.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pexelswallpaper.R
import com.example.pexelswallpaper.databinding.FragmentHomeBinding
import com.example.pexelswallpaper.presentation.ui.adapters.WallpaperAdapter
import com.example.pexelswallpaper.presentation.viewmodels.GetWallPaperViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var wallpaperAdapter: WallpaperAdapter
    private lateinit var query: String
    private val perPage: Int = 80
    private val getWallpaperViewModel by viewModels<GetWallPaperViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        homeBinding = FragmentHomeBinding.bind(view)
        wallpaperAdapter = WallpaperAdapter()
        query = "minimalist"
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestApi()
        setUpRecyclerView()
    }

    private fun requestApi() {
        getWallpaperViewModel.getWallpaper(query, perPage)
        CoroutineScope(Dispatchers.Main).launch {
            getWallpaperViewModel.wallpaperListValue.collect{
                when{
                    it.isLoading->{
                        homeBinding.homeProgressbar.visibility = View.VISIBLE
                    }
                    it.wallPaperList !=null ->{
                        homeBinding.homeProgressbar.visibility = View.GONE
                        wallpaperAdapter.differ.submitList(it.wallPaperList!!.photos)
                    }
                    it.errorMessage.isNotBlank()->{
                        homeBinding.homeProgressbar.visibility = View.GONE
                        Toast.makeText(requireContext(), "${it.errorMessage}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setUpRecyclerView(){
        homeBinding.rcvWallpaper.apply {
            adapter = wallpaperAdapter
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        }
    }

}