package br.com.pokedex.core_ui.ext

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import br.com.pokedex.core.ext.openActivity
import br.com.pokedex.core.ext.showToast
import br.com.pokedex.core_ui.base.fragment.BaseFragment

val BaseFragment<*, *>.isFragmentVisible
    get() = parentFragmentManager.fragments.filterIsInstance<BaseFragment<*, *>>().javaClass == javaClass

fun BaseFragment<*, *>.showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
    context.showToast(message, length)
}

fun BaseFragment<*, *>.showToast(@StringRes message: Int, length: Int = Toast.LENGTH_SHORT) {
    context.showToast(message, length)
}

inline fun <reified T : AppCompatActivity> BaseFragment<*, *>.openActivity(
    addNewTaskFlag: Boolean = false,
    args: Bundle.() -> Unit = { }
) {
    context?.openActivity<T>(addNewTaskFlag = addNewTaskFlag, args = args)
}

fun Fragment.setMenu(
    @MenuRes menuRes: Int,
    lifecycleOwner: LifecycleOwner,
    onMenuItemClicked: (MenuItem) -> Boolean
) {
    activity?.setMenu(menuRes, lifecycleOwner, onMenuItemClicked)
}

fun Fragment.intArg(key: String): Lazy<Int?> = lazy {
    arguments?.getInt(key)
}

fun <T : Fragment> T.putArgs(args: Bundle.() -> Unit): T = this.apply {
    arguments = Bundle().apply(args)
}