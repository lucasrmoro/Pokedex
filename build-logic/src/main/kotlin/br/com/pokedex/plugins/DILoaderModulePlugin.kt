package br.com.pokedex.plugins

import br.com.pokedex.Plugins
import br.com.pokedex.ext.implementation
import br.com.pokedex.ext.ksp
import br.com.pokedex.plugins.base.BaseLibraryPlugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class DILoaderModulePlugin : BaseLibraryPlugin() {

    override fun setupPlugins(project: Project) = with(project) {
        apply(plugin = Plugins.KSP)
        apply(plugin = Plugins.ANDROID_LIBRARY)
        apply(plugin = Plugins.KOTLIN_ANDROID)
    }

    override fun setupDependencies(project: Project) = with(project) {
        dependencies {
            ksp(libs.koin.compiler)
            implementation(libs.bundles.koin)
        }
    }

}