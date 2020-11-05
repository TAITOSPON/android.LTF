package com.toat.ltf.Tool

import android.Manifest
import android.app.Activity
import android.content.Context
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class CheckPermission(context: Context ,checkPermissioncallback:CheckPermissionCallback ) {

    private var checkPermissioncallback = checkPermissioncallback

    interface CheckPermissionCallback{

        fun OnSuccessCallback()

        fun OnErrorCallback()
    }

    private var context: Context? = context

    // Check Permission //
    fun requestCameraPermission() {
        Dexter.withActivity(context as Activity)
            .withPermissions(
                Manifest.permission.CAMERA
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    // check if all permissions are granted

                    if (report.areAllPermissionsGranted()) {
                        checkPermissioncallback.OnSuccessCallback()
                    }else{
                        checkPermissioncallback.OnErrorCallback()
                    }

                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).withErrorListener { }
            .onSameThread()
            .check()
    }
}