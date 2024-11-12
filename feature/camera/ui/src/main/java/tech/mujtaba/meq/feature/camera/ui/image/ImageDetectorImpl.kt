package tech.mujtaba.meq.feature.camera.ui.image

import android.graphics.Bitmap
import android.view.Surface
import androidx.activity.ComponentActivity
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.core.vision.ImageProcessingOptions
import org.tensorflow.lite.task.core.vision.ImageProcessingOptions.Orientation
import org.tensorflow.lite.task.vision.detector.ObjectDetector
import tech.mujtaba.meq.feature.camera.domain.model.DetectionResult
import tech.mujtaba.meq.feature.camera.domain.repository.ImageDetector

@ActivityScoped
internal class ImageDetectorImpl @Inject constructor(
    private val activity: ComponentActivity
) : ImageDetector {
    private val threshold: Float = 0.3f
    private val maxResults = 3

    private val baseOptions = BaseOptions.builder()
        .setNumThreads(2)
        .build()

    private val objectDetectorOptions = ObjectDetector.ObjectDetectorOptions.builder()
        .setBaseOptions(baseOptions)
        .setMaxResults(maxResults)
        .setScoreThreshold(threshold)
        .build()

    private val objectDetector = ObjectDetector.createFromFileAndOptions(
        activity,
        "ssd_mobilenet_v1.tflite",
        objectDetectorOptions
    )

    override fun detect(bitmap: Bitmap, rotation: Int): List<DetectionResult> {
        val imageProcessingOptions = ImageProcessingOptions
            .builder()
            .setOrientation(getOrientationFromRotation(rotation))
            .build()
        val tensorImage = TensorImage.fromBitmap(bitmap)

        return objectDetector.detect(tensorImage, imageProcessingOptions)
            .map { detection ->
                val category = detection.categories.first()
                DetectionResult(detection.boundingBox, category.label)
            }.distinctBy { it.label }
    }

    private fun getOrientationFromRotation(rotation: Int): Orientation {
        return when (rotation) {
            Surface.ROTATION_270 -> Orientation.BOTTOM_RIGHT
            Surface.ROTATION_90 -> Orientation.TOP_LEFT
            Surface.ROTATION_180 -> Orientation.RIGHT_BOTTOM
            else -> Orientation.RIGHT_TOP
        }
    }
}
