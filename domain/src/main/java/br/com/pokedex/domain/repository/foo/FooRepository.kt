package br.com.pokedex.domain.repository.foo

import br.com.pokedex.core_ui.adapter.model.Foo
import kotlinx.coroutines.flow.Flow

interface FooRepository {

    suspend fun insert(foo: Foo): Long
    suspend fun update(foo: Foo)
    suspend fun delete(foo: Foo)
    suspend fun deleteBy(id: Long)
    suspend fun getAll(page: Int, itemsPerPage: Int): Flow<List<Foo>>
    suspend fun getBy(id: Long): Foo?
    suspend fun getPagesCount(itemsPerPage: Int): Int

}