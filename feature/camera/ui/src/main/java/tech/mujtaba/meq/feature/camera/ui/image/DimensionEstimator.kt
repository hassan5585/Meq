package tech.mujtaba.meq.feature.camera.ui.image

import tech.mujtaba.meq.feature.camera.domain.model.DetectionResult

interface DimensionEstimator {
    suspend fun estimate(results: List<DetectionResult>): List<Estimate>

    companion object {
        const val HEIGHT_REFERENCE_CM = 10
        const val WIDTH_REFERENCE_CM = 8
        const val LABEL_REFERENCE = "cup"
    }
}

/**
 * Both height and width are in cm
 */
data class Estimate(
    val height: String,
    val width: String,
    val isReference: Boolean,
    val label: String
)
