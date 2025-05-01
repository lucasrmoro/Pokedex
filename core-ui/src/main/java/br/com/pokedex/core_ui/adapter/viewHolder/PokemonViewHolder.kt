package br.com.pokedex.core_ui.adapter.viewHolder

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import br.com.pokedex.core.ext.ONE
import br.com.pokedex.core_ui.R
import br.com.pokedex.core_ui.adapter.base.BaseViewHolder
import br.com.pokedex.core_ui.adapter.callbacks.PokemonsListAdapterCallbacks
import br.com.pokedex.core_ui.databinding.RvPokemonItemBinding
import br.com.pokedex.core_ui.ext.onComplete
import br.com.pokedex.core_ui.ext.palette
import br.com.pokedex.domain.adapter.model.pokemons.PokemonItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class PokemonViewHolder(
    binding: RvPokemonItemBinding,
    private val callbacks: PokemonsListAdapterCallbacks?
) : BaseViewHolder<PokemonItem, RvPokemonItemBinding>(binding) {

    private val defaultBackgroundColor = context.getColor(R.color.dark_450)

    override fun onBind(item: PokemonItem) = bind {
        root.setCardBackgroundColor(defaultBackgroundColor)
        clContent.setOnClickListener {
            callbacks?.onClick(item)
        }
        btnFavorite.setOnClickListener {
            callbacks?.onFavorite(item, !item.isFavorite)
        }
        btnFavorite.contentDescription =
            context.getString(if (item.isFavorite) R.string.to_unfavorite else R.string.to_favorite)
        btnFavorite.setImageResource(
            if (item.isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_outlined
        )
        tvIndex.text = item.index
        tvName.text = item.name
        Glide.with(context)
            .load(item.imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .onComplete(::setupColors)
            .into(ivImage)
    }

    private fun dismissLoading() = bind {
        pbLoading.hide()
    }

    private fun setupColors(drawable: Drawable?) = bind {
        drawable.palette {
            val defTextColor = context.getColor(R.color.white)
            (dominantSwatch?.bodyTextColor ?: defTextColor).also {
                tvIndex.setTextColor(it)
                btnFavorite.setColorFilter(it)
            }
            tvName.setTextColor(dominantSwatch?.titleTextColor ?: defTextColor)
            clContent.background = createGradientDrawable(
                darkColor = dominantSwatch?.rgb ?: defaultBackgroundColor,
                lightColor = lightMutedSwatch?.rgb ?: defaultBackgroundColor
            )
            dismissLoading()
        } ?: dismissLoading()
    }

    private fun createGradientDrawable(darkColor: Int, lightColor: Int): GradientDrawable =
        GradientDrawable(
            GradientDrawable.Orientation.TL_BR,
            intArrayOf(lightColor, darkColor)
        ).apply {
            gradientType = GradientDrawable.RADIAL_GRADIENT
            gradientRadius = BACKGROUND_GRADIENT_RADIUS
            setGradientCenter(Float.ONE, Float.ONE)
        }

    companion object {
        private const val BACKGROUND_GRADIENT_RADIUS = 700f
    }
}