package br.com.pokedex.core.provider.permission

import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi

interface PermissionsProvider {

    fun requestCameraPermission(block: (hasGranted: Boolean) -> Unit)
    fun requestFilePermission(block: (hasGranted: Boolean) -> Unit)
    @RequiresApi(VERSION_CODES.TIRAMISU)
    fun requestNotificationsPermission(block: (hasGranted: Boolean) -> Unit)
    fun onRequestPermissionsResult(
        requestCode: Int = REQUEST_CODE,
        permissions: List<String>,
        grantResults: IntArray
    )

    companion object {
        internal const val REQUEST_CODE = 1909
    }
}