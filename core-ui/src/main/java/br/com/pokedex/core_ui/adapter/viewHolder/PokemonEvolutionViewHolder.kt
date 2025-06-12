package br.com.pokedex.core_ui.adapter.viewHolder

import android.widget.ImageView
import androidx.core.view.isGone
import br.com.pokedex.core_ui.adapter.base.BaseViewHolder
import br.com.pokedex.core_ui.databinding.RvPokemonEvolutionItemBinding
import br.com.pokedex.core_ui.ext.hide
import br.com.pokedex.core_ui.ext.onComplete
import br.com.pokedex.core_ui.ext.show
import br.com.pokedex.pokemons.model.PokemonEvolutionItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class PokemonEvolutionViewHolder(binding: RvPokemonEvolutionItemBinding) :
    BaseViewHolder<PokemonEvolutionItem, RvPokemonEvolutionItemBinding>(binding) {

    fun onBind(item: PokemonEvolutionItem, isLastItem: Boolean) = bind {
        loadImageInto(
            imageView = ivFrom,
            imageUrl = item.fromImgUrl,
            onStart = loadingFrom::show,
            onFinish = loadingFrom::hide
        )
        loadImageInto(
            imageView = ivTo,
            imageUrl = item.toImgUrl,
            onStart = loadingTo::show,
            onFinish = loadingTo::hide
        )
        tvFrom.text = item.from
        tvTo.text = item.to
        tvEvolution.text = item.evolutionNecessity
        tvEvolution.setTextColor(getColor(item.mainPokemonColor))
        vDivider.isGone = isLastItem
    }

    private fun loadImageInto(
        imageView: ImageView,
        imageUrl: String,
        onStart: () -> Unit,
        onFinish: () -> Unit
    ) {
        if (imageView.drawable != null) return

        onStart()
        Glide.with(context)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .onComplete {
                onFinish()
                imageView.setImageDrawable(it)
            }
            .into(imageView)
    }

}