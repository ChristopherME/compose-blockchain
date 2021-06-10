package com.christopherelias.blockchain.features.home.domain.repository

import com.christopherelias.blockchain.core.functional_programming.Either
import com.christopherelias.blockchain.core.functional_programming.Failure
import com.christopherelias.blockchain.model.TransactionsPerSecond

/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

interface HomeRepository {
    suspend fun getTransactionsPerSecond(): Either<Failure, TransactionsPerSecond>
}