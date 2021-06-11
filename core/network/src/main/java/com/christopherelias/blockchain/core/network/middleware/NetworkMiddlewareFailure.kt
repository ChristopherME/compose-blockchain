package com.christopherelias.blockchain.core.network.middleware

import com.christopherelias.blockchain.functional_programming.Failure

/*
 * Created by Christopher Elias on 11/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

class NetworkMiddlewareFailure(
    val middleWareExceptionMessage: String,
) : Failure.CustomFailure()