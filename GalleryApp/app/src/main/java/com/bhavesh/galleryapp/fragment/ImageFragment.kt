package com.bhavesh.galleryapp.fragment

import android.Manifest.permission.READ_EXTERNAL_STORAGE
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
import com.bhavesh.galleryapp.adapter.ImageAdapter
import com.bhavesh.galleryapp.inteface.OnItemClick
import kotlinx.android.synthetic.main.fragment_image.*
import java.io.File


class ImageFragment : Fragment(R.layout.fragment_image), OnItemClick {
    private val PERMISSION_REQUEST_CODE: Int = 200
    private val imageList = mutableListOf<String>()
    private lateinit var imageAdapter: ImageAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        checkPermissionGrantedOrNot()
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(READ_EXTERNAL_STORAGE),
            PERMISSION_REQUEST_CODE
        )
    }

    private fun setRecyclerView() {
        imageAdapter = context?.let { ImageAdapter(it, imageList, this) }!!
        image_recycler_view.apply {
            adapter = imageAdapter
            layoutManager = GridLayoutManager(context, 3)
        }
    }

    private fun checkPermission(): Boolean {
        val result = context?.applicationContext?.let {
            ContextCompat.checkSelfPermission(
                it,
                READ_EXTERNAL_STORAGE
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
        val externalStorage = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

        if (externalStorage) {
            val columns = arrayOf(MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID)
            val order = MediaStore.Images.Media._ID

            val cursor: Cursor? = context?.contentResolver?.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                columns,
                null,
                null,
                order
            )
            val count: Int? = cursor?.count
            for (i in 0 until count!!) {
                cursor.moveToPosition(i)
                val columnIndex: Int = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                imageList.add(cursor.getString(columnIndex))
            }
            imageAdapter.notifyDataSetChanged()
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
                    val granted = grantResults[0] === PackageManager.PERMISSION_GRANTED
                    if (granted) {
                        Toast.makeText(context, "Permissions Granted.", Toast.LENGTH_SHORT).show()
                        loadImagePath()
                    } else {
                        Toast.makeText(
                            context,
                            "Permissions denied",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    override fun onImageClick(file: File) {

    }
}