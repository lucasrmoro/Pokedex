package br.com.pokedex.core.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.util.AttributeSet
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.annotation.StyleableRes
import androidx.appcompat.app.AppCompatActivity

fun Context?.showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
    this?.let { Toast.makeText(this, message, length).show() }
}

fun Context?.showToast(@StringRes message: Int, length: Int = Toast.LENGTH_SHORT) {
    this?.let { showToast(getString(message), length) }
}

fun Context.getCustomAttributes(
    attributeSet: AttributeSet,
    @StyleableRes styleableRes: IntArray,
    block: TypedArray.() -> Unit
) {
    obtainStyledAttributes(attributeSet, styleableRes).apply {
        block()
        recycle()
    }
}

inline fun <reified T : AppCompatActivity> Context.openActivity(
    addNewTaskFlag: Boolean = false,
    args: Bundle.() -> Unit = { }
) {
    Intent(this, T::class.java).also {
        if (addNewTaskFlag) {
            listOf(Intent.FLAG_ACTIVITY_NEW_TASK, Intent.FLAG_ACTIVITY_CLEAR_TASK).forEach { flag ->
                it.addFlags(flag)
            }
        }
        it.putExtras(Bundle().apply(args))
        startActivity(it)
    }
}

fun Context.hideKeyboard() {
    runCatching {
        (this as Activity).currentFocus?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}