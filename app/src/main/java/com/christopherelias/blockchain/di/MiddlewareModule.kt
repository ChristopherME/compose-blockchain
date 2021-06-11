package com.christopherelias.blockchain.di

import com.christopherelias.blockchain.core.network.middleware.provider.MiddlewareProvider
import com.christopherelias.blockchain.middlewares.MiddlewareProviderImpl
import com.christopherelias.blockchain.middlewares.ConnectivityMiddleware
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

@Module
@InstallIn(SingletonComponent::class)
object MiddlewareModule {

    @Provides
    @Singleton
    fun provideMiddlewares(
        connectivityMiddleware: ConnectivityMiddleware
    ): MiddlewareProvider {
        return MiddlewareProviderImpl.Builder()
            .add(connectivityMiddleware)
            .build()
    }
}