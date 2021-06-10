package com.christopherelias.blockchain.di

import com.christopherelias.blockchain.BuildConfig
import com.christopherelias.blockchain.core.network.HttpClientFactory
import com.christopherelias.blockchain.core.network.models.ResponseError
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    internal fun provideOkHttpBuilder(
        httpClientFactory: HttpClientFactory
    ): OkHttpClient.Builder {
        return httpClientFactory.create()
    }

    @Provides
    @Singleton
    internal fun provideClient(
        clientBuilder: OkHttpClient.Builder
    ): OkHttpClient {
        clientBuilder
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(loggingInterceptor)
        }

        return clientBuilder.build()
    }


    // TODO : Add BuildConfig for change this.
    @Provides
    @Singleton
    fun provideRetrofitBuilder(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.blockchain.info/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    internal fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    fun provideJsonErrorAdapter(moshi: Moshi): JsonAdapter<ResponseError> {
        return moshi.adapter(ResponseError::class.java)
    }
}