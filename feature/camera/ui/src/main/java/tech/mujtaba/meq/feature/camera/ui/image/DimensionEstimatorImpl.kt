package tech.mujtaba.meq.feature.camera.ui.image

import java.text.DecimalFormat
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tech.mujtaba.meq.feature.camera.domain.model.DetectionResult
import tech.mujtaba.meq.feature.camera.ui.image.DimensionEstimator.Companion.HEIGHT_REFERENCE_CM
import tech.mujtaba.meq.feature.camera.ui.image.DimensionEstimator.Companion.LABEL_REFERENCE
import tech.mujtaba.meq.feature.camera.ui.image.DimensionEstimator.Companion.WIDTH_REFERENCE_CM

internal class DimensionEstimatorImpl @Inject constructor(
    private val decimalFormat: DecimalFormat
) : DimensionEstimator {
    override suspend fun estimate(results: List<DetectionResult>): List<Estimate> = withContext(Dispatchers.Default) {
        if (results.isEmpty()) return@withContext emptyList()
        // No reference object was found, cannot estimate dimensions without it.
        val referenceObject = results.firstOrNull { it.label == LABEL_REFERENCE } ?: return@withContext emptyList()
        val referenceBoundingBox = referenceObject.boundingBox
        // Inverting height and width because the image processed was with rotation in there.
        val pixelHeight = HEIGHT_REFERENCE_CM / referenceBoundingBox.width()
        val pixelWidth = WIDTH_REFERENCE_CM / referenceBoundingBox.height()

        results.map { result ->
            val boundingBox = result.boundingBox
            Estimate(
                height = "${decimalFormat.format(boundingBox.width() * pixelHeight)} cm",
                width = "${decimalFormat.format(boundingBox.height() * pixelWidth)} cm",
                isReference = result.label == LABEL_REFERENCE,
                label = result.label
            )
        }
    }
}
