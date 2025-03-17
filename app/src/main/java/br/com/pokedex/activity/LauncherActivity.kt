package br.com.pokedex.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.pokedex.core.ext.startActivityByClassName
import com.gaelmarhic.quadrant.QuadrantConstants.FEATURE_A_ACTIVITY

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivityByClassName(
            className = FEATURE_A_ACTIVITY,
            showActivityTransition = false,
            finishCurrentActivity = true
        )
    }

}