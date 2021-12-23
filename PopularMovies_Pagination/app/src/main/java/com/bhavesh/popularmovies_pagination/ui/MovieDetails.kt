package com.bhavesh.popularmovies_pagination.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bhavesh.popularmovies_pagination.R
import com.bhavesh.popularmovies_pagination.databinding.ActivityMovieDetailsBinding
import com.bhavesh.popularmovies_pagination.model.MovieDetailsModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.movie_item_layout.*

class MovieDetails : AppCompatActivity() {

    private lateinit var dataBinding: ActivityMovieDetailsBinding
    private lateinit var  webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        webView = dataBinding.webView
        var intent = Intent()
        intent = getIntent()
        val title = intent.getStringExtra("title")
        val overview = intent.getStringExtra("overview")
        val language = intent.getStringExtra("language")
        val release_date = intent.getStringExtra("release_date")
        val vote_average = intent.getDoubleExtra("vote_average", 0.0)
        val vote_count = intent.getIntExtra("vote_count", 0)
        val poster_path = intent.getStringExtra("poster_path")

        val movieDetails = MovieDetailsModel(
            title = title!!,
            overview = overview!!,
            language = language!!,
            release_date = release_date!!,
            vote_average = vote_average.toString(),
            vote_count = vote_count.toString(),
            poster_path = poster_path!!,
        )
        dataBinding.movie = movieDetails

        tvMovieName.text = title
        tvOverview.text = overview
        tvRelease_date.text = release_date
        tvRating.text = "$vote_average ratings"
        tvTotalVote.text = "$vote_count"
        tvLang.text = language
        Glide.with(ivMoviePoster).load(poster_path).into(ivMoviePoster)

        dataBinding.btnMovieBook.setOnClickListener {
            dataBinding.webView.visibility = View.VISIBLE

            val movie_path = intent.getStringExtra("movie_id")
            startWebView(movie_path!!)
        }
    }

    private fun startWebView(url: String) {
        val settings = dataBinding.webView.settings
        settings.javaScriptEnabled = true
        WebView.setWebContentsDebuggingEnabled(false)

        webView.scrollBarStyle = View.SCROLLBARS_OUTSIDE_OVERLAY
        webView.settings.useWideViewPort = true
        webView.settings.loadWithOverviewMode = true

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...")
        progressDialog.show()
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                if (progressDialog.isShowing) {
                    progressDialog.dismiss()
                }
            }

            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String,
                failingUrl: String
            ) {
                Toast.makeText(this@MovieDetails, "Error:$description", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        webView.loadUrl(url)
    }
}
