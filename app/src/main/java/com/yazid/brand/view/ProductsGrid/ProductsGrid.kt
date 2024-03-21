package com.yazid.brand.view.ProductsGrid

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImage
import com.google.accompanist.flowlayout.FlowRow
import com.yazid.brand.model.ResponseItem
import com.yazid.brand.view.Details
import com.yazid.brand.viewModel.viewModel
import kotlinx.coroutines.launch

@Composable
fun ProductsGrid(viewModelInst: viewModel) {
    var context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var limit by remember {
        mutableStateOf<Int>(4)
    }
    var productData by remember {
        mutableStateOf<List<ResponseItem>?>(null)
    }
    LaunchedEffect(key1 = context, key2 =Unit ) {
        productData = viewModelInst.LimitGetData(4)

    }

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        FlowRow(
            modifier = Modifier.fillMaxSize(),
            mainAxisSpacing = 8.dp, // Adjust spacing as needed
            crossAxisSpacing = 8.dp // Adjust spacing between rows as needed
        ) {
            var context = LocalContext.current
            productData?.forEach { product ->
                Box(
                    modifier = Modifier
                        .height(300.dp)
                        .width(200.dp) // Set a fixed width for each item
                        .border(1.dp, Color(0xFFDEE2E7))
                        .padding(5.dp)
                        .background(Color.White)
                        .clickable {
                            val intent = Intent(context, Details::class.java)
                            intent.putExtra("item", product.id.toString())
                            context.startActivity(intent)
                        }
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Assuming AsyncImage is a custom composable to load images asynchronously
                        AsyncImage(
                            model = product.image.toString(),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                        )
                        Text(
                            text = product.title.toString(),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedButton(
                    onClick = {
                        limit = limit + 4

                        coroutineScope.launch {
                            productData = viewModelInst.LimitGetData(limit)
                        }

                    },
                    modifier =
                    Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .border(
                            2.dp,
                            Color(0xFF0D6EFD),
                            shape = RoundedCornerShape(20.dp)
                        ) // Set border color to black

                        .width(150.dp)
                        .height(40.dp)
                        .background(Color(0xFF0D6EFD))

                ) {
                    Text(
                        text = " more",
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
