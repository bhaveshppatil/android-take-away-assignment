package com.bhavesh.galleryapp.fragment

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bhavesh.galleryapp.R
import com.bhavesh.galleryapp.adapter.VideoAdapter
import com.bhavesh.galleryapp.inteface.OnItemClick
import kotlinx.android.synthetic.main.fragment_video.*
import java.io.File

class VideoFragment : Fragment(R.layout.fragment_video), OnItemClick {
    private val PERMISSION_REQUEST_CODE: Int = 200
    private val videoList = mutableListOf<String>()
    private lateinit var videoAdapter: VideoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        checkPermissionGrantedOrNot()
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            PERMISSION_REQUEST_CODE
        )
    }

    private fun setRecyclerView() {
        videoAdapter = context?.let { VideoAdapter(it, videoList, this) }!!
        video_recycler_view.apply {
            adapter = videoAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    private fun checkPermission(): Boolean {
        val result = context?.applicationContext?.let {
            ContextCompat.checkSelfPermission(
                it,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
        return result == PackageManager.PERMISSION_GRANTED
    }


    private fun checkPermissionGrantedOrNot() {
        if (checkPermission()) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            loadImagePath()
        } else {
            requestPermission()
        }
    }

    private fun loadImagePath() {
        val isExternalStorage = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

        if (isExternalStorage) {
            val columns = arrayOf(MediaStore.Video.Media.DATA, MediaStore.Video.Media._ID)
            val sortOrder = MediaStore.Video.Media._ID

            val cursor: Cursor? = context?.contentResolver?.query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                columns,
                null,
                null,
                sortOrder
            )
            val count: Int? = cursor?.count
            for (i in 0 until count!!) {
                cursor.moveToPosition(i)
                val columnIndex: Int = cursor.getColumnIndex(MediaStore.Video.Media.DATA)
                videoList.add(cursor.getString(columnIndex))
            }
            videoAdapter.notifyDataSetChanged()
            cursor.close()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        when (requestCode) {
            PERMISSION_REQUEST_CODE ->
                if (grantResults.isNotEmpty()) {
                    val storageAccepted = grantResults[0] === PackageManager.PERMISSION_GRANTED
                    if (storageAccepted) {
                        Toast.makeText(context, "Permissions Granted.", Toast.LENGTH_SHORT).show()
                        loadImagePath()
                    } else {
                        // if permissions are denied we are closing the app and displaying the toast message.
                        Toast.makeText(
                            context,
                            "Permissions denied.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    override fun onImageClick(file: File) {

    }
}