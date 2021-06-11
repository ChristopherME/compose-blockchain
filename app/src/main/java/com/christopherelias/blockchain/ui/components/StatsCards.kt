package com.christopherelias.blockchain.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.christopherelias.blockchain.ui.models.Stats

/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

@Composable
fun StatsHorizontalList(
    stats: List<Stats>
) {
    Column {
        Text(
            text = "Popular Stats",
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        )
        LazyRow {
            items(stats) { statItem ->
                StatsCardItem(
                    title = statItem.title,
                    content = statItem.content,
                    description = statItem.description,
                )
            }
        }
    }
}

@Composable
fun StatsCardItem(
    title: String,
    content: String,
    description: String
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        elevation = 12.dp,
        modifier = Modifier
            .padding(8.dp)
            .width(200.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(6.dp)
            )
            Text(
                text = content,
                style = MaterialTheme.typography.h6,
                color = Color(40, 193, 218),
                modifier = Modifier.padding(6.dp)
            )
            Text(
                text = description,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(6.dp),
                textAlign = TextAlign.Center
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun StatCardPreview() {
    StatsHorizontalList(
        stats = listOf(
            Stats(
                "Market Price",
                "$36,980.20",
                "The avarage USD market price acorss major bitcoin exchanges"
            ),
            Stats(
                "Market Price",
                "$36,980.20",
                "The avarage USD market price acorss major bitcoin exchanges"
            )
        )
    )
}