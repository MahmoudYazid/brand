package com.yazid.brand.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.flowlayout.FlowRow
import com.yazid.brand.model.ResponseItem
import com.yazid.brand.view.ui.theme.BrandTheme
import com.yazid.brand.viewModel.viewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelInst: viewModel by viewModels()
        val searchWordInput = intent.getStringExtra("word")
        setContent {
            var productData by remember {
                mutableStateOf<List<ResponseItem>?>(null)
            }
            LaunchedEffect(key1 = this, key2 = Unit) {
                viewModelInst.SearchGetAllData().let { productList ->
                    val filteredList = productList.filter {

                        if(searchWordInput?.let { it1 -> it.title?.contains(it1) } ==true){

                            true
                        } else{
                            false
                        }


                    }
                    // Update productData with the filtered list
                    productData = filteredList
                }


            }

            BrandTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = searchWordInput.toString()) },
                            navigationIcon = {
                                IconButton(
                                    onClick = {
                                        finish()

                                    }
                                ) {
                                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                                }
                            }
                        )
                    },

                    ) {it

                    LazyColumn(
                        modifier =
                        Modifier
                            .padding(it)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        item {
                            FlowRow(
                                modifier = Modifier.fillMaxSize(),
                                mainAxisSpacing = 8.dp, // Adjust spacing as needed
                                crossAxisSpacing = 8.dp // Adjust spacing between rows as needed
                            ) {
                                productData?.forEach { product ->
                                    Box(
                                        modifier = Modifier
                                            .height(300.dp)
                                            .width(200.dp) // Set a fixed width for each item
                                            .border(1.dp, Color(0xFFDEE2E7))
                                            .padding(5.dp)
                                            .background(Color.White)
                                            .clickable {
                                                val intent = Intent(this@SearchActivity, Details::class.java)
                                                intent.putExtra("item",product.id.toString())
                                                startActivity(intent)
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
                            }
                        }

                    }
                }

            }
        }
    }
}

