package com.yazid.brand.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gowtham.ratingbar.RatingBar
import com.yazid.brand.R
import com.yazid.brand.model.DBClassItem
import com.yazid.brand.model.ResponseItem
import com.yazid.brand.view.ScrollerTitle.ScrollerTitle
import com.yazid.brand.view.similarScroller.similarScroller
import com.yazid.brand.view.ui.theme.BrandTheme
import com.yazid.brand.viewModel.viewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Details : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelInst:viewModel by viewModels();
        val id = intent.getStringExtra("item")

        setContent {
            val coroutineScopeInst = rememberCoroutineScope()
            val context_ = LocalContext.current
            var ProductData by remember {
                mutableStateOf<ResponseItem?>(null)
            }
            var ProductinCart by remember {
                mutableStateOf<Int>(0)
            }
            val Rating: Float? = ProductData?.rating?.rate?.toString()?.toFloatOrNull()

            LaunchedEffect(key1 = this, key2 =Unit ) {
                ProductData = viewModelInst.getSpecificId(id.toString())
            }

            BrandTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "") },
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
                    modifier = Modifier
                        .background(Color(0xFFEFF2F4))

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
                            AsyncImage(
                                model =ProductData?.image.toString(),
                                contentDescription =null,
                                modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .height(400.dp)
                                )
                        }

                        item {
                            Row(
                                modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .height(30.dp)
                                    .padding(horizontal = 20.dp)

                                ,
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically

                            ) {

                                RatingBar(
                                    value = if (Rating == null) 0F else Rating,
                                    onValueChange = {
                                    },
                                    onRatingChanged = {
                                    }
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Image(painter = painterResource(id = R.drawable.dot) ,
                                    contentDescription =null,
                                    modifier =  Modifier

                                        .size(15.dp),
                                    colorFilter = ColorFilter.tint(Color(0xFFDBDBDB))

                                    )
                                Spacer(modifier = Modifier.width(10.dp))
                                Image(painter = painterResource(id = R.drawable.message) ,
                                    contentDescription =null,
                                    modifier =  Modifier
                                        .clickable {
                                            Toast.makeText(context_,"the comments not available",Toast.LENGTH_LONG).show()
                                        }

                                        .size(30.dp),
                                    colorFilter = ColorFilter.tint(Color(0xFFDBDBDB))

                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text = "${ProductData?.rating?.count.toString()} reviews",
                                    style = TextStyle(
                                        color = Color(0xFF787A80),
                                        fontSize = 20.sp

                                    ),
                                    modifier =
                                        Modifier
                                            .clickable {
                                                Toast.makeText(context_,"the comments not available",Toast.LENGTH_LONG).show()

                                            }
                                )

                            }




                            }

                        item {
                            Row(
                                modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(horizontal = 20.dp)

                                ,
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = ProductData?.title.toString(),
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 30.sp
                                    ),


                                    )


                            }
                        }
                        item {
                            Row(
                                modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(horizontal = 20.dp)

                                ,
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            )  {
                                Text(
                                    text = "$${ProductData?.price.toString()}",
                                    style = TextStyle(
                                        color = Color.Red,
                                        fontSize = 30.sp
                                    )
                                )
                            }
                        }
                        item {
                            Row (
                                modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(horizontal = 20.dp)

                                ,
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                OutlinedButton(
                                    onClick = {
                                        ProductinCart.plus(1)
                                        coroutineScopeInst.launch {
                                            viewModelInst.InsertProductInCart(DBClassItem(

                                                image = ProductData?.image.toString(),
                                                price = ProductData?.price.toString(),
                                                description = ProductData?.description.toString(),
                                                id = null,
                                                title = ProductData?.title.toString(),
                                                category = ProductData?.category.toString(),
                                                rate = ProductData?.rating?.rate.toString(),
                                                count = ProductData?.rating?.count.toString(),
                                                productId = ProductData?.id.toString()
                                            ))
                                        }

                                    },
                                    modifier =
                                    Modifier
                                        .clip(RoundedCornerShape(5.dp))
                                        .border(
                                            2.dp,
                                            Color(0xFF0D6EFD),
                                            shape = RoundedCornerShape(5.dp)
                                        ) // Set border color to black

                                        .fillMaxWidth()
                                        .height(40.dp)
                                        .background(Color(0xFF0D6EFD))

                                ) {
                                    Text(
                                        text = " Add in cart",
                                        color = Color.White,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                        item {
                            Row(
                                modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .padding(horizontal = 20.dp)

                                ,
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = ProductData?.description.toString(),
                                    style = TextStyle(
                                        fontSize = 21.sp,
                                        color = Color(0xFF505050)
                                    ),



                                    )


                            }
                        }
                        
                        item { 
                            ScrollerTitle(Title = "Similar products")
                        }
                        item {
                            similarScroller(CatigoryString = ProductData?.category.toString(), viewModelInst = viewModelInst )
                        }
                        item {
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                        }

                    }
                }

            }
        }
    }

