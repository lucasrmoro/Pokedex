package br.com.pokedex.core_ui.ext

import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

fun <T> RequestBuilder<T>.onComplete(block: (T?) -> Unit) =
    listener(
        object : RequestListener<T> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<T>,
                isFirstResource: Boolean
            ): Boolean {
                block(null)
                return false
            }

            override fun onResourceReady(
                resource: T & Any,
                model: Any,
                target: Target<T>?,
                dataSource: DataSource,
                isFirstResource: Boolean
            ): Boolean {
                block(resource)
                return false
            }
        }
    )