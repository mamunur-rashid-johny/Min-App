package com.example.testminapp.ui.dashboard.editphoto

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testminapp.R
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions


const val PERMISSION_CODE = 102
private const val REQUEST_CAMERA = 1
private const val SELECT_FILE = 2

class EditActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        //show image selection tools
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        } else {
            requestPermission()
        }
    }

    private fun hasDevicePermission(): Boolean {
        return EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)
    }

    private fun requestPermission() {
        EasyPermissions.requestPermissions(
            this, "Camera permission needed to capture photo.",
            com.example.testminapp.ui.dashboard.addphoto.PERMISSION_CODE, Manifest.permission.CAMERA
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }
}