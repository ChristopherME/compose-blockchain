package com.christopherelias.blockchain.features.home.data.data_source

import com.christopherelias.blockchain.core.functional_programming.Either
import com.christopherelias.blockchain.core.functional_programming.Failure
import com.christopherelias.blockchain.features.home.data_source.model.TransactionPerSecondResponse

/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

interface HomeRemoteDataSource {

    suspend fun getTransactionsPerSecond(): Either<Failure, List<TransactionPerSecondResponse>>
}