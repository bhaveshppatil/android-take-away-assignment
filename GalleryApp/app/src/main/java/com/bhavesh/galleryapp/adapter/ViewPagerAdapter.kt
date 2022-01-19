package com.bhavesh.galleryapp.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.bhavesh.galleryapp.fragment.ImageFragment
import com.bhavesh.galleryapp.fragment.VideoFragment

internal class ViewPagerAdapter(
    context: Context,
    fragmentManager: FragmentManager,
    var totalTabs: Int
) : FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                ImageFragment()
            }
            1 -> {
                VideoFragment()
            }
            else -> getItem(position)
        }
    }
}

