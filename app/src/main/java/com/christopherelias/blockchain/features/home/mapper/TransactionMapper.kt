package com.christopherelias.blockchain.features.home.mapper

import com.christopherelias.blockchain.features.home.data_source.model.TransactionPerSecondResponse
import com.christopherelias.blockchain.model.TransactionsPerSecond


/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

interface TransactionMapper {

    suspend fun mapRemoteTransactionsToUi(
        transactionsRemote: List<TransactionPerSecondResponse>
    ): TransactionsPerSecond
}