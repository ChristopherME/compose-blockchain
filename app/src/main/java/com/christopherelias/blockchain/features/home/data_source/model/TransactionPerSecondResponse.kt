package com.christopherelias.blockchain.features.home.data_source.model

import com.squareup.moshi.Json

/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

data class TransactionPerSecondResponse(
    @field:Json(name = "x") val timeStamp: Double,
    @field:Json(name = "y") val transactionsPerSecondValue: Double
)