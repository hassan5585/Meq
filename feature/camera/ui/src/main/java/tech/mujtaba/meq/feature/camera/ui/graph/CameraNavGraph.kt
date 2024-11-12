package tech.mujtaba.meq.feature.camera.ui.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import tech.mujtaba.meq.feature.camera.navigation.ObjectDetectionDestination
import tech.mujtaba.meq.feature.camera.ui.upcoming.CameraScreen

internal fun NavGraphBuilder.cameraNavGraph() {
    composable(route = ObjectDetectionDestination.route, content = { entry ->
        CameraScreen(entry)
    })
}
