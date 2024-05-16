package com.jgomez.cabifytest.di

import com.feature.onboarding.navigation.OnboardingNavigation
import com.feature.ui.navigation.ProductNavigation
import com.jgomez.cabifytest.navigation.NavigationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideNavigationProvides(productNavigation: ProductNavigation, onboardingNavigation: OnboardingNavigation): NavigationProvider {
        return NavigationProvider(productNavigation, onboardingNavigation)
    }
}