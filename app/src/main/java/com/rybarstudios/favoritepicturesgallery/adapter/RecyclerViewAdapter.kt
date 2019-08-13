package com.rybarstudios.favoritepicturesgallery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rybarstudios.favoritepicturesgallery.R
import com.rybarstudios.favoritepicturesgallery.model.ImageData
import kotlinx.android.synthetic.main.image_list_layout.view.*

class RecyclerViewAdapter(val imageList: ArrayList<ImageData>) : RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_list_layout, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val data = imageList[position]
        holder.image.setImageURI(data.getImageUri)
        holder.imageName.text = data.name
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageName: TextView = view.image_list_text_view
        var image: ImageView = view.image_list_image_view
    }
}