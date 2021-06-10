package com.christopherelias.blockchain

import com.christopherelias.blockchain.model.TransactionRate
import com.christopherelias.blockchain.model.TransactionsPerSecond

object MockTransactions {


    fun previewTransactions(): TransactionsPerSecond {
        return TransactionsPerSecond(
            maxTransaction = 100.00,
            transactions = listOf(
                TransactionRate(
                    timeStamp = 0.0,
                    transactionsPerSecondValue = 50.0
                ),
                TransactionRate(
                    timeStamp = 0.0,
                    transactionsPerSecondValue = 20.0
                ),

                TransactionRate(
                    timeStamp = 0.0,
                    transactionsPerSecondValue = 60.0
                ),
                TransactionRate(
                    timeStamp = 0.0,
                    transactionsPerSecondValue = 30.0
                )
            )
        )
    }
}