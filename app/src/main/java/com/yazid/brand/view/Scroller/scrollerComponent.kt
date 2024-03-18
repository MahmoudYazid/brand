package com.yazid.brand.view.Scroller
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.yazid.brand.model.ResponseItem
import com.yazid.brand.viewModel.viewModel

@Composable
fun ScrollerComp(viewModelInst: viewModel) {
    val DataAllProducts = remember {
        mutableStateOf<List<ResponseItem>?>(
            null
        )
    }
    LaunchedEffect(key1 = Unit, block ={
        DataAllProducts.value = viewModelInst.GetAllData()
    } )

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

            DataAllProducts.value?.map{
                Box(
                    modifier =
                    Modifier
                        .fillMaxHeight()
                        .width(200.dp)
                        .border(1.dp, Color(0xFFDEE2E7))

                ){

                }
            }


        }
    }

}