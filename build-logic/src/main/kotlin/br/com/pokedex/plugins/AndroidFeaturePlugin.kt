package br.com.pokedex.plugins

import br.com.pokedex.Plugins
import br.com.pokedex.ext.implementation
import br.com.pokedex.ext.ksp
import br.com.pokedex.modules.Modules
import br.com.pokedex.plugins.base.BasePlugin
import com.android.build.api.dsl.DynamicFeatureExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeaturePlugin : BasePlugin() {

    override fun setup(project: Project) = with(project) {
        setupPlugins()
        setupConfig()
        setupDependencies()
    }

    private fun Project.setupPlugins() {
        apply(plugin = Plugins.ANDROID_DYNAMIC_FEATURE)
        apply(plugin = Plugins.KOTLIN_ANDROID)
        apply(plugin = Plugins.KSP)
        apply(plugin = Plugins.QUADRANT)
    }

    private fun Project.setupConfig() = extensions.configure<DynamicFeatureExtension> {
        setupProjectConfig(this)
    }

    private fun Project.setupDependencies() = dependencies {
        implementation(project(Modules.APP))
        implementation(project(Modules.CORE))
        implementation(project(Modules.CORE_UI))
        implementation(project(Modules.CORE_NETWORK))
        implementation(project(Modules.ENTITIES))
        implementation(libs.material)
        implementation(libs.bundles.android)
        implementation(libs.bundles.koin)
        ksp(libs.koin.compiler)
        implementation(libs.timber)
    }
}