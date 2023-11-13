package com.example.destinationapp.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.destinationapp.R
import com.example.destinationapp.data.Discount
import com.example.destinationapp.data.dummyCategory
import com.example.destinationapp.data.dummyDiscount
import com.example.destinationapp.ui.components.CategoryItem
import com.example.destinationapp.ui.components.DiscountItem
import com.example.destinationapp.ui.components.Search

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column {
        Banner()
        SectionText(stringResource(R.string.section_category))
        CategoryRow()
        SectionText(stringResource(R.string.section_discount))
        DiscountRow(dummyDiscount)
        SectionText(stringResource(R.string.section_destination))
        ContentText(stringResource(R.string.content_destination))
        Button(
            onClick = {
                navController.navigate("destination")
            },
            content = {
                Text(
                    text = stringResource(R.string.button_navigation),
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue, contentColor = Color.White),
            modifier = Modifier.padding(horizontal = 16.dp),
        )
    }
}

@Composable
fun Banner(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.banner),
            contentDescription = "Banner Image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(170.dp)
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        Search()

    }
}

@Composable
fun CategoryRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(16.dp)
    ) {
        items(dummyCategory, key = { it.textCategory }) { category ->
            CategoryItem(category)
        }
    }
}

@Composable
fun SectionText(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall.copy(
            fontWeight = FontWeight.ExtraBold
        ),
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 2.dp)
    )
}

@Composable
fun ContentText(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        fontSize = 14.sp,
        style = MaterialTheme.typography.headlineSmall.copy(
            fontWeight = FontWeight.Light

        ),
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 2.dp)
    )
}

@Composable
fun DiscountRow(
    listDiscount: List<Discount>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(listDiscount, key = { it.title }) { menu ->
            DiscountItem(menu)
        }
    }
}
@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen(
        navController = NavController(LocalContext.current)
    )
}