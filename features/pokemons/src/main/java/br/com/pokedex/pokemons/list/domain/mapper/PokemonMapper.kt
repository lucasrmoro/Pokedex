package br.com.pokedex.pokemons.list.domain.mapper

import br.com.pokedex.core.base.mapper.BaseMapper
import br.com.pokedex.core_ui.adapter.model.PokemonItem
import br.com.pokedex.pokemons.list.data.dto.PokemonDTO
import br.com.pokedex.pokemons.list.data.dto.PokemonDetailsDTO

abstract class PokemonMapper : BaseMapper<PokemonDTO, PokemonItem>() {

    abstract fun toDomainModel(dto: PokemonDetailsDTO): PokemonItem

}