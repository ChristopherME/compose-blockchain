package com.christopherelias.blockchain.features.home.di

import com.christopherelias.blockchain.features.home.data.data_source.HomeRemoteDataSource
import com.christopherelias.blockchain.features.home.data.repository.HomeRepositoryImpl
import com.christopherelias.blockchain.features.home.data_source.remote.HomeRemoteDataSourceImpl
import com.christopherelias.blockchain.features.home.domain.repository.HomeRepository
import com.christopherelias.blockchain.features.home.mapper.TransactionMapper
import com.christopherelias.blockchain.features.home.mapper.TransactionMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

@Module(includes = [HomeNetworkModule::class])
@InstallIn(ViewModelComponent::class)
abstract class HomeFeatureModule {

    @Binds
    internal abstract fun provideHomeRemoteDataSource(
        homeRemoteDataSourceImpl: HomeRemoteDataSourceImpl
    ): HomeRemoteDataSource

    @Binds
    internal abstract fun provideTransactionMapper(
        transactionMapperImpl: TransactionMapperImpl
    ): TransactionMapper

    @Binds
    internal abstract fun provideHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository

}