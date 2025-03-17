package br.com.pokedex.core.base.dialog

import android.app.AlertDialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.view.KeyEvent
import androidx.annotation.CallSuper
import androidx.viewbinding.ViewBinding
import br.com.pokedex.core.ext.ONE_HUNDRED
import br.com.pokedex.core.ext.ZERO

abstract class BaseAlertDialog(context: Context) : AlertDialog(context) {

    private var onBackPressed: (() -> Unit)? = null
    private var _onDismissListener: (() -> Unit)? = null
    private var isDismissible = true

    protected abstract val binding: ViewBinding
    protected open val widthPercentage = DEFAULT_WIDTH_PERCENTAGE

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupWidthPercent()
        setupOnBackPressed()
    }

    fun setOnBackPressed(block: () -> Unit) {
        onBackPressed = block
    }

    private fun setupWidthPercent() {
        val percentageAsFloat = widthPercentage.toFloat() / Int.ONE_HUNDRED
        val displayMetrics = Resources.getSystem().displayMetrics
        val rect = displayMetrics.run { Rect(Int.ZERO, Int.ZERO, widthPixels, heightPixels) }
        val width = rect.width().times(percentageAsFloat).toInt()

        window?.apply {
            setLayout(width, attributes.height)
        }
    }

    private fun setupOnBackPressed() {
        setOnKeyListener { _, keyCode, keyEvent ->
            if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.action == KeyEvent.ACTION_UP) {
                onBackPressed?.let {
                    it.invoke()
                    true
                }
                isDismissible.not()
            }
            false
        }
    }

    companion object {
        private const val DEFAULT_WIDTH_PERCENTAGE = 85
    }
}