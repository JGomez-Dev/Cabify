package com.core.common.error.di

import com.core.common.error.GlobalErrorManager
import com.core.common.error.GlobalErrorMapper
import com.core.common.error.GlobalErrorType
import com.core.common.error.SafeFlowUseCaseDelegate
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.io.IOException
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CommonErrorModule {

    @Provides
    @Singleton
    fun provideGlobalErrorManager(defaultErrorMapper: GlobalErrorMapper): GlobalErrorManager {
        return GlobalErrorManager(defaultErrorMapper)
    }

    @Provides
    @Singleton
    fun provideSafeFlowUseCaseDelegate(globalErrorManager: GlobalErrorManager): SafeFlowUseCaseDelegate {
        return object : SafeFlowUseCaseDelegate {
            override val globalErrorManager: GlobalErrorManager = globalErrorManager
        }
    }

    @Provides
    @Singleton
    fun provideGlobalErrorMapperImpl(): GlobalErrorMapper {
        return object : GlobalErrorMapper {
            override fun map(throwable: Throwable): GlobalErrorType {
                if (throwable is IOException) {
                    return GlobalErrorType.NETWORK_UNAVAILABLE
                }
                return GlobalErrorType.GENERIC_ERROR
            }
        }
    }
}