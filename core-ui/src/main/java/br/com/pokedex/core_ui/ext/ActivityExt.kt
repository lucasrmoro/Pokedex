package br.com.pokedex.core_ui.ext

import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Build
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
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

fun FragmentActivity.setMenu(
    @MenuRes menuRes: Int,
    lifecycleOwner: LifecycleOwner,
    onMenuItemClicked: (MenuItem) -> Boolean
) {
    addMenuProvider(
        object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(menuRes, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean =
                onMenuItemClicked(menuItem)
        },
        lifecycleOwner,
        Lifecycle.State.RESUMED
    )
}