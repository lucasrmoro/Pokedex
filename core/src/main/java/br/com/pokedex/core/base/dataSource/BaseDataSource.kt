package br.com.pokedex.core.base.dataSource

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

abstract class BaseDataSource {

    suspend fun <T> call(block: suspend FlowCollector<T>.() -> T) = flow {
        emit(block())
    }

}