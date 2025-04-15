package br.com.pokedex.core_ui.base.fragment

import android.content.res.Resources.NotFoundException
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import br.com.pokedex.core.base.viewModel.BaseViewModel
import br.com.pokedex.core_ui.R
import br.com.pokedex.core_ui.base.activity.BaseContainerActivity
import br.com.pokedex.core_ui.base.activity.BaseNavDrawerContainerActivity
import br.com.pokedex.core_ui.ext.getViewModelClass
import br.com.pokedex.core_ui.ext.setHomeAsUpIndicator
import com.google.android.material.navigation.NavigationView
import org.koin.androidx.viewmodel.ext.android.viewModelForClass
import br.com.pokedex.core.R as coreR

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(
    private val inflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {

    private val baseContainerActivity: BaseContainerActivity<*>?
        get() = activity as? BaseContainerActivity<*>
    private val baseNavDrawerContainerActivity: BaseNavDrawerContainerActivity<*>?
        get() = activity as? BaseNavDrawerContainerActivity<*>

    private var _binding: VB? = null
    protected val binding
        get() = _binding ?: throw NotFoundException("ViewBinding not found")

    protected val viewModel by viewModelForClass(getViewModelClass())
    protected val defaultErrorMessage
        get() = getString(coreR.string.an_error_occurred_try_again_later)
    protected val activityNavigationView: NavigationView?
        get() = baseNavDrawerContainerActivity?.navigationView
    protected val activityActionBar: ActionBar?
        get() = baseContainerActivity?.supportActionBar

    open val showBackButton: Boolean = true
    protected abstract fun setupViews()
    protected open fun setupListeners() {}
    protected open fun setupObservers() {}
    open fun onHomeMenuItemClicked(): Boolean = false

    @StringRes
    protected open fun toolbarTitle(): Int? = null

    @CallSuper
    open fun onFragmentVisible() {
        activityActionBar?.apply {
            title = toolbarTitle()?.let(::getString).orEmpty()
            if (showBackButton) {
                setDisplayShowHomeEnabled(true)
                setHomeAsUpIndicator(context, R.drawable.ic_back_arrow)
            }
        }
    }

    // Return true to use your custom impl otherwise false for default
    open fun onBackPressed(): Boolean = false

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflater(inflater, container, false)
        binding.root.setBackgroundColor(requireContext().getColor(R.color.dark_75))
        return binding.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    @CallSuper
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    protected fun navigate(
        fragment: Fragment,
        clearBackStack: Boolean = false,
        clearTop: Boolean = false
    ) {
        baseContainerActivity?.navigate(fragment, clearBackStack, clearTop)
    }

    protected fun popBackStack() {
        parentFragmentManager.popBackStack()
    }

    protected fun showScreenLoading() {
        baseContainerActivity?.showScreenLoading()
    }

    protected fun dismissScreenLoading() {
        baseContainerActivity?.dismissScreenLoading()
    }

    protected fun showNavDrawer() {
        baseNavDrawerContainerActivity?.drawerLayout?.openDrawer(GravityCompat.START)
    }

    protected fun closeNavDrawer() {
        baseNavDrawerContainerActivity?.drawerLayout?.closeDrawer(GravityCompat.START)
    }
}