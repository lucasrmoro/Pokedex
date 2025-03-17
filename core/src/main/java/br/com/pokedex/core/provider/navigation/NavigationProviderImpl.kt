package br.com.pokedex.core.provider.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import br.com.pokedex.core.R
import br.com.pokedex.core.ext.ONE
import timber.log.Timber

internal class NavigationProviderImpl(
    private val fragmentActivity: FragmentActivity
) : NavigationProvider {

    private var _onCurrentFragmentChangeListener: ((currentFragment: Fragment?, isNotLastFragment: Boolean) -> Unit)? =
        null
    private var oldBackStackEntryCount = Int.ONE

    private val fragmentManager: FragmentManager
        get() = fragmentActivity.supportFragmentManager

    override val lastFragment: Fragment?
        get() = fragmentManager.fragments.lastOrNull()

    override val isLastFragment: Boolean
        get() = fragmentManager.backStackEntryCount == Int.ONE

    override fun navigate(
        fragment: Fragment,
        backStack: String,
        containerId: Int,
        clearBackStack: Boolean,
        clearTop: Boolean
    ) {
        when {
            clearBackStack -> clearBackStack(backStack)
            clearTop -> clearTop(fragment)
        }
        addFragment(containerId, fragment, backStack)
    }

    override fun popBackStack() {
        fragmentManager.popBackStack()
    }

    override fun setOnCurrentFragmentChangeListener(block: (currentFragment: Fragment?, isNotLastFragment: Boolean) -> Unit) {
        _onCurrentFragmentChangeListener = block
        setupOnBackStackChangedListener()
    }

    private fun addFragment(@IdRes containerId: Int, fragment: Fragment, backStack: String) =
        with(fragmentManager) {
            if (fragment.isAdded) return@with
            with(beginTransaction()) {
                setCustomAnimations(
                    R.anim.slide_in_from_right,
                    R.anim.slide_out_to_left,
                    R.anim.slide_in_from_left,
                    R.anim.slide_out_to_right
                )
                add(containerId, fragment)
                addToBackStack(backStack)
                commitSafe(transaction = this)
            }
        }

    private fun commitSafe(transaction: FragmentTransaction?) = with(fragmentManager) {
        if (isDestroyed.not() && isStateSaved.not()) {
            transaction?.commit()
        } else {
            transaction?.commitAllowingStateLoss()
        }
    }

    private fun clearBackStack(backStack: String) = with(fragmentManager) {
        popBackStack(backStack, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        runPendingTransactions()
    }

    private fun clearTop(fragment: Fragment) = with(fragmentManager) {
        fragments.find { it.javaClass.name == fragment.javaClass.name }?.let {
            do {
                popBackStack()
                runPendingTransactions()
            } while (fragments.last() != it)
        }
    }

    private fun runPendingTransactions() {
        runCatching {
            fragmentManager.executePendingTransactions()
        }.onFailure {
            Timber.e(it)
        }
    }

    private fun setupOnBackStackChangedListener() = with(fragmentManager) {
        addOnBackStackChangedListener {
            Timber.d("AddOnBackStackChangedListener - Android")
            fragmentManager.fragments.lastOrNull()?.let { lastFrag ->
                oldBackStackEntryCount.also { oldCount ->
                    oldBackStackEntryCount = backStackEntryCount
                    _onCurrentFragmentChangeListener?.invoke(
                        lastFrag.takeIf { backStackEntryCount != oldCount },
                        isLastFragment.not()
                    )
                }
            }
        }
    }
}