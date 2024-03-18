package com.yazid.brand.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yazid.brand.Repository.onlineResourse.apiServiceImplement
import com.yazid.brand.model.ResponseItem

import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class viewModel @Inject constructor(
   val ApiserviceInst: apiServiceImplement

):ViewModel() {

   suspend fun GetAllData(): List<ResponseItem> {


      return ApiserviceInst.GetAllProductData()



   }




}