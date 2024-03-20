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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.yazid.brand.R
import com.yazid.brand.ui.theme.BrandTheme
import com.yazid.brand.view.ProductsGrid.ProductsGrid

import com.yazid.brand.view.Scroller.ScrollerComp
import com.yazid.brand.view.ScrollerTitle.ScrollerTitle
import com.yazid.brand.view.banner.bannerCompose
import com.yazid.brand.view.categoryScroller.categoryScroller
import com.yazid.brand.view.navbar.navbar
import com.yazid.brand.view.searchbar.SearchBarComp
import com.yazid.brand.viewModel.viewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelInst:viewModel by viewModels()


        viewModelInst.GetAllData()
        viewModelInst.GetCatigoriesData()


        setContent {
            BrandTheme {
                // A surface container using the 'background' color from the theme
                LazyColumn(
                    modifier =
                    Modifier

                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                    navbar()
                    }


                    item {
                        SearchBarComp()
                    }
                    item {
                        categoryScroller(viewModelInst)
                    }
                    item {
                        bannerCompose(
                            Resourses = R.drawable.adv_one,
                            FirstColor = Color.Black,
                            SecondColor = Color.Black,
                            BtmTextColor = Color(0xFF0D6EFD),
                            BtmBgColor = Color.White,
                            FirstText = "Latest trending",
                            SecondText = "Electronic items"
                        )
                    }
                    item {
                        ScrollerTitle("Deals and offers")
                    }
                    item {
                        ScrollerComp(viewModelInst)
                    }
                    item {
                        bannerCompose(
                            Resourses = R.drawable.adv_two,
                            FirstColor = Color.White,
                            SecondColor = Color.White,
                            BtmTextColor = Color.White,
                            BtmBgColor = Color(0xFF0D6EFD),
                            FirstText = "An easy way to send ",
                            SecondText = "requests to all suppliers"
                        )
                    }
                    item {
                        ScrollerTitle("Recommended items")
                    }
                    item{

                        ProductsGrid(viewModelInst = viewModelInst)
                    }
                    item{



                    }
                    
                    
                    item { 
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                }
            }
        }
    }
}

