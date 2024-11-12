package tech.mujtaba.meq.feature.camera.ui.image

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import tech.mujtaba.meq.feature.camera.domain.model.DetectionResult
import tech.mujtaba.meq.feature.camera.domain.repository.ImageDetector

internal class ObjectImageAnalyzer(
    private val detector: ImageDetector
) : ImageAnalysis.Analyzer {

    companion object {
        const val DESIRED_SIZE = 300
    }

    private val imageProcessor = ImageProcessor.Builder().add(
        ResizeOp(DESIRED_SIZE, DESIRED_SIZE, ResizeOp.ResizeMethod.BILINEAR)
    ).build()

    private val _results: MutableStateFlow<AnalyzerResult?> = MutableStateFlow(null)
    val results = _results.asStateFlow()

    private var frameCounter = 0

    override fun analyze(image: ImageProxy) {
        // We only want to analyze 1 bitmap every 60 frames to save on unnecessary processing
        if (frameCounter % 60 == 0) {
            val rotationDegrees = image.imageInfo.rotationDegrees
            val tempBitmap = image.toBitmap()
            val scaledDownBitmap = imageProcessor.process(TensorImage.fromBitmap(tempBitmap)).bitmap
            _results.value = AnalyzerResult(detector.detect(scaledDownBitmap, rotationDegrees))
        }
        frameCounter++
        image.close()
    }

    data class AnalyzerResult(
        val results: List<DetectionResult>
    )
}
