package br.com.pokedex.pokemons.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import br.com.pokedex.domain_pokemons.R

enum class PokemonType(
    @StringRes val text: Int,
    @DrawableRes val icon: Int,
    @ColorRes val startGradientColor: Int,
    @ColorRes val endGradientColor: Int
) {
    BUG(
        text = R.string.bug,
        icon = R.drawable.ic_pokemon_type_bug,
        startGradientColor = R.color.pokemon_type_bug_start_gradient_color,
        endGradientColor = R.color.pokemon_type_bug_end_gradient_color
    ),
    DARK(
        text = R.string.dark,
        icon = R.drawable.ic_pokemon_type_dark,
        startGradientColor = R.color.pokemon_type_dark_start_gradient_color,
        endGradientColor = R.color.pokemon_type_dark_end_gradient_color
    ),
    DRAGON(
        text = R.string.dragon,
        icon = R.drawable.ic_pokemon_type_dragon,
        startGradientColor = R.color.pokemon_type_dragon_start_gradient_color,
        endGradientColor = R.color.pokemon_type_dragon_end_gradient_color
    ),
    ELECTRIC(
        text = R.string.electric,
        icon = R.drawable.ic_pokemon_type_electric,
        startGradientColor = R.color.pokemon_type_electric_start_gradient_color,
        endGradientColor = R.color.pokemon_type_electric_end_gradient_color
    ),
    FAIRY(
        text = R.string.fairy,
        icon = R.drawable.ic_pokemon_type_fairy,
        startGradientColor = R.color.pokemon_type_fairy_start_gradient_color,
        endGradientColor = R.color.pokemon_type_fairy_end_gradient_color
    ),
    FIGHTING(
        text = R.string.fighting,
        icon = R.drawable.ic_pokemon_type_fighting,
        startGradientColor = R.color.pokemon_type_fighting_start_gradient_color,
        endGradientColor = R.color.pokemon_type_fighting_end_gradient_color
    ),
    FIRE(
        text = R.string.fire,
        icon = R.drawable.ic_pokemon_type_fire,
        startGradientColor = R.color.pokemon_type_fire_start_gradient_color,
        endGradientColor = R.color.pokemon_type_fire_end_gradient_color
    ),
    FLYING(
        text = R.string.flying,
        icon = R.drawable.ic_pokemon_type_flying,
        startGradientColor = R.color.pokemon_type_flying_start_gradient_color,
        endGradientColor = R.color.pokemon_type_flying_end_gradient_color
    ),
    GHOST(
        text = R.string.ghost,
        icon = R.drawable.ic_pokemon_type_ghost,
        startGradientColor = R.color.pokemon_type_ghost_start_gradient_color,
        endGradientColor = R.color.pokemon_type_ghost_end_gradient_color
    ),
    GRASS(
        text = R.string.grass,
        icon = R.drawable.ic_pokemon_type_grass,
        startGradientColor = R.color.pokemon_type_grass_start_gradient_color,
        endGradientColor = R.color.pokemon_type_grass_end_gradient_color
    ),
    GROUND(
        text = R.string.ground,
        icon = R.drawable.ic_pokemon_type_ground,
        startGradientColor = R.color.pokemon_type_ground_start_gradient_color,
        endGradientColor = R.color.pokemon_type_ground_end_gradient_color
    ),
    ICE(
        text = R.string.ice,
        icon = R.drawable.ic_pokemon_type_ice,
        startGradientColor = R.color.pokemon_type_ice_start_gradient_color,
        endGradientColor = R.color.pokemon_type_ice_end_gradient_color
    ),
    NORMAL(
        text = R.string.normal,
        icon = R.drawable.ic_pokemon_type_normal,
        startGradientColor = R.color.pokemon_type_normal_start_gradient_color,
        endGradientColor = R.color.pokemon_type_normal_end_gradient_color
    ),
    POISON(
        text = R.string.poison,
        icon = R.drawable.ic_pokemon_type_poison,
        startGradientColor = R.color.pokemon_type_poison_start_gradient_color,
        endGradientColor = R.color.pokemon_type_poison_end_gradient_color
    ),
    PSYCHIC(
        text = R.string.psychic,
        icon = R.drawable.ic_pokemon_type_psychic,
        startGradientColor = R.color.pokemon_type_psychic_start_gradient_color,
        endGradientColor = R.color.pokemon_type_psychic_end_gradient_color
    ),
    ROCK(
        text = R.string.rock,
        icon = R.drawable.ic_pokemon_type_rock,
        startGradientColor = R.color.pokemon_type_rock_start_gradient_color,
        endGradientColor = R.color.pokemon_type_rock_end_gradient_color
    ),
    STEEL(
        text = R.string.steel,
        icon = R.drawable.ic_pokemon_type_steel,
        startGradientColor = R.color.pokemon_type_steel_start_gradient_color,
        endGradientColor = R.color.pokemon_type_steel_end_gradient_color
    ),
    WATER(
        text = R.string.water,
        icon = R.drawable.ic_pokemon_type_water,
        startGradientColor = R.color.pokemon_type_water_start_gradient_color,
        endGradientColor = R.color.pokemon_type_water_end_gradient_color
    )
}
