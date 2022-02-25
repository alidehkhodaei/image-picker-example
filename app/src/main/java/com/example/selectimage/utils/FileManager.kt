package com.example.selectimage.utils

import android.app.Activity
import android.content.Context
import android.os.Environment
import com.example.selectimage.utils.Constant.PREFIX
import com.example.selectimage.utils.Constant.SUFFIX
import java.io.File

object FileManager {
     fun createImageFile(context: Context): File {
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(PREFIX, SUFFIX, storageDir)
    }
}