package br.com.pokedex.core.ext

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity.OVERRIDE_TRANSITION_CLOSE

fun Activity.startActivityByClassName(
    className: String,
    showActivityTransition: Boolean = true,
    finishCurrentActivity: Boolean = false
) {
    startActivity(Intent().setClassName(this, className))

    if (finishCurrentActivity) finish()
    if (showActivityTransition.not()) stopPendingTransition()
}

@Suppress("DEPRECATION")
private fun Activity.stopPendingTransition() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
        overrideActivityTransition(
            OVERRIDE_TRANSITION_CLOSE,
            OVERRIDE_TRANSITION_CLOSE,
            OVERRIDE_TRANSITION_CLOSE
        )
    } else {
        overridePendingTransition(Int.ZERO, Int.ZERO)
    }
}