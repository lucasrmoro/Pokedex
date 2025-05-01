package br.com.pokedex.plugins.base

import androidx.annotation.CallSuper
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

abstract class BaseLibraryPlugin : BasePlugin() {

    abstract fun setupPlugins(project: Project)
    abstract fun setupDependencies(project: Project)

    @CallSuper
    override fun setup(project: Project) {
        setupPlugins(project)
        project.extensions.configure<LibraryExtension> {
            setupProjectConfig(this)
        }
        setupDependencies(project)
    }

}