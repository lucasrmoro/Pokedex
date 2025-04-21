package br.com.pokedex.plugins

import br.com.pokedex.BuildTypes
import br.com.pokedex.Plugins
import br.com.pokedex.ProjectConfig
import br.com.pokedex.Signing
import java.io.File
import br.com.pokedex.ext.implementation
import br.com.pokedex.modules.Modules
import br.com.pokedex.plugins.base.BasePlugin
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidAppPlugin : BasePlugin() {

    override fun setup(project: Project) = with(project) {
        setupPlugins()
        setupConfig()
        setupDependencies()
    }

    private fun Project.setupPlugins() {
        apply(plugin = Plugins.ANDROID_APPLICATION)
        apply(plugin = Plugins.KOTLIN_ANDROID)
        apply(plugin = Plugins.KSP)
        apply(plugin = Plugins.QUADRANT)
    }

    private fun Project.setupConfig() = extensions.configure<ApplicationExtension> {
        setupProjectConfig(this)
        signingConfigs {
            create(BuildTypes.RELEASE) {
                storeFile = File(System.getenv(Signing.KEYSTORE_FILE_NAME))
                storePassword = System.getenv(Signing.KEYSTORE_FILE_PASSWORD)
                keyAlias = System.getenv(Signing.KEY_ALIAS)
                keyPassword = System.getenv(Signing.KEY_PASSWORD)
            }
        }
        defaultConfig.apply {
            applicationId = ProjectConfig.APP_ID
            targetSdk = ProjectConfig.TARGET_SDK
            versionCode = ProjectConfig.VERSION_CODE
            versionName = ProjectConfig.VERSION_NAME
        }
    }

    private fun Project.setupDependencies() = dependencies {
        implementation(project(Modules.CORE))
        implementation(project(Modules.CORE_UI))
        implementation(libs.material)
        implementation(libs.bundles.android)
    }
}