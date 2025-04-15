package br.com.pokedex.core.ext

import br.com.pokedex.core.base.activity.BaseActivity
import br.com.pokedex.core.base.viewModel.BaseViewModel
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
internal fun <VM : BaseViewModel> BaseActivity<VM>.getViewModelClass(): KClass<VM> =
    ((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.first() as Class<VM>).kotlin