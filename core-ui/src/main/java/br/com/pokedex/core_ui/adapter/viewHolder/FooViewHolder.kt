package br.com.pokedex.core_ui.adapter.viewHolder

import br.com.pokedex.core_ui.adapter.base.BaseViewHolder
import br.com.pokedex.core_ui.adapter.callbacks.FooAdapterCallbacks
import br.com.pokedex.core_ui.adapter.model.Foo
import br.com.pokedex.core_ui.databinding.RvFooItemBinding

internal class FooViewHolder(
    binding: RvFooItemBinding,
    private val callbacks: FooAdapterCallbacks?
) : BaseViewHolder<Foo, RvFooItemBinding>(binding) {

    override fun onBind(item: Foo) = bind {
        root.setOnClickListener {
            callbacks?.onFooClicked(item)
        }
        tvMsg.text = item.msg
    }

}