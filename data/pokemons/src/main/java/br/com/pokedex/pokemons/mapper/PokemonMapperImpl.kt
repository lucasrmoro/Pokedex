package br.com.pokedex.pokemons.mapper

import br.com.pokedex.core.ext.BAR
import br.com.pokedex.core.ext.SLASH
import br.com.pokedex.core.ext.downloadImage
import br.com.pokedex.core.ext.orZero
import br.com.pokedex.core.ext.update
import br.com.pokedex.core.ext.updateNotNull
import br.com.pokedex.pokemons.dto.PokemonDTO
import br.com.pokedex.pokemons.dto.PokemonDetailsDTO
import br.com.pokedex.pokemons.dto.PokemonsListDTO
import br.com.pokedex.pokemons.model.PokemonDetails
import br.com.pokedex.pokemons.model.PokemonItem
import br.com.pokedex.pokemons.model.PokemonListItems
import br.com.pokedex.pokemons.model.PokemonType
import br.com.pokedex.pokemons.model.PokemonTypeItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class PokemonMapperImpl(
    private val dispatcher: CoroutineDispatcher
) : PokemonMapper {

    override fun toPokemonListItems(dto: Flow<PokemonsListDTO>): Flow<PokemonListItems> =
        dto.update {
            PokemonListItems(
                pagesCount = count.orZero(),
                pokemons = pokemons?.map { it.toPokemonItem() }.orEmpty()
            )
        }

    override fun toPokemonListItems(
        dto: Flow<PokemonDetailsDTO>,
        pagesCount: Int
    ): Flow<PokemonListItems> = dto.update {
        PokemonListItems(pagesCount = pagesCount, pokemons = listOf(toPokemonItem()))
    }

    override fun toPokemonDetails(dto: Flow<PokemonDetailsDTO>): Flow<PokemonDetails> =
        dto.updateNotNull {
            val image = getImageUrl(id).downloadImage(dispatcher) ?: return@updateNotNull null

            PokemonDetails(
                id = id.orZero(),
                image = image,
                name = name?.replaceFirstChar { char -> char.uppercase() }.orEmpty(),
                hp = getStat(HP),
                atk = getStat(ATK),
                def = getStat(DEF),
                satk = getStat(SATK),
                sdef = getStat(SDEF),
                spd = getStat(SPD),
                types = types?.mapNotNull { type ->
                    PokemonType.entries.find { type.type?.name.equals(it.name, ignoreCase = true) }
                        ?.let {
                            PokemonTypeItem(it)
                        }
                }.orEmpty()
            )
        }

    private fun PokemonDTO.toPokemonItem() = PokemonItem(
        id = url.getPokemonIndex(),
        name = name.orEmpty().replaceFirstChar { char -> char.uppercase() },
        imageUrl = getImageUrl(url.getPokemonIndex())
    )

    private fun PokemonDetailsDTO.toPokemonItem() = PokemonItem(
        id = id.orZero(),
        name = name.orEmpty(),
        imageUrl = getImageUrl(id),
        isFavorite = false
    )

    private fun String?.getPokemonIndex() =
        orEmpty().trim(Char.SLASH).substringAfterLast(String.BAR).toInt()

    private fun getImageUrl(pokemonIndex: Int?) = pokemonIndex?.let {
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/$it.png"
    }

    private fun PokemonDetailsDTO.getStat(stat: String) =
        stats?.find { it.stat?.name == stat }?.baseStat.orZero()

    companion object {
        private const val HP = "hp"
        private const val ATK = "attack"
        private const val DEF = "defense"
        private const val SATK = "special-attack"
        private const val SDEF = "special-defense"
        private const val SPD = "speed"
    }
}