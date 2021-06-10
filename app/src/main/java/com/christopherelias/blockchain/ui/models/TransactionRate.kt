package com.christopherelias.blockchain.ui.models

/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

/**
 * Represents a transaction per day object.
 * @param timeStamp the time stamp of the transaction. TODO: We have to convert this to date at some point.
 * @param transactionsPerSecondValue the quantity of transactions made per day.
 */
data class TransactionRate(
    val timeStamp: Long,
    val transactionsPerSecondValue: Double
)

/**
 * Represents a list of Transaction Rate Per Second
 * The number of transactions added to the mempool per second.
 */
data class TransactionsPerSecond(
    val maxTransaction: Double,
    val transactions: List<TransactionRate>
) {
    companion object {
        fun idle(): TransactionsPerSecond {
            return TransactionsPerSecond(0.0, emptyList())
        }
    }
}