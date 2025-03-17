package br.com.pokedex.data.foo.local

import br.com.pokedex.core.base.dataSource.BaseDataSource
import br.com.pokedex.core.ext.ONE
import br.com.pokedex.entities.db.FooDbEntity
import br.com.pokedex.local_storage.db.dao.FooDao
import kotlinx.coroutines.flow.Flow
import kotlin.math.ceil
import kotlin.math.roundToInt

internal class FooLocalDataSourceImpl(
    private val fooDao: FooDao
) : FooLocalDataSource, BaseDataSource() {

    override suspend fun insert(foo: FooDbEntity): Long = fooDao.insert(foo)

    override suspend fun update(foo: FooDbEntity) {
        fooDao.update(foo)
    }

    override suspend fun delete(foo: FooDbEntity) {
        fooDao.delete(foo)
    }

    override suspend fun deleteBy(id: Long) {
        fooDao.deleteBy(id)
    }

    override suspend fun getAll(page: Int, itemsPerPage: Int): Flow<List<FooDbEntity>> = call {
        fooDao.getAll(limit = itemsPerPage, offset = page.minus(Int.ONE).times(itemsPerPage))
    }

    override suspend fun getBy(id: Long): FooDbEntity? = fooDao.getBy(id)

    override suspend fun getPagesCount(itemsPerPage: Int): Int =
        ceil(fooDao.getRowsCount() / itemsPerPage.toDouble()).roundToInt()

}