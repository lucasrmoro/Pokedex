package br.com.pokedex.core_ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.pokedex.core_ui.delegate.EditTextDelegate
import br.com.pokedex.core_ui.delegate.EditTextHintDelegate
import br.com.pokedex.core_ui.delegate.EditTextInputTypeDelegate
import br.com.pokedex.core_ui.delegate.EditTextMaxLengthDelegate
import br.com.pokedex.core_ui.delegate.TextViewDelegate
import br.com.pokedex.core_ui.delegate.ViewDelegate
import br.com.pokedex.core.ext.getCustomAttributes
import br.com.pokedex.core_ui.R
import br.com.pokedex.core_ui.databinding.PokedexTextFieldBinding

class PokedexTextField @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val binding =
        PokedexTextFieldBinding.inflate(LayoutInflater.from(context), this, true)

    private val tvHeader by ViewDelegate(binding.tvHeader)
    private val textInputLayout by ViewDelegate(binding.textInputLayout)
    private val editText by ViewDelegate(binding.editText)

    var text by EditTextDelegate(editText)
    var header by TextViewDelegate(tvHeader)
    var hint by EditTextHintDelegate(editText)
    var maxLength by EditTextMaxLengthDelegate(editText)
    var inputType by EditTextInputTypeDelegate(editText)

    init {
        setupViews(attrs)
    }

    private fun setupViews(attrs: AttributeSet?) {
        attrs?.let {
            context.getCustomAttributes(it, R.styleable.PokedexTextField) {
                inputType = getInt(
                    R.styleable.PokedexTextField_inputType,
                    EditTextInputTypeDelegate.NONE
                )
                header = getString(R.styleable.PokedexTextField_header)
                hint = getString(R.styleable.PokedexTextField_android_hint)
                maxLength = getInt(R.styleable.PokedexTextField_android_maxLength, Int.MAX_VALUE)
            }
        }
    }

}