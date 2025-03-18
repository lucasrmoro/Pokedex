package br.com.pokedex.domain.repository.foo

import br.com.pokedex.core_ui.adapter.model.Foo
import br.com.pokedex.domain.ext.execute
import br.com.pokedex.data.foo.local.FooLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class FooRepositoryImpl(
    private val dispatcher: CoroutineDispatcher,
    private val fooLocalDataSource: FooLocalDataSource,
    private val fooMapper: FooMapper
) : FooRepository {

    override suspend fun insert(foo: Foo): Long = dispatcher.execute {
        fooLocalDataSource.insert(fooMapper.toDTO(foo))
    }

    override suspend fun update(foo: Foo) =
        dispatcher.execute { fooLocalDataSource.update(fooMapper.toDTO(foo)) }

    override suspend fun delete(foo: Foo) =
        dispatcher.execute { fooLocalDataSource.delete(fooMapper.toDTO(foo)) }

    override suspend fun deleteBy(id: Long) = dispatcher.execute { fooLocalDataSource.deleteBy(id) }

    override suspend fun getAll(page: Int, itemsPerPage: Int): Flow<List<Foo>> =
        fooMapper.toDomainModelList(
            fooLocalDataSource.getAll(page = page, itemsPerPage = itemsPerPage)
        )

    override suspend fun getBy(id: Long): Foo? = dispatcher.execute {
        fooMapper.toDomainModel(fooLocalDataSource.getBy(id))
    }

    override suspend fun getPagesCount(itemsPerPage: Int): Int =
        fooLocalDataSource.getPagesCount(itemsPerPage)

}