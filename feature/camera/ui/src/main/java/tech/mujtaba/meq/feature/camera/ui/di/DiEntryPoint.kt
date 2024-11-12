package tech.mujtaba.meq.feature.camera.ui.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import java.util.concurrent.Executor
import tech.mujtaba.meq.feature.camera.domain.repository.ImageDetector
import tech.mujtaba.meq.feature.camera.ui.image.DimensionEstimator
import tech.mujtaba.meq.feature.camera.ui.permission.PermissionHelper

@EntryPoint
@InstallIn(ActivityComponent::class)
internal interface DiEntryPoint {
    fun permissionHelper(): PermissionHelper
    fun executor(): Executor
    fun imageDetector(): ImageDetector
    fun dimensionEstimator(): DimensionEstimator
}
