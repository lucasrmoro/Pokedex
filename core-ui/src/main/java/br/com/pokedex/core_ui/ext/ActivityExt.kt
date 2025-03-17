package br.com.pokedex.core_ui.ext

import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Build
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline inflater: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.NONE) { inflater.invoke(layoutInflater) }

inline fun <T : ViewBinding> AlertDialog.viewBinding(
    crossinline inflater: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.NONE) { inflater.invoke(layoutInflater) }


val AppCompatActivity.activityInfo
    get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        packageManager.getActivityInfo(
            componentName,
            PackageManager.ComponentInfoFlags.of(PackageManager.GET_META_DATA.toLong())
        )
    } else {
        packageManager.getActivityInfo(componentName, PackageManager.GET_META_DATA)
    }