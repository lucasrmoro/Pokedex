package br.com.pokedex.core_ui.base.activity

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import br.com.pokedex.core.base.viewModel.BaseViewModel
import br.com.pokedex.core_ui.R
import br.com.pokedex.core_ui.databinding.ActivityBaseNavDrawerContainerBinding
import br.com.pokedex.core_ui.ext.hide
import br.com.pokedex.core_ui.ext.setHomeAsUpIndicator
import br.com.pokedex.core_ui.ext.show
import br.com.pokedex.core_ui.ext.viewBinding
import com.google.android.material.navigation.NavigationView

abstract class BaseNavDrawerContainerActivity<VM : BaseViewModel> : ContainerHostActivity<VM>() {

    override val binding by viewBinding(ActivityBaseNavDrawerContainerBinding::inflate)

    val drawerLayout: DrawerLayout
        get() = binding.root
    val navigationView: NavigationView
        get() = binding.navigationView

    override fun containerId(): Int = R.id.flContainer

    override fun showScreenLoading() = binding.loading.show()

    override fun dismissScreenLoading() = binding.loading.hide()

    override fun toolbar(): Toolbar = binding.toolbar.androidToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(supportActionBar) {
            this?.setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(this@BaseNavDrawerContainerActivity, R.drawable.ic_hamburger)
        }
    }
}