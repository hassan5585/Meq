package tech.mujtaba.meq.feature.camera.domain.model

import android.graphics.RectF

data class DetectionResult(
    val boundingBox: RectF,
    val label: String
)
