package br.com.pokedex.core_ui.base.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.addCallback
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import br.com.pokedex.core.base.viewModel.BaseViewModel
import br.com.pokedex.core.provider.navigation.NavigationProvider
import br.com.pokedex.core_ui.R
import br.com.pokedex.core_ui.base.fragment.BaseFragment
import br.com.pokedex.core_ui.databinding.ActivityBaseContainerBinding
import br.com.pokedex.core_ui.ext.hide
import br.com.pokedex.core_ui.ext.show
import br.com.pokedex.core_ui.ext.viewBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

abstract class BaseContainerActivity<VM : BaseViewModel> : BaseActivity<VM>() {

    override val binding by viewBinding(ActivityBaseContainerBinding::inflate)
    private val navigationProvider by inject<NavigationProvider> {
        parametersOf(this)
    }
    private val lastFragment: BaseFragment<*, *>?
        get() = navigationProvider.lastFragment as? BaseFragment<*, *>

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
            onBackPressedDispatcher.onBackPressed()
            true
        }

        else -> super.onOptionsItemSelected(item)
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

    fun showScreenLoading() {
        binding.pbLoading.show()
    }

    fun dismissScreenLoading() {
        binding.pbLoading.hide()
    }

    private fun setupScreen() = with(binding) {
        setSupportActionBar(toolbar.androidToolbar)
        setupActionBar()
    }

    private fun setupActionBar() = supportActionBar?.let {
        it.title = null
        it.setHomeAsUpIndicator(AppCompatResources.getDrawable(this, R.drawable.ic_back_arrow))
    }

    private fun setupOnBackStackChangedListener() = with(navigationProvider) {
        setOnCurrentFragmentChangeListener { currentFragment, isNotLastFragment ->
            if (currentFragment is BaseFragment<*, *>) currentFragment.onFragmentVisible()
            supportActionBar?.setDisplayHomeAsUpEnabled(isNotLastFragment || isTaskRoot.not())
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