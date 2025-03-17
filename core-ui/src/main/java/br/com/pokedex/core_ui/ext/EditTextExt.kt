package br.com.pokedex.core_ui.ext

import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.EditText
import br.com.pokedex.core.ext.EMPTY
import br.com.pokedex.core.ext.MASK_SIGN
import br.com.pokedex.core.ext.ONE
import br.com.pokedex.core.ext.TWO
import br.com.pokedex.core.ext.ZERO
import br.com.pokedex.core.ext.isSign
import br.com.pokedex.core.ext.toMoney
import br.com.pokedex.core.ext.unmasked
import timber.log.Timber

fun EditText.setMaxLength(maxLength: Int) {
    val currentFilters = filters.filterNot { it.javaClass == InputFilter.LengthFilter::class.java }
    filters = currentFilters.plus(InputFilter.LengthFilter(maxLength)).toTypedArray()
}

fun EditText.onTextChange(
    shouldAddListener: Boolean = true,
    onBeforeTextChanged: (EditText.(String) -> Unit)? = null,
    onTextChanged: (EditText.(String) -> Unit)? = null,
    onAfterTextChanged: (EditText.(String) -> Unit)? = null
): TextWatcher = object : TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        onBeforeTextChanged?.invoke(this@onTextChange, s.toString())
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onTextChanged?.invoke(this@onTextChange, s.toString())
    }

    override fun afterTextChanged(s: Editable?) {
        onAfterTextChanged?.invoke(this@onTextChange, s.toString())
    }

}.also {
    if (shouldAddListener) addTextChangedListener(it)
}

fun EditText.setMoneyMask(fractionDigits: Int = Int.TWO): TextWatcher {
    var isUpdating = false

    return onTextChange(
        onTextChanged = { text ->
            if (isUpdating) {
                isUpdating = false
                return@onTextChange
            }
            runCatching {
                this@onTextChange.setText(text.toMoney(fractionDigits))
            }.onFailure {
                Timber.e(it)
            }
        }
    )
}

fun EditText.setMask(mask: String, shouldAddListener: Boolean = true): TextWatcher {
    var isUpdating = false
    var oldText = String.EMPTY

    return onTextChange(shouldAddListener = shouldAddListener) {
        val text = it.unmasked()
        if (isUpdating) {
            oldText = text
            isUpdating = false
        } else {
            isUpdating = true
            text.setMask(mask, oldText).updateText(text, oldText).also { maskedText ->
                setText(maskedText)
                setSelection(maskedText.length)
            }
        }
    }
}

fun String?.setMask(mask: String, oldText: String): String {
    if (this.isNullOrEmpty()) return String.EMPTY
    var maskedText = String.EMPTY
    var index = Int.ZERO

    for (element in mask) {
        if (element != Char.MASK_SIGN) {
            if (index == this.length && this.length < oldText.length) continue
            maskedText += element
            continue
        }
        maskedText += try {
            this[index]
        } catch (e: Exception) {
            break
        }
        index++
    }
    return maskedText
}

private fun String.updateText(newText: String, oldText: String): String {
    if (this.isEmpty()) return String.EMPTY

    var maskedText = this
    var lastChar = this[length.minus(Int.ONE)]
    var lastCharIsMaskSign = false

    while (lastChar.isSign() && newText.length == oldText.length) {
        maskedText = maskedText.substring(Int.ZERO, maskedText.length.minus(Int.ONE))
        lastChar = maskedText[maskedText.length.minus(Int.ONE)]
        lastCharIsMaskSign = true
    }
    if (maskedText.isNotEmpty() && lastCharIsMaskSign) {
        maskedText = maskedText.substring(Int.ZERO, maskedText.length.minus(Int.ONE))
    }

    return maskedText
}