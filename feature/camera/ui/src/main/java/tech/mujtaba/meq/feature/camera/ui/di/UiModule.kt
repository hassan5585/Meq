package tech.mujtaba.meq.feature.camera.ui.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import tech.mujtaba.meq.feature.camera.ui.image.DimensionEstimator
import tech.mujtaba.meq.feature.camera.ui.image.DimensionEstimatorImpl

@Module
@InstallIn(SingletonComponent::class)
internal class UiModule {

    @Provides
    @Singleton
    fun provideEstimator(impl: DimensionEstimatorImpl): DimensionEstimator = impl
}
