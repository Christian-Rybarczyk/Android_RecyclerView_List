package com.rybarstudios.favoritepicturesgallery.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.rybarstudios.favoritepicturesgallery.R
import com.rybarstudios.favoritepicturesgallery.model.ImageData
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private var data: ImageData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val intent = intent
        data = intent.getSerializableExtra("object") as ImageData
        val context = this

        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        image.setOnClickListener {
            val intent = Intent(context, FullscreenActivity::class.java)
            intent.putExtra("image", data?.fileUriString)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        image.setImageURI(data?.fileUri)
        text_name.text = data?.name
        edit_name.setText(data?.name)
        text_uri.text = data?.fileUri.toString()
        text_description.text = data?.description
        edit_description.setText(data?.description)
    }

    override fun onBackPressed() {
        data?.name = edit_name.text.toString()
        data?.description = edit_description.text.toString()
        val resultIntent = Intent()
        resultIntent.putExtra("object", data)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}
