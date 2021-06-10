package com.christopherelias.blockchain.features.home.di

import com.christopherelias.blockchain.features.home.data_source.remote.TransactionsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

@Module
@InstallIn(ViewModelComponent::class)
internal class HomeNetworkModule {

    @Provides
    internal fun provideTransactionService(
        retrofit: Retrofit
    ): TransactionsService = retrofit.create(TransactionsService::class.java)
}