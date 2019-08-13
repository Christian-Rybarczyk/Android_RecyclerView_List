package com.rybarstudios.favoritepicturesgallery.model

import android.net.Uri
import java.io.Serializable

class ImageData(val imageUri: Uri) : Serializable {
    var name: String? = null
    val imageUriString: String
    var description: String? = null

    val getImageUri: Uri
        get() = Uri.parse(imageUriString)

    init {
        imageUriString = imageUri.toString()
        name = imageUri.toString()
        description = ""
    }
}