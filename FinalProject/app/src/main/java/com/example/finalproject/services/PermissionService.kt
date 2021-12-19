package com.example.finalproject.services

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.finalproject.configs.Constants

class PermissionService(private val activity: Activity) {
    fun checkPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(
            activity,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        return result == PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission() {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            Constants.PERMISSION_REQUEST_CODE
        )
    }

//    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>?, grantResults: IntArray) {
//        when (requestCode) {
//            Constants.PERMISSION_REQUEST_CODE -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                startImageDownload()
//            } else {
//                Toast.makeText(context, "Нет доступа к хранилищу", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        }
//    }
}