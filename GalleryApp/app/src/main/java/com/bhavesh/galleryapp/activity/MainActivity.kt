package com.bhavesh.galleryapp.activity

import android.graphics.PorterDuff
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.bhavesh.galleryapp.R
import com.bhavesh.galleryapp.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        tabLayout.addTab(tabLayout.newTab().setText("Images"))
        tabLayout.addTab(tabLayout.newTab().setText("Videos"))

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = ViewPagerAdapter(this, supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = adapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
                tabLayout.getTabAt(0)?.icon?.setColorFilter(
                    resources.getColor(android.R.color.black),
                    PorterDuff.Mode.SRC_IN
                );
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tabLayout.getTabAt(2)?.icon
                    ?.setColorFilter(
                        resources.getColor(android.R.color.white),
                        PorterDuff.Mode.SRC_IN
                    );
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}