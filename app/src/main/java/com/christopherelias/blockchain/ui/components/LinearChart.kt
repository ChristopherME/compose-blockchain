package com.christopherelias.blockchain.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.christopherelias.blockchain.ui.models.TransactionsPerSecond

/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

@Composable
fun CurrencyStatistics(
    transactionsPerSecond: TransactionsPerSecond
) {
    Column {
        Text(
            text = "Currency Statistics",
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        )
        TransactionsPerSecondComposable(transactionsPerSecond = transactionsPerSecond)
    }
}

@Composable
fun TransactionsPerSecondComposable(
    transactionsPerSecond: TransactionsPerSecond,
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        elevation = 12.dp,
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Transactions Per Second",
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            )
            LinearTransactionsChart(
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .padding(12.dp),
                transactionsPerSecond = transactionsPerSecond
            )
        }
    }
}

@Composable
fun LinearTransactionsChart(
    modifier: Modifier = Modifier,
    transactionsPerSecond: TransactionsPerSecond
) {
    if (transactionsPerSecond.transactions.isEmpty()) return

    Canvas(modifier = modifier) {
        // Total number of transactions.
        val totalRecords = transactionsPerSecond.transactions.size

        // Maximum distance between dots (transactions)
        val lineDistance = size.width / (totalRecords + 1)

        // Canvas height
        val cHeight = size.height

        // Add some kind of a "Padding" for the initial point where the line starts.
        var currentLineDistance = 0F + lineDistance

        transactionsPerSecond.transactions.forEachIndexed { index, transactionRate ->
            if (transactionsPerSecond.transactions.getOrNull(index = index + 1) != null) {
                drawLine(
                    start = Offset(
                        x = currentLineDistance,
                        y = calculateYCoordinate(
                            higherTransactionRateValue = transactionsPerSecond.maxTransaction,
                            currentTransactionRate = transactionRate.transactionsPerSecondValue,
                            canvasHeight = cHeight
                        )
                    ),
                    end = Offset(
                        x = currentLineDistance + lineDistance,
                        y = calculateYCoordinate(
                            higherTransactionRateValue = transactionsPerSecond.maxTransaction,
                            currentTransactionRate = transactionsPerSecond.transactions[index + 1].transactionsPerSecondValue,
                            canvasHeight = cHeight
                        )
                    ),
                    color = Color(40, 193, 218),
                    strokeWidth = Stroke.DefaultMiter
                )
            }
            currentLineDistance += lineDistance
        }
    }
}

/**
 * Calculates the Y pixel coordinate for a given transaction rate.
 *
 * @param higherTransactionRateValue the highest rate value in the whole list of transactions.
 * @param currentTransactionRate the current transaction RATE while iterating the list of transactions.
 * @param canvasHeight the canvas HEIGHT for draw the linear chart.
 *
 * @return [Float] Y coordinate for a transaction rate.
 */
private fun calculateYCoordinate(
    higherTransactionRateValue: Double,
    currentTransactionRate: Double,
    canvasHeight: Float
): Float {
    val maxAndCurrentValueDifference = (higherTransactionRateValue - currentTransactionRate)
        .toFloat()
    val relativePercentageOfScreen = (canvasHeight / higherTransactionRateValue)
        .toFloat()
    return maxAndCurrentValueDifference * relativePercentageOfScreen
}