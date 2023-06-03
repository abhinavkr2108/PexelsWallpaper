package com.example.pexelswallpaper.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pexelswallpaper.R
import com.example.pexelswallpaper.databinding.FragmentCategoriesBinding
import com.example.pexelswallpaper.domain.models.CategoryItem
import com.example.pexelswallpaper.presentation.ui.adapters.CategoriesAdapter

class CategoriesFragment : Fragment() {
    private lateinit var categoryList: ArrayList<CategoryItem>
    private lateinit var categoryBinding: FragmentCategoriesBinding
    private lateinit var categoriesAdapter: CategoriesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_categories, container, false)
        categoryBinding = FragmentCategoriesBinding.bind(view)
        categoryList = ArrayList()
        categoriesAdapter = CategoriesAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryList.add(
            CategoryItem(
            imageResource = "https://images.pexels.com/photos/3408744/pexels-photo-3408744.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=627&w=1200",
            text = "NATURE"
            )
        )
        categoryList.add(CategoryItem(
            imageResource = "https://images.pexels.com/photos/2693208/pexels-photo-2693208.png?auto=compress&cs=tinysrgb&fit=crop&h=627&w=1200",
            text = "ABSTRACT"
        ))
        categoryList.add(CategoryItem(
            imageResource = "https://images.pexels.com/photos/1661179/pexels-photo-1661179.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=627&w=1200",
            text = "ANIMALS"
        ))
        categoryList.add(CategoryItem(
            imageResource = "https://images.pexels.com/photos/1545743/pexels-photo-1545743.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=627&w=1200",
            text = "CARS"
        ))
        categoryList.add(CategoryItem(
            imageResource = "https://images.pexels.com/photos/1007426/pexels-photo-1007426.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=627&w=1200",
            text = "INDIA"
        ))

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        categoriesAdapter.categoryDiffer.submitList(categoryList as List<CategoryItem>)
        categoryBinding.rcvCategories.apply {
            adapter = categoriesAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

}