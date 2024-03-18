package com.yazid.brand.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yazid.brand.ui.theme.BrandTheme
import com.yazid.brand.view.Scroller.ScrollerComp
import com.yazid.brand.view.navbar.navbar
import com.yazid.brand.view.searchbar.SearchBarComp
import com.yazid.brand.viewModel.viewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelInst:viewModel by viewModels()

        setContent {
            BrandTheme {
                // A surface container using the 'background' color from the theme
             Column(
                 modifier =
                 Modifier
                     .fillMaxSize(),
                 verticalArrangement = Arrangement.spacedBy(10.dp),
                 horizontalAlignment = Alignment.CenterHorizontally
             ) {
                 navbar()
                 SearchBarComp()
                 ScrollerComp(viewModelInst)

             }
            }
        }



    }
}

