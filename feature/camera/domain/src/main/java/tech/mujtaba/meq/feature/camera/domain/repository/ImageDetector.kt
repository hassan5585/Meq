package tech.mujtaba.meq.feature.camera.domain.repository

import android.graphics.Bitmap
import tech.mujtaba.meq.feature.camera.domain.model.DetectionResult

interface ImageDetector {
    fun detect(bitmap: Bitmap, rotation: Int): List<DetectionResult>
}
