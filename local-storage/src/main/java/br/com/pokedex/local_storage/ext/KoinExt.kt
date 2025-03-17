package br.com.pokedex.local_storage.ext

import br.com.pokedex.local_storage.db.LocalDatabase
import org.koin.core.scope.Scope

internal val Scope.database: LocalDatabase
    get() = get<LocalDatabase>()