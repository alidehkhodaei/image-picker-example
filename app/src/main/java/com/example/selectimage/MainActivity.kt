package com.example.selectimage

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.selectimage.utils.Constant.ALL_TYPE_IMAGE
import com.example.selectimage.utils.Constant.AUTHORITY
import com.example.selectimage.utils.FileManager
import com.example.selectimage.utils.PermissionManager
import java.io.File

class MainActivity : AppCompatActivity() {
    private val image: ImageView by lazy {
        findViewById(R.id.image)
    }
    private val btnImageFromCamera: Button by lazy {
        findViewById(R.id.btn_capture_image)
    }
    private val btnImageFromGallery: Button by lazy {
        findViewById(R.id.btn_take_from_gallery)
    }
    private var tempImageUri: Uri? = null

    private lateinit var tempImageFile: File

    private val selectImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            image.setImageURI(it)
        }

    private val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success)
                image.setImageURI(tempImageUri)
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSelectCameraClicked()

        btnSelectGalleryClicked()

    }

    private fun btnSelectGalleryClicked() {
        btnImageFromGallery.setOnClickListener {
            PermissionManager.requestPermission(this) {
                selectImageLauncher.launch(ALL_TYPE_IMAGE)
            }
        }
    }

    private fun btnSelectCameraClicked() {
        btnImageFromCamera.setOnClickListener {
            PermissionManager.requestPermission(this) {
                tempImageUri = FileProvider.getUriForFile(
                    this,
                    AUTHORITY,
                    FileManager.createImageFile(this).also {
                        tempImageFile = it
                    })

                cameraLauncher.launch(tempImageUri)
            }
        }
    }

}