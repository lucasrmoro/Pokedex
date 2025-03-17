package br.com.pokedex.core.provider.permission

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

internal class PermissionsProviderImpl(private val activity: Activity?) : PermissionsProvider {

    private var permissionsRequested = emptyList<String>()
    private var listener: (hasGranted: Boolean) -> Unit = {}

    override fun requestCameraPermission(block: (hasGranted: Boolean) -> Unit) {
        requestPermissions(block, CAMERA_PERMISSION)
    }

    override fun requestFilePermission(block: (hasGranted: Boolean) -> Unit) {
        requestPermissions(block, *WRITE_AND_READ_PERMISSION.toTypedArray())
    }

    @RequiresApi(VERSION_CODES.TIRAMISU)
    override fun requestNotificationsPermission(block: (hasGranted: Boolean) -> Unit) {
        requestPermissions(block, NOTIFICATIONS_PERMISSION)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: List<String>,
        grantResults: IntArray
    ) {
        val anyPermissionUngranted =
            permissions.any(::isUngranted) || grantResults.any(::isUngranted)

        when {
            anyPermissionUngranted && shouldShowPermissionsRationale().not() ->
                listener.invoke(false)

            else -> listener.invoke(true)
        }
    }

    private fun requestPermissions(
        listener: (hasGranted: Boolean) -> Unit,
        vararg permission: String
    ) {
        permissionsRequested = permission.toList()
        this.listener = listener

        when {
            permissionsRequested.none(::isUngranted) -> listener(true)
            else -> activity?.let {
                ActivityCompat.requestPermissions(
                    it,
                    permissionsRequested.ungrantedPermissions(),
                    PermissionsProvider.REQUEST_CODE
                )
            }
        }
    }

    private fun List<String>.ungrantedPermissions() = filter(::isUngranted).toTypedArray()

    private fun shouldShowPermissionsRationale() =
        permissionsRequested.any(::showPermissionRationale)

    private fun isUngranted(permission: String) = activity?.let {
        isUngranted(ContextCompat.checkSelfPermission(it, permission))
    } ?: true

    private fun isUngranted(result: Int) = result != PackageManager.PERMISSION_GRANTED

    private fun showPermissionRationale(permission: String) = activity?.let {
        ActivityCompat.shouldShowRequestPermissionRationale(it, permission)
    } ?: false

    companion object {
        private const val CAMERA_PERMISSION = Manifest.permission.CAMERA
        private const val FILE_WRITE_PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE
        private const val FILE_READ_PERMISSION = Manifest.permission.READ_EXTERNAL_STORAGE
        private val WRITE_AND_READ_PERMISSION = listOf(
            FILE_READ_PERMISSION,
            FILE_WRITE_PERMISSION
        ).takeIf { VERSION.SDK_INT <= VERSION_CODES.Q }.orEmpty()

        @RequiresApi(VERSION_CODES.TIRAMISU)
        private const val NOTIFICATIONS_PERMISSION = Manifest.permission.POST_NOTIFICATIONS
    }
}