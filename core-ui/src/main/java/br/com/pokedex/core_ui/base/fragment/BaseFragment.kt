package br.com.pokedex.core_ui.base.fragment

import android.content.res.Resources.NotFoundException
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import br.com.pokedex.core.R
import br.com.pokedex.core.base.viewModel.BaseViewModel
import br.com.pokedex.core_ui.base.activity.BaseContainerActivity
import br.com.pokedex.core_ui.ext.getViewModelClass
import org.koin.androidx.viewmodel.ext.android.viewModelForClass
import br.com.pokedex.core_ui.R as coreUiR

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(
    private val inflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {

    private val baseContainerActivity: BaseContainerActivity<*>?
        get() = activity as? BaseContainerActivity<*>

    private var _binding: VB? = null
    protected val binding
        get() = _binding ?: throw NotFoundException("ViewBinding not found")

    protected val viewModel by viewModelForClass(getViewModelClass())
    protected val defaultErrorMessage
        get() = getString(R.string.an_error_occurred_try_again_later)

    protected abstract fun setupViews()
    protected open fun setupListeners() {}
    protected open fun setupObservers() {}
    open fun onFragmentVisible() {}

    // Return true to use your custom impl otherwise false for default
    open fun onBackPressed(): Boolean = false

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflater(inflater, container, false)
        binding.root.setBackgroundColor(requireContext().getColor(coreUiR.color.dark_75))
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
}