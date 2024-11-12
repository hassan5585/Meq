package tech.mujtaba.meq.core.navigation

import androidx.navigation.NavGraphBuilder

/**
 * Provide an instance of this class from each feature ui module to load your nav graph into the main app
 */
interface NavGraphProvider {
    val graph: NavGraphBuilder.() -> Unit
}
