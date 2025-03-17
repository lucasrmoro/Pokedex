package br.com.pokedex.ext

import com.android.build.api.dsl.VariantDimension

private const val STRING = "String"

internal fun VariantDimension.setStringBuildConfigField(name: String, value: String) {
    buildConfigField(type = STRING, name = name, value = "\"$value\"")
}