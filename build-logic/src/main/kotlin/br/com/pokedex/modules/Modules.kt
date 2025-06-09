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
            private const val POKEMONS = "$ROOT:pokemons"

            const val DATA = "$POKEMONS:data"
            const val DOMAIN = "$POKEMONS:domain"
            const val PRESENTATION = "$POKEMONS:presentation"
        }
    }

    object DiLoader {
        private const val ROOT = ":di-loader"

        object Pokemons {
            private const val POKEMONS = "$ROOT:pokemons"

            const val API = "$POKEMONS:api"
            const val IMPL = "$POKEMONS:impl"
        }
    }
}