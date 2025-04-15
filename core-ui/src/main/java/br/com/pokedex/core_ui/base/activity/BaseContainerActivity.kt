package br.com.pokedex.core_ui.base.activity

import androidx.appcompat.widget.Toolbar
import br.com.pokedex.core.base.viewModel.BaseViewModel
import br.com.pokedex.core_ui.R
import br.com.pokedex.core_ui.databinding.ActivityBaseContainerBinding
import br.com.pokedex.core_ui.ext.hide
import br.com.pokedex.core_ui.ext.show
import br.com.pokedex.core_ui.ext.viewBinding

abstract class BaseContainerActivity<VM : BaseViewModel> : ContainerHostActivity<VM>() {

    override val binding by viewBinding(ActivityBaseContainerBinding::inflate)

    override fun containerId(): Int = R.id.flContainer

    override fun showScreenLoading() = binding.loading.show()

    override fun dismissScreenLoading() = binding.loading.hide()

    override fun toolbar(): Toolbar = binding.toolbar.androidToolbar

}