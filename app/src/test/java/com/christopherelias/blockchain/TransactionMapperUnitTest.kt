package com.christopherelias.blockchain

import com.christopherelias.blockchain.features.home.data_source.model.TransactionPerSecondResponse
import com.christopherelias.blockchain.features.home.mapper.TransactionMapper
import com.christopherelias.blockchain.features.home.mapper.TransactionMapperImpl
import com.christopherelias.blockchain.ui.models.TransactionsPerSecond
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

/*
 * Created by Christopher Elias on 10/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

@ExperimentalCoroutinesApi
class TransactionMapperUnitTest {

    companion object {
        private const val MAX_TRANSACTION_VALUE = 10.0
    }

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val mapper: TransactionMapper =
        TransactionMapperImpl(testCoroutineDispatcher)

    @Test
    fun `assert REMOTE transactions per second are mapped correctly for UI`() = runBlockingTest {

        // Some fake data
        val dummyFakeRemoteTransactions = listOf(
            TransactionPerSecondResponse(
                timeStamp = 1L,
                transactionsPerSecondValue = (MAX_TRANSACTION_VALUE - 1.0)
            ),
            TransactionPerSecondResponse(
                timeStamp = 3L,
                transactionsPerSecondValue = MAX_TRANSACTION_VALUE
            ),
            TransactionPerSecondResponse(
                timeStamp = 2L,
                transactionsPerSecondValue = (MAX_TRANSACTION_VALUE - 1.5)
            ),
            TransactionPerSecondResponse(
                timeStamp = 3L,
                transactionsPerSecondValue = (MAX_TRANSACTION_VALUE - 2.0)
            )
        )

        val uiTransactionObject: TransactionsPerSecond =
            mapper.mapRemoteTransactionsToUi(dummyFakeRemoteTransactions)

        assertEquals(
            "Max transaction value is not mapped correctly",
            MAX_TRANSACTION_VALUE,
            uiTransactionObject.maxTransaction,
            0.001
        )

        assertEquals(
            "UI Transactions mapped does not have the same size of the remote list",
            dummyFakeRemoteTransactions.size,
            uiTransactionObject.transactions.size
        )

        val thirdRemoteItem = dummyFakeRemoteTransactions[2]
        val thirdUiItem = uiTransactionObject.transactions[2]

        assertEquals(
            "UI transaction mapped does not have the same timeStamp mark from its remote pair",
            thirdRemoteItem.timeStamp,
            thirdUiItem.timeStamp
        )

        assertEquals(
            "UI transaction mapped does not have the same transaction rate value from its remote pair",
            thirdRemoteItem.transactionsPerSecondValue,
            thirdUiItem.transactionsPerSecondValue,
            0.001
        )

    }
}