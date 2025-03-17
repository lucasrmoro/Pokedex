package br.com.pokedex.core_ui.delegate

import android.view.View
import android.widget.TextView
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class TextViewDelegate(private val textView: TextView) : ReadWriteProperty<View, CharSequence?> {

    override fun getValue(thisRef: View, property: KProperty<*>): CharSequence? = textView.text

    override fun setValue(thisRef: View, property: KProperty<*>, value: CharSequence?) {
        textView.text = value
    }

}