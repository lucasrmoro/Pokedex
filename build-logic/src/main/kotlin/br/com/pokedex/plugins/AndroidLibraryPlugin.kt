package br.com.pokedex.plugins

import br.com.pokedex.Plugins
import br.com.pokedex.ext.implementation
import br.com.pokedex.plugins.base.BasePlugin
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryPlugin : BasePlugin() {

    override fun setup(project: Project) = with(project) {
        setupPlugins()
        setupConfig()
        setupDependencies()
    }

    private fun Project.setupPlugins() {
        apply(plugin = Plugins.ANDROID_LIBRARY)
        apply(plugin = Plugins.KOTLIN_ANDROID)
    }

    private fun Project.setupConfig() {
        extensions.configure<LibraryExtension> {
            setupProjectConfig(this)
        }
    }

    private fun Project.setupDependencies() {
        dependencies {
            implementation(libs.timber)
        }
    }
}