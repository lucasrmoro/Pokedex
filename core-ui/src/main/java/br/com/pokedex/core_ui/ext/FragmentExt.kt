package br.com.pokedex.core_ui.ext

import android.content.Intent
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import br.com.pokedex.core.ext.showToast
import br.com.pokedex.core.base.activity.BaseActivity
import br.com.pokedex.core_ui.base.fragment.BaseFragment
import kotlin.reflect.KClass

val BaseFragment<*, *>.isFragmentVisible
    get() = parentFragmentManager.fragments.filterIsInstance<BaseFragment<*, *>>().javaClass == javaClass

fun BaseFragment<*, *>.showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
    context.showToast(message, length)
}

fun BaseFragment<*, *>.showToast(@StringRes message: Int, length: Int = Toast.LENGTH_SHORT) {
    context.showToast(message, length)
}

fun <T : KClass<out BaseActivity<*>>> BaseFragment<*, *>.openActivity(activityKClass: T) {
    Intent(context, activityKClass.java).also { startActivity(it) }
}

fun Fragment.setMenu(
    @MenuRes menuRes: Int,
    lifecycleOwner: LifecycleOwner,
    onMenuItemClicked: (MenuItem) -> Boolean
) {
    activity?.setMenu(menuRes, lifecycleOwner, onMenuItemClicked)
}