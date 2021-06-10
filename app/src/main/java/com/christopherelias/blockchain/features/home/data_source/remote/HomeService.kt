package com.christopherelias.blockchain.features.home.data_source.remote

import com.christopherelias.blockchain.core.network.models.ResponseList
import com.christopherelias.blockchain.features.home.data_source.model.TransactionPerSecondResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

interface HomeService {

    @GET("charts/{chartName}")
    suspend fun getTransactionsPerSecond(
        @Path("chartName") chartName: String,
        @Query("timespan") timeSpan: String,
        @Query("rollingAverage") rollingAverage: String
    ): ResponseList<TransactionPerSecondResponse>
}