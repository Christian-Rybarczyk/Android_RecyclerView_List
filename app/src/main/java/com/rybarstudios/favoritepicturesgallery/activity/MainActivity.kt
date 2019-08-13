package com.rybarstudios.favoritepicturesgallery.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.rybarstudios.favoritepicturesgallery.R
import com.rybarstudios.favoritepicturesgallery.adapter.RecyclerViewAdapter
import com.rybarstudios.favoritepicturesgallery.model.ImageData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var imageList: ArrayList<ImageData> = ArrayList()

    companion object {
        const val IMAGE_REQUEST_CODE = 654321
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageList = ArrayList()

        

        val manager = LinearLayoutManager(this)
        recycler_view.layoutManager = manager

        val adapter = RecyclerViewAdapter(imageList)
        recycler_view.adapter = adapter
    }
}
