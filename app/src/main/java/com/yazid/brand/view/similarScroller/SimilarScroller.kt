package com.yazid.brand.view.similarScroller

import android.content.Intent
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yazid.brand.model.ResponseItem
import com.yazid.brand.view.Details
import com.yazid.brand.viewModel.viewModel

@Composable
fun similarScroller(CatigoryString:String,viewModelInst:viewModel){
    val context = LocalContext.current
    var productData by remember {
        mutableStateOf<List<ResponseItem>?>(null)
    }
    LaunchedEffect(key1 = context, key2 = Unit) {
        productData = viewModelInst.getSpecificCatigoriesData(CatigoryString)



    }
    Box (
        modifier =
        Modifier
            .fillMaxWidth()
            .height(250.dp)
    ){

        Row (
            modifier =
            Modifier
                .horizontalScroll(rememberScrollState())
                .fillMaxSize(),




            ){
            var context= LocalContext.current
            productData?.map {
                Box(
                    modifier =
                    Modifier
                        .fillMaxHeight()
                        .width(200.dp)
                        .border(1.dp, Color(0xFFDEE2E7))
                        .clickable {
                            val intent = Intent(context, Details::class.java)
                            intent.putExtra("item",it.id.toString())
                            context.startActivity(intent)
                        }

                ){
                    Column(
                        modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(5.dp)
                    ) {
                        AsyncImage(
                            model = it.image.toString(),
                            contentDescription = null,
                            modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(180.dp)

                        )
                        Text(text = it.title.toString(),

                            modifier =
                            Modifier
                                .fillMaxWidth()

                            ,
                            textAlign = TextAlign.Center,
                            overflow = TextOverflow.Ellipsis

                        )

                    }

                }
            }


        }
    }

}