package com.yazid.brand.view.Scroller
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import coil.compose.AsyncImage
import com.yazid.brand.model.ResponseItem
import com.yazid.brand.view.Details
import com.yazid.brand.viewModel.viewModel

@Composable
fun ScrollerComp(viewModelInst: viewModel) {


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
            viewModelInst.ProductDataViewModelLiveData.observeAsState().value?.map {
                Box(
                    modifier =
                    Modifier
                        .fillMaxHeight()
                        .width(200.dp)
                        .border(1.dp, Color(0xFFDEE2E7))
                        .clickable {
                            val intent = Intent(context, Details::class.java)
                            intent.putExtra("item",it.id)
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