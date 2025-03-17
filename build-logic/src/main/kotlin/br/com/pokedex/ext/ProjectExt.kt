import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.plugin.use.PluginDependency

private val Provider<PluginDependency>.id
    get() = get().pluginId

internal fun Project.applyPlugins(vararg plugins: Provider<PluginDependency>) {
    plugins.forEach {
        this@applyPlugins.plugins.apply(it.id)
    }
}