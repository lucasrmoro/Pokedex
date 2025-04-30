package br.com.pokedex.plugins

import br.com.pokedex.Plugins
import br.com.pokedex.ext.implementation
import br.com.pokedex.ext.api
import br.com.pokedex.ext.ksp
import br.com.pokedex.modules.Modules
import br.com.pokedex.plugins.base.BaseLibraryPlugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class DomainModulePlugin : BaseLibraryPlugin() {

    override fun setupPlugins(project: Project) = with(project) {
        apply(plugin = Plugins.ANDROID_LIBRARY)
        apply(plugin = Plugins.KOTLIN_ANDROID)
        apply(plugin = Plugins.KSP)
    }

    override fun setupDependencies(project: Project) = with(project) {
        dependencies {
            api(project(Modules.DOMAIN))
            implementation(project(Modules.CORE))
            implementation(libs.timber)
            ksp(libs.koin.compiler)
            implementation(libs.bundles.koin)
        }
    }

}