package com.christopherelias.blockchain.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

@Composable
fun StatsCard(
    title: String,
    content: String,
    description: String,
    modifier: Modifier
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        elevation = 12.dp,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                modifier = modifier.padding(6.dp)
            )
            Text(
                text = content,
                style = MaterialTheme.typography.h4,
                modifier = modifier.padding(6.dp)
            )
            Text(
                text = description,
                modifier = modifier.padding(6.dp),
                textAlign = TextAlign.Center
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun StatCardPreview() {
    StatsCard(
        title = "Market Price(USD)",
        content = "$36,980.20",
        description = "The average USD market price across major bitcoin exchanges.",
        modifier = Modifier
    )
}