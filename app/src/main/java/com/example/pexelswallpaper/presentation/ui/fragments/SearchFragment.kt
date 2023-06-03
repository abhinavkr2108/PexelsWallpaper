package com.example.pexelswallpaper.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pexelswallpaper.R
import com.example.pexelswallpaper.databinding.FragmentSearchBinding
import com.example.pexelswallpaper.presentation.ui.adapters.SearchAdapter
import com.example.pexelswallpaper.presentation.viewmodels.GetWallPaperViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var searchBinding: FragmentSearchBinding
    private lateinit var searchAdapter: SearchAdapter
    private val getWallpaperViewModel by viewModels<GetWallPaperViewModel>()
    private val perPage = 50
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        searchBinding = FragmentSearchBinding.bind(view)
        searchAdapter = SearchAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                callApi(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        setUpRecyclerView()
    }

    private fun callApi(query: String?) {
        if (query!= null){
            getWallpaperViewModel.getWallpaper(query, perPage)
        }
        CoroutineScope(Dispatchers.Main).launch {
            getWallpaperViewModel.wallpaperListValue.collect{
                when{
                    it.isLoading->{
                        searchBinding.searchProgressbar.visibility = View.VISIBLE
                    }
                    it.wallPaperList !=null ->{
                        searchBinding.searchProgressbar.visibility = View.GONE
                        searchAdapter.differ.submitList(it.wallPaperList!!.photos)
                    }
                    it.errorMessage.isNotBlank()->{
                        searchBinding.searchProgressbar.visibility = View.GONE
                        Toast.makeText(requireContext(), "${it.errorMessage}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setUpRecyclerView(){
        searchBinding.rcvSearch.apply {
            adapter = searchAdapter
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        }
    }

}