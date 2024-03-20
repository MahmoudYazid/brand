package com.yazid.brand.view.categoryScroller
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yazid.brand.view.CategoryActivity
import com.yazid.brand.viewModel.viewModel
import kotlinx.coroutines.delay
import java.time.Duration
import java.util.logging.Handler

@Composable
fun categoryScroller(viewModelInst: viewModel) {

    val context = LocalContext.current
    Box (
        modifier =
        Modifier
            .fillMaxWidth()
            .height(50.dp)
    ){

        Row (
            modifier =
            Modifier
                .horizontalScroll(rememberScrollState())
                .fillMaxSize(),




        ){

            viewModelInst.CatigoryDataViewModelLiveData.observeAsState().value?.map {
                Spacer(modifier = Modifier.width(5.dp))
                Box(
                    modifier =
                    Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .border(1.dp, Color(0xFFDEE2E7),shape = RoundedCornerShape(20.dp))

                        .fillMaxHeight()

                        .width(150.dp)
                        .background(Color(0xFFEFF2F4))
                        .clickable {

                            val Intent= Intent(context, CategoryActivity::class.java)
                            Intent.putExtra("type",it.toString())
                            context.startActivity(Intent);
                        }



                ){
                    Column(
                        modifier =
                        Modifier

                            .fillMaxSize()
                            .padding(5.dp)
                            .background(Color(0xFFEFF2F4))


                        ,
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(text = it.toString(),

                            modifier =
                            Modifier
                                .fillMaxWidth()



                            ,
                            textAlign = TextAlign.Center,

                            color = Color(0xFF0D6EFD)

                        )

                    }

                }
            }


        }
    }

}