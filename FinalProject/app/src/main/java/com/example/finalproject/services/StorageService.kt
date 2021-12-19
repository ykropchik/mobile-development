package com.example.finalproject.services

import android.os.Environment
import com.example.finalproject.configs.Constants
import java.io.File
import java.net.URI


class StorageService {
    fun getDownloadedIdList(): ArrayList<String> {
        val result = arrayListOf<String>()
        val appStore = File(Environment.getExternalStorageDirectory(), Constants.APP_STORE_DIR_NAME)
        for (file in appStore.listFiles()!!) {
            if (file.isFile) {
                result.add(file.nameWithoutExtension)
            }
        }

        return result
    }

    fun getDownloadedPhotosList(): ArrayList<URI> {
        val result = arrayListOf<URI>()
        val appStore = File(Environment.getExternalStorageDirectory(), Constants.APP_STORE_DIR_NAME)
        for (file in appStore.listFiles()!!) {
            if (file.isFile) {
                result.add(URI.create(file.absolutePath))
            }
        }

        return result
    }
}