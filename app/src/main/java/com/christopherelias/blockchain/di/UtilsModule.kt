package com.christopherelias.blockchain.di

import com.christopherelias.blockchain.utils.connectivity.ConnectivityUtils
import com.christopherelias.blockchain.utils.resource_provider.ResourceProvider
import com.christopherelias.blockchain.utils_impl.connectivity.ConnectivityUtilsImpl
import com.christopherelias.blockchain.utils_impl.resource_provider.ResourceProviderImpl
import dagger.Binds
import dagger.Module
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
abstract class UtilsModule {

    @Binds
    @Singleton
    abstract fun provideConnectivityUtils(
        connectivityUtilsImpl: ConnectivityUtilsImpl
    ): ConnectivityUtils

    @Binds
    @Singleton
    abstract fun provideResourceProviderUtils(
        resourceProviderImpl: ResourceProviderImpl
    ): ResourceProvider
}