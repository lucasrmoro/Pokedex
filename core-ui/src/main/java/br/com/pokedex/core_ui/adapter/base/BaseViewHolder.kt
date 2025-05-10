package br.com.pokedex.core_ui.adapter.base

import android.content.Context
import android.content.res.Resources
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import br.com.pokedex.domain.adapter.AdapterItem

abstract class BaseViewHolder<T : AdapterItem, VB : ViewBinding>(private val binding: VB) :
    RecyclerView.ViewHolder(binding.root) {

    protected val context: Context
        get() = binding.root.context

    protected val resources: Resources
        get() = context.resources

    open fun onBind(item: T) {}

    protected fun bind(block: VB.() -> Unit) {
        block(binding)
    }

    protected fun getString(@StringRes stringRes: Int, vararg args: Any) =
        resources.getString(stringRes, *args)

    protected fun getColor(@ColorRes colorRes: Int) = context.getColor(colorRes)

}