package com.christopherelias.blockchain.core.network.models

import com.christopherelias.blockchain.functional_programming.Failure

/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

class NetworkMiddlewareFailure(
    val middleWareExceptionMessage: String,
) : Failure.CustomFailure()

object TimeOut : Failure.CustomFailure()

object NetworkConnectionLostSuddenly : Failure.CustomFailure()

object SSLError : Failure.CustomFailure()

/**
 * If your service return some custom error use this with the given attars you expect.
 */
data class ServiceBodyFailure(
    val internalStatus: String,
    val internalMessage: String?
) : Failure.CustomFailure()
