package com.yazid.brand.view.navbar

import android.content.Intent
import android.graphics.drawable.PaintDrawable
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yazid.brand.R
import com.yazid.brand.view.ShoppingCart

@Composable
fun navbar(){
    val context = LocalContext.current
    Box (
        modifier =
        Modifier
            .fillMaxWidth()
            .height(100.dp)
    ){
        Row (
            modifier =
            Modifier
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

            
            
        ){
            Image(
                painter = painterResource(R.drawable.logocolored),
                contentDescription ="Logo",
                modifier =
                    Modifier
                        .size(150.dp)
            )
            Row (

                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically

            ){
                Image(
                    painter = painterResource(R.drawable.baseline_person_24),
                    contentDescription = "Logo",
                    modifier =
                    Modifier
                        .size(40.dp)
                        .clickable {
                            context.startActivity(Intent(
                                Intent.ACTION_VIEW
                                ,Uri.parse("https://www.linkedin.com/in/mahmoudyazid/")

                            ))
                        }
                )
                Spacer(modifier = Modifier.width(20.dp))
                Image(
                    painter = painterResource(R.drawable.baseline_shopping_cart_24),
                    contentDescription = "Logo",
                    modifier =
                    Modifier
                        .size(30.dp)
                        .clickable {
                            context.startActivity(Intent(
                                context
                                ,ShoppingCart::class.java

                            ))
                        }
                )
            }
        }

    }

}