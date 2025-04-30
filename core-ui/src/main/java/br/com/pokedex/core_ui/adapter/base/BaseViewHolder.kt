package br.com.pokedex.core_ui.adapter.base

import android.content.Context
import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import br.com.pokedex.domain.adapter.AdapterItem

abstract class BaseViewHolder<T : AdapterItem, VB : ViewBinding>(private val binding: VB) :
    RecyclerView.ViewHolder(binding.root) {

    protected val context: Context
        get() = binding.root.context

    protected val resources: Resources
        get() = context.resources

    abstract fun onBind(item: T)

    protected fun bind(block: VB.() -> Unit) {
        block(binding)
    }

}