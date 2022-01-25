package com.example.testapplampa

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.testapplampa.ui.favourites_scene.FavouritesFragment
import com.example.testapplampa.ui.stories_scene.StoriesFragment
import com.example.testapplampa.ui.video_scene.VideoFragment

class MyAdapter(fm: FragmentManager, lifeCycle: Lifecycle) : FragmentStateAdapter(fm,lifeCycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                StoriesFragment()
            }

            1 -> {
                VideoFragment()
            }

            2 -> {
                FavouritesFragment()
            }
            else -> {

                VideoFragment()
            }

        }
    }
}