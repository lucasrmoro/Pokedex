package br.com.pokedex.local_storage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.pokedex.local_storage.db.entity.foo.FooDbEntity
import br.com.pokedex.local_storage.db.dao.FooDao

@TypeConverters(br.com.pokedex.local_storage.db.TypeConverters::class)
@Database(entities = [FooDbEntity::class], version = 1, exportSchema = false)
internal abstract class LocalDatabase : RoomDatabase() {

    abstract fun fooDao(): FooDao

}