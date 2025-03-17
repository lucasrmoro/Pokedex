package br.com.pokedex.core_ui.delegate

import android.text.InputType
import android.view.View
import android.widget.EditText
import androidx.annotation.IntDef
import br.com.pokedex.core_ui.delegate.EditTextInputTypeDelegate.Companion.EditTextInputType
import br.com.pokedex.core.ext.CELLPHONE_MASK
import br.com.pokedex.core.ext.DATE_MASK
import br.com.pokedex.core.ext.ONE
import br.com.pokedex.core.ext.orZero
import br.com.pokedex.core_ui.ext.setMask
import br.com.pokedex.core_ui.ext.setMaxLength
import br.com.pokedex.core_ui.ext.setMoneyMask
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class EditTextDelegate(private val editText: EditText) : ReadWriteProperty<View, String?> {

    override fun getValue(thisRef: View, property: KProperty<*>): String? =
        editText.text?.toString()

    override fun setValue(thisRef: View, property: KProperty<*>, value: String?) {
        editText.setText(value)
    }

}

class EditTextHintDelegate(private val editText: EditText) :
    ReadWriteProperty<View, CharSequence?> {

    override fun getValue(thisRef: View, property: KProperty<*>): CharSequence? =
        editText.hint

    override fun setValue(thisRef: View, property: KProperty<*>, value: CharSequence?) {
        editText.hint = value
    }

}

/**
 * Returns [-1] if not set.
 */
class EditTextMaxLengthDelegate(private val editText: EditText) : ReadWriteProperty<View, Int?> {

    private var maxLength: Int? = null

    override fun getValue(thisRef: View, property: KProperty<*>): Int? =
        maxLength

    override fun setValue(thisRef: View, property: KProperty<*>, value: Int?) {
        maxLength = if (value == null || value < Int.ONE) Int.MAX_VALUE else value
        editText.setMaxLength(maxLength.orZero())
    }

}

class EditTextInputTypeDelegate(private val editText: EditText) :
    ReadWriteProperty<View, @EditTextInputType Int> {

    private var inputType = NONE

    override fun getValue(thisRef: View, property: KProperty<*>): Int = inputType

    override fun setValue(thisRef: View, property: KProperty<*>, value: Int) {
        with(editText) {
            inputType = InputType.TYPE_CLASS_NUMBER
            if (value == MONEY) {
                setMoneyMask()
                return@with
            }

            when (value) {
                PHONE -> String.CELLPHONE_MASK
                DATE -> String.DATE_MASK
                else -> null
            }?.let {
                setMask(it)
                return@with
            }

            inputType = InputType.TYPE_CLASS_TEXT
        }
    }

    companion object {
        @Target(AnnotationTarget.TYPE)
        @IntDef(NONE, PHONE, DATE, MONEY)
        @Retention(AnnotationRetention.SOURCE)
        annotation class EditTextInputType

        const val NONE = -1
        const val PHONE = 1909
        const val DATE = 1910
        const val MONEY = 1911
    }
}