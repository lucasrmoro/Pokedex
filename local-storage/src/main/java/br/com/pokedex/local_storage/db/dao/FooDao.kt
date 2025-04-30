package br.com.pokedex.local_storage.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.pokedex.local_storage.db.entity.foo.FooDbEntity

@Dao
interface FooDao {

    @Insert
    suspend fun insert(foo: FooDbEntity): Long

    @Update
    suspend fun update(foo: FooDbEntity)

    @Delete
    suspend fun delete(foo: FooDbEntity)

    @Query("DELETE FROM foo_table WHERE id=:id")
    suspend fun deleteBy(id: Long)

    @Query("SELECT * FROM foo_table LIMIT :limit OFFSET :offset")
    suspend fun getAll(limit: Int, offset: Int): List<FooDbEntity>

    @Query("SELECT * FROM foo_table WHERE id=:id LIMIT 1")
    suspend fun getBy(id: Long): FooDbEntity?

    @Query("SELECT COUNT(id) FROM foo_table")
    suspend fun getRowsCount(): Int

}