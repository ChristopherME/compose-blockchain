package com.christopherelias.blockchain.core.network.models

import com.squareup.moshi.Json

/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

data class ResponseError(
    @field:Json(name = "status") val status: String,
    @field:Json(name = "error") val error: String?
)