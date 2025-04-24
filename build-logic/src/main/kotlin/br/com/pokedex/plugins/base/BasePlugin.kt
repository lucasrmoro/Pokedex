package br.com.pokedex.plugins.base

import br.com.pokedex.BuildTypes
import br.com.pokedex.ConfigField.API_BASE_URL
import br.com.pokedex.ConfigField.API_BASE_URL_DEV
import br.com.pokedex.ConfigField.API_BASE_URL_PRD
import br.com.pokedex.Flavors.DEBUG_APP_ID_SUFFIX
import br.com.pokedex.Flavors.DEBUG_APP_VERSION_NAME_SUFFIX
import br.com.pokedex.Flavors.DEFAULT_DIMEN
import br.com.pokedex.Flavors.DEV
import br.com.pokedex.Flavors.PRD
import br.com.pokedex.ProjectConfig
import br.com.pokedex.ext.setStringBuildConfigField
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.dsl.VariantDimension
import com.android.build.gradle.BaseExtension
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

abstract class BasePlugin : Plugin<Project> {

    protected val Project.libs: LibrariesForLibs
        get() = extensions.getByType()

    abstract fun setup(project: Project)

    override fun apply(project: Project) = with(project) {
        setup(project)
        setupJvmTarget()
    }

    protected fun setupProjectConfig(commonExt: CommonExtension<*, *, *, *, *, *>) {
        commonExt.apply {
            compileSdk = ProjectConfig.COMPILE_SDK

            defaultConfig.apply {
                minSdk = ProjectConfig.MIN_SDK
                compileSdk = ProjectConfig.COMPILE_SDK
                testInstrumentationRunner = ProjectConfig.ANDROID_JUNIT_RUNNER
            }

            if (this is BaseExtension) {
                setupBuildTypes()
                setupFlavors()
            }

            compileOptions {
                sourceCompatibility = ProjectConfig.JAVA_VERSION
                targetCompatibility = ProjectConfig.JAVA_VERSION
            }

            with(buildFeatures) {
                buildConfig = true
                viewBinding = true
            }
        }
    }

    private fun BaseExtension.setupFlavors() {
        flavorDimensions(DEFAULT_DIMEN)
        productFlavors {
            create(DEV) {
                setupAppName(isDev = true)
                setStringBuildConfigField(name = API_BASE_URL, value = API_BASE_URL_DEV)
                dimension = DEFAULT_DIMEN
            }

            create(PRD) {
                setupAppName(isDev = false)
                setStringBuildConfigField(name = API_BASE_URL, value = API_BASE_URL_PRD)
                dimension = DEFAULT_DIMEN
            }
        }
    }

    private fun BaseExtension.setupBuildTypes() {
        buildTypes {
            getByName(BuildTypes.DEBUG) {
                versionNameSuffix = DEBUG_APP_VERSION_NAME_SUFFIX
                if (this is ApplicationExtension) {
                    applicationIdSuffix = DEBUG_APP_ID_SUFFIX
                }
                if (this is ApplicationExtension || this is LibraryExtension) {
                    isMinifyEnabled = false
                    isShrinkResources = false
                }
                isDebuggable = true
            }

            getByName(BuildTypes.RELEASE) {
                if (this is ApplicationExtension || this is LibraryExtension) {
                    isMinifyEnabled = true
                    isShrinkResources = true
                }
                if (this is ApplicationExtension) {
                    proguardFiles(
                        getDefaultProguardFile(ProjectConfig.PROGUARD_ANDROID_OPTIMIZE),
                        ProjectConfig.PROGUARD_RULES
                    )
                }
                isDebuggable = false
            }
        }
    }

    private fun Project.setupJvmTarget() {
        tasks.withType<KotlinCompile> {
            compilerOptions {
                jvmTarget.set(ProjectConfig.JVM_TARGET)
            }
        }
    }

    private fun VariantDimension.setupAppName(isDev: Boolean) {
        val appName = if (isDev) DEV else PRD
        manifestPlaceholders["appLabel"] = "@string/app_name_$appName"
    }
}