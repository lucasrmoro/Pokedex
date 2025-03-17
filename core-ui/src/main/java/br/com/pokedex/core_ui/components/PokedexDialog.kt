package br.com.pokedex.core_ui.components

import android.content.Context
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import br.com.pokedex.core.base.dialog.BaseAlertDialog
import br.com.pokedex.core_ui.ext.show
import br.com.pokedex.core_ui.ext.viewBinding
import br.com.pokedex.core_ui.databinding.PokedexDialogBinding

class PokedexDialog(
    context: Context,
    private val title: String?,
    private val bodyMessage: String?,
    @DrawableRes private val image: Int?,
    private val primaryButtonText: String?,
    private val secondaryButtonText: String?,
    private val onPrimaryButtonClick: (() -> Unit)?,
    private val onSecondaryButtonClick: (() -> Unit)?,
    private val onDismissListener: (() -> Unit)?,
    private val isDismissible: Boolean
) : BaseAlertDialog(context) {

    override val binding by viewBinding(PokedexDialogBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding) {
            setCancelable(isDismissible)
            title?.let { tvTitle.text = it }
            bodyMessage?.let { tvBodyMessage.text = it }
            image?.let(ivImage::setImageResource)

            primaryButtonText?.let {
                btnPrimary.text = it
                btnPrimary.setOnClickListener {
                    onPrimaryButtonClick?.invoke()
                }
                btnPrimary.show()
            }

            secondaryButtonText?.let {
                btnSecondary.text = it
                btnSecondary.setOnClickListener {
                    onSecondaryButtonClick?.invoke()
                }
                btnSecondary.show()
            }

            setOnDismissListener {
                onDismissListener?.invoke()
            }

            show()
        }
    }

    class Builder(private val context: Context) {
        private var title: String? = null
        private var bodyMessage: String? = null

        @DrawableRes
        private var image: Int? = null
        private var primaryButtonText: String? = null
        private var secondaryButtonText: String? = null
        private var onPrimaryButtonClick: (() -> Unit)? = null
        private var onSecondaryButtonClick: (() -> Unit)? = null
        private var onDismissListener: (() -> Unit)? = null
        private var isDismissible: Boolean = true

        fun build() = PokedexDialog(
            context,
            title,
            bodyMessage,
            image,
            primaryButtonText,
            secondaryButtonText,
            onPrimaryButtonClick,
            onSecondaryButtonClick,
            onDismissListener,
            isDismissible
        ).create()

        fun setTitle(title: String) = this.also {
            this.title = title
        }

        fun setTitle(@StringRes title: Int) = setTitle(context.getString(title))

        fun setBodyMessage(bodyMessage: String) = this.also {
            this.bodyMessage = bodyMessage
        }

        fun setBodyMessage(@StringRes bodyMessage: Int) =
            setBodyMessage(context.getString(bodyMessage))

        fun setImage(@DrawableRes image: Int) = this.also {
            this.image = image
        }

        fun setPrimaryButtonText(primaryButtonText: String) = this.also {
            this.primaryButtonText = primaryButtonText
        }

        fun setPrimaryButtonText(@StringRes primaryButtonText: Int) =
            setPrimaryButtonText(context.getString(primaryButtonText))

        fun setSecondaryButtonText(secondaryButtonText: String) = this.also {
            this.secondaryButtonText = secondaryButtonText
        }

        fun setSecondaryButtonText(@StringRes secondaryButtonText: Int) =
            setSecondaryButtonText(context.getString(secondaryButtonText))

        fun setOnPrimaryButtonClickListener(onClick: () -> Unit) = this.also {
            onPrimaryButtonClick = onClick
        }

        fun setOnSecondaryButtonClickListener(onClick: () -> Unit) = this.also {
            onSecondaryButtonClick = onClick
        }

        fun setOnDismissListener(block: () -> Unit) = this.also {
            onDismissListener = block
        }

        fun setDismissible(isDismissible: Boolean) = this.also {
            this.isDismissible = isDismissible
        }
    }
}