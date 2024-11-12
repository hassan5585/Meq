package tech.mujtaba.meq.feature.camera.ui.image

import android.graphics.RectF
import io.mockk.every
import io.mockk.mockk
import java.text.DecimalFormat
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import tech.mujtaba.meq.feature.camera.domain.model.DetectionResult

class DimensionEstimatorImplTest {

    private val decimalFormat = DecimalFormat("###.##")

    private fun estimator() = DimensionEstimatorImpl(decimalFormat)

    @Test
    fun `given no results return empty list`() = runTest {
        val estimator = estimator()

        assertEquals(0, estimator.estimate(emptyList()).size)
    }

    @Test
    fun `given no reference object found return empty list`() = runTest {
        val estimator = estimator()

        val list: List<DetectionResult> = listOf(mockk(relaxed = true))

        assertEquals(0, estimator.estimate(list).size)
    }

    @Test
    fun `given a reference object is found return mapped list of estimates in cm`() = runTest {
        val estimator = estimator()

        val rectOne = mockRectF(30f, 30f)
        val rectTwo = mockRectF(150f, 90f)
        val resultOne = DetectionResult(rectOne, DimensionEstimator.LABEL_REFERENCE)
        val resultTwo = DetectionResult(rectTwo, "Some Other reference")
        val list: List<DetectionResult> = listOf(resultOne, resultTwo)

        val estimates = estimator.estimate(list)

        assertEquals(2, estimates.size)
        val nonReferenceEstimate = estimates[1]
        assertEquals("30 cm", nonReferenceEstimate.height)
        assertEquals("40 cm", nonReferenceEstimate.width)
    }

    private fun mockRectF(height: Float, width: Float): RectF {
        return mockk<RectF>().apply {
            every { height() } returns height
            every { width() } returns width
        }
    }
}
