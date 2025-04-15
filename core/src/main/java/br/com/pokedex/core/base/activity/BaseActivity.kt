package br.com.pokedex.core.base.activity

import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.widget.EditText
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import br.com.pokedex.core.base.viewModel.BaseViewModel
import br.com.pokedex.core.ext.getViewModelClass
import br.com.pokedex.core.ext.hideKeyboard
import br.com.pokedex.core.provider.appSession.AppSessionProvider
import br.com.pokedex.core.provider.network.provider.NetworkConnectivityProvider
import br.com.pokedex.core.provider.permission.PermissionsProvider
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModelForClass
import org.koin.core.parameter.parametersOf
import timber.log.Timber

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    private val appSessionProvider by inject<AppSessionProvider>()
    private val networkConnectivityProvider by inject<NetworkConnectivityProvider>()
    private val permissionsProvider by inject<PermissionsProvider> {
        parametersOf(this)
    }
    protected abstract val binding: ViewBinding
    protected val viewModel: VM by lazy { viewModelForClass(getViewModelClass()).value }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupObservers()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionsProvider.onRequestPermissionsResult(
            requestCode,
            permissions.asList(),
            grantResults
        )
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            currentFocus.takeIf { it is EditText }?.run {
                val outRect = Rect()
                getGlobalVisibleRect(outRect)
                if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    hideKeyboard()
                    clearFocus()
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun setupObservers() {
        appSessionProvider.isSessionExpired.observe(this) { sessionExpired -> }
        networkConnectivityProvider.isInternetAvailable.observe(this) { isInternetAvailable ->
            Timber.d("Is internet available: $isInternetAvailable")
        }
    }
}