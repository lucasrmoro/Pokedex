package br.com.pokedex.local_storage.db.entity.foo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "foo_table")
data class FooDbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val msg: String
)