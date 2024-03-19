package com.yazid.brand.view.banner

import android.graphics.drawable.PaintDrawable
import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yazid.brand.R

@Composable
fun bannerCompose(
    Resourses:Int,
    FirstColor:Color,
    SecondColor:Color,
    BtmTextColor:Color,
    BtmBgColor:Color,
    FirstText:String,
    SecondText:String
    ){
    Box(
        modifier =
        Modifier
            .fillMaxWidth()
            .height(200.dp)){
        Image(
            painter = painterResource(id = Resourses),
            contentDescription = null,
            modifier =
                Modifier
                    .fillMaxSize()
            )

        Column (
            modifier =
            Modifier
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,

        ){
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = FirstText,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = FirstColor

            )
            Text(
                text =SecondText,
                fontSize = 25.sp,
                color = SecondColor

            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedButton(
                onClick = { /*TODO*/ },
                modifier =
                Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .border(
                        2.dp,
                        BtmBgColor,
                        shape = RoundedCornerShape(20.dp)
                    ) // Set border color to black

                    .width(150.dp)
                    .height(40.dp)
                    .background(BtmBgColor)

            ) {
                Text(
                    text = "Learn more",
                    color = BtmTextColor ,
                    textAlign = TextAlign.Center
                )
            }

        }
    }

}