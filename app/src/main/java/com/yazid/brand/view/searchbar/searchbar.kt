package com.yazid.brand.view.searchbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yazid.brand.R
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun SearchBarComp(){
    Box (
        modifier =
        Modifier
            .fillMaxWidth()
            .height(50.dp)
    ){
        Row (
            modifier =
            Modifier
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically



        ){
            var TempText  by remember {
                mutableStateOf("")
            }
            TextField(
                value =TempText ,

                onValueChange ={
                    TempText=it
                } ,

                leadingIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = null
                        )


                    }

                },
                modifier =
                    Modifier
                        .fillMaxSize()
                        .border(
                            2.dp,
                            Color(0xFFDEE2E7),
                            shape = RoundedCornerShape(10.dp))
                        ,
                colors = TextFieldDefaults.textFieldColors(

                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.White),





                )

            
            
            
            
            
            
            
            
        }

    }
    
}