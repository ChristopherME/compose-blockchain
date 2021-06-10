package com.christopherelias.blockchain.core.network

import okhttp3.OkHttpClient

/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

// Add this class as internal when modularize the project.
class HttpClientFactory {

    private val httpClient by lazy {

        OkHttpClient()
    }

    fun create(): OkHttpClient.Builder {
        return httpClient.newBuilder()
    }
}