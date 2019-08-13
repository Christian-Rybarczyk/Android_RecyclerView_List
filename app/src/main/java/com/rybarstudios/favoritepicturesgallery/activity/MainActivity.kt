package com.rybarstudios.favoritepicturesgallery.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.rybarstudios.favoritepicturesgallery.R
import com.rybarstudios.favoritepicturesgallery.adapter.RecyclerViewAdapter
import com.rybarstudios.favoritepicturesgallery.model.ImageData
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class MainActivity : AppCompatActivity(), Serializable {

    var imageList: ArrayList<ImageData> = ArrayList()
    var adapter: RecyclerViewAdapter? = null

    companion object {
        const val IMAGE_REQUEST_CODE = 65
        const val EDIT_IMAGE_REQUEST_CODE = 42
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageList = ArrayList()

        add_image_button.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, IMAGE_REQUEST_CODE)
            }
        }

        val manager = LinearLayoutManager(this)
        recycler_view.layoutManager = manager

        adapter = RecyclerViewAdapter(imageList)
        recycler_view.adapter = adapter
    }

    private fun refreshListView() {
        adapter!!.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_REQUEST_CODE) {
            if (data != null) {
                val imageUri = data.data
                imageList.add(ImageData(imageUri!!))
                adapter!!.notifyItemInserted(imageList.size - 1)
            }
        } else if (requestCode == EDIT_IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            // Make sure the request was successful
            val returnedData = data!!.getSerializableExtra("object") as ImageData

            for (i in imageList.indices) {
                if (imageList[i].fileUriString == returnedData.fileUriString) {
                    imageList[i] = returnedData
                }
            }
        }
        refreshListView()
    }
}
