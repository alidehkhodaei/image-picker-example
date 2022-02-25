package com.example.selectimage.utils

import android.app.Activity
import android.os.Environment
import com.example.selectimage.utils.Constant.PREFIX
import com.example.selectimage.utils.Constant.SUFFIX
import java.io.File

object FileManager {
     fun createImageFile(activity: Activity): File {
        val storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(PREFIX, SUFFIX, storageDir)
    }
}