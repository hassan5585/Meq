package tech.mujtaba.meq.feature.camera.navigation

import tech.mujtaba.meq.core.navigation.Destination

data object ObjectDetectionDestination : Destination {
    override val route: String
        get() = "ObjectDetector"
}
