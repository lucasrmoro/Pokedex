package br.com.pokedex.core_ui.base.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.addCallback
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import br.com.pokedex.core.base.viewModel.BaseViewModel
import br.com.pokedex.core.provider.navigation.NavigationProvider
import br.com.pokedex.core_ui.base.fragment.BaseFragment
import br.com.pokedex.core_ui.components.PokedexToolbar
import br.com.pokedex.core_ui.databinding.ActivityBaseContainerBinding
import br.com.pokedex.core_ui.ext.hide
import br.com.pokedex.core_ui.ext.show
import br.com.pokedex.core_ui.ext.viewBinding
import com.google.android.material.navigation.NavigationView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

abstract class BaseContainerActivity<VM : BaseViewModel> : BaseActivity<VM>() {

    override val binding by viewBinding(ActivityBaseContainerBinding::inflate)
    private val navigationProvider by inject<NavigationProvider> {
        parametersOf(this)
    }
    private val lastFragment: BaseFragment<*, *>?
        get() = navigationProvider.lastFragment as? BaseFragment<*, *>
    val toolbar: PokedexToolbar
        get() = binding.toolbar
    val drawerLayout: DrawerLayout
        get() = binding.root
    val navigationView: NavigationView
        get() = binding.navigationView.also { it.show() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupScreen()
        setupOnBackStackChangedListener()
        setupOnBackPressed()
    }

    override fun onResume() {
        super.onResume()
        lastFragment?.onFragmentVisible()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            if (lastFragment?.onHomeMenuItemClicked() == false) {
                onBackPressedDispatcher.onBackPressed()
            }
            true
        }

        else -> false
    }

    fun navigate(fragment: Fragment, clearBackStack: Boolean = false, clearTop: Boolean = false) {
        val backStack = "${javaClass.canonicalName}BackStack"
        navigationProvider.navigate(
            fragment = fragment,
            backStack = backStack,
            containerId = binding.flContainer.id,
            clearBackStack = clearBackStack,
            clearTop = clearTop
        )
    }

    fun showScreenLoading() = with(binding.pbLoading) {
        show()
    }

    fun dismissScreenLoading() = with(binding.pbLoading) {
        hide()
    }

    private fun setupScreen() = with(binding) {
        setSupportActionBar(toolbar.androidToolbar)
    }

    private fun setupOnBackStackChangedListener() = with(navigationProvider) {
        setOnCurrentFragmentChangeListener { currentFragment ->
            if (currentFragment is BaseFragment<*, *>) currentFragment.onFragmentVisible()
        }
    }

    private fun setupOnBackPressed() {
        onBackPressedDispatcher.addCallback(this) {
            when {
                lastFragment?.onBackPressed() ?: false -> return@addCallback
                navigationProvider.isLastFragment.not() -> navigationProvider.popBackStack()
                else -> finish()
            }
        }
    }
}