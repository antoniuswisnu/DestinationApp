package com.example.destinationapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.destinationapp.R
import com.example.destinationapp.ui.theme.DestinationAppTheme

@Composable
fun DestinationItem(
    image: Int,
    title: String,
    description: String,
    price: Int,
    modifier: Modifier = Modifier,
) {
    Card (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onError,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ){
        Row(
            modifier = modifier
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically)
            )
            Column(
                modifier = modifier,
            ) {
                Text(
                    text = title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Text(
                    text = description,
                    maxLines = 2,
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = stringResource(R.string.price, price),
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    modifier = Modifier
                        .padding(end = 16.dp, top = 8.dp, bottom = 8.dp)
                        .align(Alignment.End)
                    ,
                    color = Color(0xFFFFAB40)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DestinationItemPreview() {
    DestinationAppTheme {
        DestinationItem(
            R.drawable.image4,
            "Gunung Bromo",
            "Gunung Bromo adalah salah satu gunung berapi aktif di Indonesia. Gunung ini terletak di Jawa Timur, lebih tepatnya berada di empat kabupaten, yakni Kabupaten Probolinggo, ",
            150000
        )
    }
}