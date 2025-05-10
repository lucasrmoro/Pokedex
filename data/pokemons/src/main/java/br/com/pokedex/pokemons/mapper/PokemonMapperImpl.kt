package br.com.pokedex.pokemons.mapper

import br.com.pokedex.core.ext.BAR
import br.com.pokedex.core.ext.ONE
import br.com.pokedex.core.ext.SLASH
import br.com.pokedex.core.ext.TWO
import br.com.pokedex.core.ext.ZERO
import br.com.pokedex.core.ext.ZERO_DOT_FIVE
import br.com.pokedex.core.ext.ZERO_DOT_NINE
import br.com.pokedex.core.ext.ZERO_DOT_ONE
import br.com.pokedex.core.ext.downloadImage
import br.com.pokedex.core.ext.orZero
import br.com.pokedex.core.ext.update
import br.com.pokedex.pokemons.dto.DamageRelationDTO
import br.com.pokedex.pokemons.dto.DamageRelationsDTO
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

    override suspend fun toPokemonDetails(
        dto: PokemonDetailsDTO,
        damageRelations: List<DamageRelationsDTO>
    ): PokemonDetails? = with(dto) {
        val image = getImageUrl(id).downloadImage(dispatcher) ?: return null
        val effectiveness = damageRelations.calculateTypesEffectiveness()

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
            types = types?.mapNotNull {
                it.type?.name?.toPokemonType()?.let { type -> PokemonTypeItem(type) }
            }.orEmpty(),
            weaknesses = effectiveness.filterValues { it > Double.ONE }.keys.map(::PokemonTypeItem),
            resistances = effectiveness.filterValues {
                it in Double.ZERO_DOT_ONE..Double.ZERO_DOT_NINE
            }.keys.map(::PokemonTypeItem),
            immunities = effectiveness.filterValues { it == Double.ZERO }.keys.map(::PokemonTypeItem)
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

    private fun String.toPokemonType() =
        PokemonType.entries.find { it.name.equals(this, ignoreCase = true) }

    private fun List<DamageRelationsDTO>.calculateTypesEffectiveness(): Map<PokemonType, Double> {
        val typeEffectiveness = mutableMapOf<PokemonType, Double>().withDefault { Double.ONE }
        val updateEffectiveness: (List<DamageRelationDTO>?, Double) -> Unit = { types, multiplier ->
            types.orEmpty().forEach { dto ->
                dto.name?.toPokemonType()?.let { type ->
                    typeEffectiveness[type] = typeEffectiveness.getValue(type) * multiplier
                }
            }
        }

        forEach { damageRelations ->
            with(damageRelations) {
                updateEffectiveness(doubleDamageFrom, Double.TWO)
                updateEffectiveness(halfDamageFrom, Double.ZERO_DOT_FIVE)
                updateEffectiveness(noDamageFrom, Double.ZERO)
            }
        }

        return typeEffectiveness
    }

    companion object {
        private const val HP = "hp"
        private const val ATK = "attack"
        private const val DEF = "defense"
        private const val SATK = "special-attack"
        private const val SDEF = "special-defense"
        private const val SPD = "speed"
    }
}