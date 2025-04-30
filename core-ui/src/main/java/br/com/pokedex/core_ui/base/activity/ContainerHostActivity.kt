package br.com.pokedex.core_ui.base.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.addCallback
import androidx.annotation.CallSuper
import androidx.annotation.IdRes
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import br.com.pokedex.core.base.activity.BaseActivity
import br.com.pokedex.core.base.viewModel.BaseViewModel
import br.com.pokedex.core.provider.navigation.NavigationProvider
import br.com.pokedex.core_ui.base.fragment.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

abstract class ContainerHostActivity<VM : BaseViewModel> : BaseActivity<VM>() {

    private val navigationProvider by inject<NavigationProvider> {
        parametersOf(this)
    }
    private val lastFragment: BaseFragment<*, *>?
        get() = navigationProvider.lastFragment as? BaseFragment<*, *>?

    @IdRes
    abstract fun containerId(): Int
    abstract fun showScreenLoading()
    abstract fun dismissScreenLoading()
    abstract fun toolbar(): Toolbar

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupScreen()
        setupOnBackStackChangedListener()
        setupOnBackPressed()
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        lastFragment?.onFragmentVisible()
    }

    @CallSuper
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            if (lastFragment?.onHomeMenuItemClicked() == false) {
                onBackPressedDispatcher.onBackPressed()
            }
            true
        }

        else -> false
    }

    fun navigate(
        fragment: Fragment,
        clearBackStack: Boolean = false,
        clearTop: Boolean = false
    ) {
        val backStack = "${javaClass.canonicalName}BackStack"
        navigationProvider.navigate(
            fragment = fragment,
            backStack = backStack,
            containerId = containerId(),
            clearBackStack = clearBackStack,
            clearTop = clearTop
        )
    }

    private fun setupScreen() {
        setSupportActionBar(toolbar())
    }

    private fun setupOnBackStackChangedListener() = with(navigationProvider) {
        setOnCurrentFragmentChangeListener { currentFragment ->
            if (currentFragment is BaseFragment<*, *>) currentFragment.onFragmentVisible()
        }
    }

    private fun setupOnBackPressed() {
        onBackPressedDispatcher.addCallback(this) {
            when {
                lastFragment?.onActivityBackPressed() ?: false -> return@addCallback
                navigationProvider.isLastFragment.not() -> navigationProvider.popBackStack()
                else -> finish()
            }
        }
    }

}