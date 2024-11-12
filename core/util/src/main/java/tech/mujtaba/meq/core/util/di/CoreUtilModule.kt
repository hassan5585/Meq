package tech.mujtaba.meq.core.util.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.text.DecimalFormat
import java.util.concurrent.Executor
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor

@Module
@InstallIn(SingletonComponent::class)
internal class CoreUtilModule {
    @Provides
    @Singleton
    fun provideComputationDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @Singleton
    fun provideComputationExecutor(dispatcher: CoroutineDispatcher): Executor = dispatcher.asExecutor()

    @Provides
    @Singleton
    fun provideDecimalFormat(): DecimalFormat = DecimalFormat("###.##")
}
