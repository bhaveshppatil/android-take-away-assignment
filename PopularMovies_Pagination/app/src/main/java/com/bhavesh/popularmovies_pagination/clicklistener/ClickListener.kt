package com.bhavesh.popularmovies_pagination.clicklistener

import com.bhavesh.popularmovies_pagination.response.Results

interface ClickListener {

    fun onClick(results: Results)
}