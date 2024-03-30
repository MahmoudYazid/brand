package com.yazid.brand.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.yazid.brand.model.DBClassItem
import com.yazid.brand.view.ui.theme.BrandTheme
import com.yazid.brand.viewModel.viewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint

class ShoppingCart : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelInst: viewModel by viewModels()

        setContent {
            var CartData by remember {
                mutableStateOf<List<DBClassItem>?>(null)
            }
            var sumOfCart by remember {
                mutableStateOf<Float>(0.0F)
            }
            var totalOfCart by remember {
                mutableStateOf<Float>(0.0F)
            }
            LaunchedEffect(key1 = this, key2 = Unit) {
                CartData = viewModelInst.getProductsFromCart()
                sumOfCart = viewModelInst.sumOfItems(CartData!!).toFloat()
                totalOfCart = viewModelInst.TotalOfItem(sumOfCart).toFloat()



            }
            BrandTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Cart") },
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

                ){it
                    val coroutine = rememberCoroutineScope()
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                            .verticalScroll(rememberScrollState())
                    ) {
                        CartData?.map {

                            CustomCard(

                                it.image.toString(),
                                it.title.toString(),
                                it.price.toString(),
                                viewModelInst,
                                it


                            ) {
                                coroutine.launch {
                                    CartData = viewModelInst.getProductsFromCart()
                                    sumOfCart = viewModelInst.sumOfItems(CartData!!).toFloat()
                                    totalOfCart = viewModelInst.TotalOfItem(sumOfCart).toFloat()

                                }

                            }

                        }
                        TotalPaymentBox(
                            CartData,
                            sumOfCart,
                            totalOfCart

                        )


                        }


                }
            }
        }
    }
}
@Composable

fun CustomCard(

    ImageInput:String ,TitleInput:String,Price:String,viewModelInst: viewModel , item: DBClassItem,RefreshData: () -> Unit){
    Column(
        modifier =
        Modifier
            .height(200.dp)
            .fillMaxWidth()
            .background(Color.Black)
            .border(1.dp, Color(0xFFDEE2E7))
    ) {
        Row(
            modifier =
            Modifier
                .background(Color.White)
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .weight(3F),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ){

            FirstRowContent(ImageInput,TitleInput,viewModelInst,item,RefreshData)


        }
        Row(
            modifier =
            Modifier
                .background(Color.White)
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .weight(1F),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ){

            Text(

                text = Price +"$"

             ,       style = TextStyle(
                    fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            )
        }
    }
}


@Composable
fun FirstRowContent(ImageInput: String, TitleInput: String,viewModelInst: viewModel , item: DBClassItem,RefreshData: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier =
        Modifier
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = ImageInput,
            contentDescription = null,
            modifier =
            Modifier
                .width(100.dp)
                .height(100.dp)
        )

        Text(
            text = TitleInput,
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.weight(1f)
        )

        ThreeDots(viewModelInst,item,RefreshData)
    }
}

@Composable
fun ThreeDots(viewModelInst: viewModel , item: DBClassItem, RefreshData: () -> Unit) {

    var expandedSignal by remember {
        mutableStateOf(false)
    }
    val coroutiene = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .size(48.dp) // Fixed size or any other size constraint
    ) {
        IconButton(
            onClick = {
                expandedSignal = !expandedSignal
            },
            modifier = Modifier
                .size(48.dp) // Same size as Box for consistency
        ) {
            Icon(Icons.Filled.MoreVert, contentDescription = "Back")
        }

        DropdownMenu(
            expanded = expandedSignal,
            onDismissRequest = { expandedSignal = false }
        ) {
            DropdownMenuItem(
                text = { Text("Delete") },
                onClick = {
                    coroutiene.launch {
                        viewModelInst.DeleteProductFromCart(item)
                        RefreshData()

                    }

                }
            )
            DropdownMenuItem(
                text = { Text("Close") },
                onClick = {
                    expandedSignal= false
                }
            )

        }
    }
}

@Composable
fun TotalPaymentBox(CartData:List<DBClassItem>?, sum: Float, total: Float){
    Box(
        modifier =
        Modifier
            .fillMaxWidth()
            .height(250.dp)

    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier =
                    Modifier
                        .weight(2F)

            ) {




                Column(
                    modifier =
                    Modifier
                        .weight(1F)
                        .background(Color.White)
                        .fillMaxHeight()
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                        horizontalAlignment = Alignment.Start

                ) {
                    Text(text = "items" + " "+"("+CartData?.size.toString()+")",
                        style = TextStyle(
                            color = Color(0xFF8B96A5)
                            ,
                            fontWeight = FontWeight.Bold
                            ,
                            fontSize = 20.sp
                        )
                    )
                    Spacer(
                        modifier =
                        Modifier
                            .height(10.dp)

                    )
                    Text(text = "Shipping :",
                        style = TextStyle(
                            color =Color(0xFF8B96A5)
                            ,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    )
                    Spacer(
                        modifier =
                        Modifier
                            .height(10.dp)

                    )
                    Text(text = "Tax :",
                        style = TextStyle(
                            color = Color(0xFF8B96A5),
                            fontWeight = FontWeight.Bold
                            ,
                            fontSize = 20.sp
                        )
                    )

                    Spacer(
                        modifier =
                        Modifier
                            .height(10.dp)

                    )
                    Text(text = "Total :",
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                            ,
                            fontSize = 20.sp
                        )
                    )

                }

                Column(
                    modifier =
                    Modifier
                        .weight(1F)
                        .background(Color.White)
                        .fillMaxHeight()
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    horizontalAlignment = Alignment.End

                ) {
                    Text(text =sum.toInt().toString(),
                        style = TextStyle(
                            color = Color(0xFF8B96A5)
                            ,
                            fontWeight = FontWeight.Bold
                            ,
                            fontSize = 20.sp
                        )
                    )
                    Spacer(
                        modifier =
                        Modifier
                            .height(10.dp)

                    )
                    Text(text = "7$",
                        style = TextStyle(
                            color =Color(0xFF8B96A5)
                            ,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    )
                    Spacer(
                        modifier =
                        Modifier
                            .height(10.dp)

                    )
                    Text(text = "7$",
                        style = TextStyle(
                            color = Color(0xFF8B96A5),
                            fontWeight = FontWeight.Bold
                            ,
                            fontSize = 20.sp
                        )
                    )

                    Spacer(
                        modifier =
                        Modifier
                            .height(10.dp)

                    )
                    Text(text = total.toInt().toString() + "$" ,
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                            ,
                            fontSize = 20.sp
                        )
                    )

                }

            }
            Row(
                modifier =
                Modifier
                    .weight(1F)
                    .padding(15.dp)

            ) {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00B517)) ,
                    modifier = Modifier.fillMaxSize()

                ) {
                    Text(text = "Checkout" +" "+"("+CartData?.size.toString()+" "+"items"+")",
                        style = TextStyle(
                            color = Color.White
                        )
                        )
                    
                }
            }

        }

    }
}


