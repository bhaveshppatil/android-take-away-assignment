package com.bhavesh.itunesapp.remote.clicklistener

import com.bhavesh.itunesapp.remote.model.Results

interface ClickListener {

    fun onTrackClick(results: Results)
}