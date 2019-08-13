package com.rybarstudios.favoritepicturesgallery.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.rybarstudios.favoritepicturesgallery.R
import com.rybarstudios.favoritepicturesgallery.activity.DetailsActivity
import com.rybarstudios.favoritepicturesgallery.model.ImageData
import kotlinx.android.synthetic.main.image_list_layout.view.*
import java.io.Serializable

class RecyclerViewAdapter(val imageList: ArrayList<ImageData>) : RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder>() {

    companion object {
        const val EDIT_IMAGE_REQUEST_CODE = 42
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_list_layout, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val data = imageList[position]
        holder.image.setImageURI(data.fileUri)
        holder.imageName.text = data.name
        holder.imageListLayout.tag = position
        holder.imageListLayout.setOnClickListener { view ->
            val intent = Intent(view.context, DetailsActivity::class.java)
            intent.putExtra("object", imageList[Integer.parseInt(view.tag.toString())])
            (view.context as Activity).startActivityForResult(intent, EDIT_IMAGE_REQUEST_CODE)
        }


    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageName: TextView = view.image_list_text_view
        var image: ImageView = view.image_list_image_view
        var imageListLayout: androidx.constraintlayout.widget.ConstraintLayout = view.image_list_main_layout
    }
}