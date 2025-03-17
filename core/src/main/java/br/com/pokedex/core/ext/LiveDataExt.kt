package br.com.pokedex.core.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

fun <T> LiveData<T>.debounce(timeout: Long, timeUnit: TimeUnit): LiveData<T> {
    val result = MediatorLiveData<T>()
    val scope = CoroutineScope(Dispatchers.Main)
    var debounceJob: Job? = null

    result.addSource(this) { value ->
        debounceJob?.cancel()
        debounceJob = scope.launch {
            delay(timeUnit.toMillis(timeout))
            result.value = value
        }
    }

    return result
}