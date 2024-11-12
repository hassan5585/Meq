package tech.mujtaba.meq.feature.camera.ui.di

import androidx.navigation.NavGraphBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoSet
import tech.mujtaba.meq.core.navigation.NavGraphProvider
import tech.mujtaba.meq.feature.camera.domain.repository.ImageDetector
import tech.mujtaba.meq.feature.camera.ui.graph.cameraNavGraph
import tech.mujtaba.meq.feature.camera.ui.image.ImageDetectorImpl
import tech.mujtaba.meq.feature.camera.ui.permission.PermissionHelper
import tech.mujtaba.meq.feature.camera.ui.permission.PermissionHelperImpl

@Module
@InstallIn(ActivityComponent::class)
internal class UiActivityModule {
    @Provides
    @IntoSet
    fun provideRaceGraphProvider(): NavGraphProvider = object : NavGraphProvider {
        override val graph: NavGraphBuilder.() -> Unit = NavGraphBuilder::cameraNavGraph
    }

    @Provides
    fun providePermissionHelper(impl: PermissionHelperImpl): PermissionHelper = impl

    @Provides
    fun provideImageDetector(impl: ImageDetectorImpl): ImageDetector = impl
}
