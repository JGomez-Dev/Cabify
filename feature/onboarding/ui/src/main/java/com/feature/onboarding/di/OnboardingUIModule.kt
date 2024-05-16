package com.feature.onboarding.di

import com.feature.onboarding.navigation.OnboardingNavigation
import com.feature.onboarding.navigation.OnboardingNavigationImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object OnboardingUIModule {

    @Provides
    fun provideOnboardingNavigation(): OnboardingNavigation {
        return OnboardingNavigationImpl()
    }
}