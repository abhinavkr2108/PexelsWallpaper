package com.example.pexelswallpaper.presentation.ui.fragments

import android.app.WallpaperManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.pexelswallpaper.R
import com.example.pexelswallpaper.databinding.FragmentWallpaperViewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL


class WallpaperViewFragment : Fragment() {
    private lateinit var wallPaperBinding: FragmentWallpaperViewBinding
    private val args by navArgs<WallpaperViewFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_wallpaper_view, container, false)
        wallPaperBinding = FragmentWallpaperViewBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wallPaperBinding.imgApplyWallpaper.load(args.url)

        wallPaperBinding.btnApplyWallpaper.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val inputStream = URL(args.url).openStream()
                WallpaperManager.getInstance(requireContext()).setStream(inputStream)
                lifecycleScope.launch(Dispatchers.Main){
                    Toast.makeText(requireContext(), "Wallpaper Applied", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

}