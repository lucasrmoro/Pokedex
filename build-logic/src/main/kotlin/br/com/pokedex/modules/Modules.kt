package br.com.pokedex.modules

object Modules {
    const val CORE = ":core"
    const val CORE_NETWORK = ":core-network"
    const val CORE_UI = ":core-ui"
    const val COMMON_DOMAIN = ":common:domain"
    const val LOCAL_STORAGE = ":local-storage"

    object Feature {
        private const val ROOT = ":features"

        object Pokemons {
            const val DATA = "$ROOT:pokemons:data"
            const val DOMAIN = "$ROOT:pokemons:domain"
            const val PRESENTATION = "$ROOT:pokemons:presentation"
        }
    }
}