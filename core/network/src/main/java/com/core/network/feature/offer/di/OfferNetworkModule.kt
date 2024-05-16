package com.core.network.feature.offer.di

import android.content.Context
import com.core.network.feature.offer.repository.OfferDataProviders
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object OfferNetworkModule {
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
    @Provides
    fun provideOfferDataProviders(gson: Gson, @ApplicationContext context: Context): OfferDataProviders {
        return OfferDataProviders(gson, context)
    }

}