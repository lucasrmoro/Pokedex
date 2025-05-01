package br.com.pokedex.modules

object Modules {
    const val APP = ":app"
    const val CORE = ":core"
    const val CORE_NETWORK = ":core-network"
    const val CORE_UI = ":core-ui"
    const val DOMAIN = ":domain:domain"
    const val LOCAL_STORAGE = ":local-storage"

    object Data {
        private const val ROOT = ":data"
        const val POKEMONS = "$ROOT:pokemons"
    }

    object Domain {
        private const val ROOT = ":domain"
        const val POKEMONS = "$ROOT:pokemons"
    }

    object Feature {
        private const val ROOT = ":features"
        const val POKEMONS = "$ROOT:pokemons"
    }
}