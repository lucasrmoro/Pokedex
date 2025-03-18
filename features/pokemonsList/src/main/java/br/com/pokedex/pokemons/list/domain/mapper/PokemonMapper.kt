package br.com.pokedex.pokemons.list.domain.mapper

import br.com.pokedex.core.base.mapper.BaseMapper
import br.com.pokedex.core_ui.adapter.model.PokemonItem
import br.com.pokedex.pokemons.list.data.dto.PokemonDTO

abstract class PokemonMapper : BaseMapper<PokemonDTO, PokemonItem>()