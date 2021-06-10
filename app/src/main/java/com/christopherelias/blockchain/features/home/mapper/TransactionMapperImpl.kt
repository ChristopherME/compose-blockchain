package com.christopherelias.blockchain.features.home.mapper

import com.christopherelias.blockchain.di.DefaultDispatcher
import com.christopherelias.blockchain.features.home.data_source.model.TransactionPerSecondResponse
import com.christopherelias.blockchain.model.TransactionRate
import com.christopherelias.blockchain.model.TransactionsPerSecond
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

class TransactionMapperImpl @Inject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : TransactionMapper {

    override suspend fun mapRemoteTransactionsToUi(
        transactionsRemote: List<TransactionPerSecondResponse>
    ): TransactionsPerSecond {
        return withContext(defaultDispatcher) {

            var maxRateValue = 0.0

            val uiTransactions = transactionsRemote.mapIndexed { index, remoteTransaction ->
                // Find maximum rate value
                if (transactionsRemote[index].transactionsPerSecondValue >= maxRateValue) {
                    maxRateValue = transactionsRemote[index].transactionsPerSecondValue
                }

                // Map items
                TransactionRate(
                    timeStamp = remoteTransaction.timeStamp,
                    transactionsPerSecondValue = remoteTransaction.transactionsPerSecondValue
                )
            }

            return@withContext TransactionsPerSecond(maxRateValue, uiTransactions)
        }
    }
}