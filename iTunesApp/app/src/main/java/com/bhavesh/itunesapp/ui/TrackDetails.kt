package com.bhavesh.itunesapp.ui

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bhavesh.itunesapp.R
import com.bumptech.glide.Glide

class TrackDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_details)

        val ivTrackPoster = findViewById<ImageView>(R.id.ivTrackPoster)
        val tvTrackName = findViewById<TextView>(R.id.tvTrackName)
        val ivPlay = findViewById<ImageView>(R.id.ivPlay)
        val ivPause = findViewById<ImageView>(R.id.ivPause)
        val ivStop = findViewById<ImageView>(R.id.ivStop)

        var intent = Intent()
        intent = getIntent()
        val trackName = intent.getStringExtra("trackName")
        val artistName = intent.getStringExtra("artistName")
        val artworkUrl100 = intent.getStringExtra("artworkUrl100")
        val releaseDate = intent.getStringExtra("releaseDate")
        val trackViewUrl = intent.getStringExtra("trackViewUrl")
        val trackTimeMillis = intent.getStringExtra("trackTimeMillis")

        Glide.with(ivTrackPoster).load(artworkUrl100).into(ivTrackPoster)
        tvTrackName.text = trackName
      /*  val uri = Uri.parse(trackViewUrl)
        val mediaPlayer = MediaPlayer.create(this, uri)
        ivPlay.setOnClickListener {
            mediaPlayer.start()
        }
        ivPause.setOnClickListener {
            mediaPlayer.pause()
        }
        ivStop.setOnClickListener {
            mediaPlayer.stop()
        }*/
    }
}