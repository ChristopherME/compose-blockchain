package com.christopherelias.blockchain.functional_programming

/*
 * Created by Christopher Elias on 11/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

sealed class Failure {

    /**
     * Extend this class in order to provide your own
     * custom failure.
     */
    open class CustomFailure : Failure()

    data class UnexpectedFailure(
        val message: String?
    ) : Failure()

}