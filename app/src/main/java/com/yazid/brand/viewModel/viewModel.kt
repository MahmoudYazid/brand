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
   val ProductDataViewModel = MutableLiveData<List<ResponseItem>>()
   val ProductDataViewModelLiveData: LiveData<List<ResponseItem>> = ProductDataViewModel

   val CatigoryDataViewModel = MutableLiveData<List<String>>()
   val CatigoryDataViewModelLiveData: LiveData<List<String>> = CatigoryDataViewModel

   val limitProductShowViewModel = MutableLiveData<Int>(10)
   val LimitProductDataViewModel = MutableLiveData<List<ResponseItem>?>()
   val LimitProductDataViewModelLiveData: MutableLiveData<List<ResponseItem>?> = LimitProductDataViewModel


   fun incLimit(){
      limitProductShowViewModel.value= limitProductShowViewModel.value?.plus(5);


   }
   fun LimitGetData() {

      viewModelScope.launch {
         val newProductData = limitProductShowViewModel.value?.let {
            ApiserviceInst.GetAllProductDataWithLimit(
               it
            )
         }
         withContext(Dispatchers.Main){
            LimitProductDataViewModel.value=newProductData
         }
      }




   }
   fun GetAllData() {

      viewModelScope.launch {
         val newProductData = ApiserviceInst.GetAllProductData()
         withContext(Dispatchers.Main){
            ProductDataViewModel.value=newProductData
         }
      }




   }

   fun GetCatigoriesData() {

      viewModelScope.launch {
         val newProductData = ApiserviceInst.GetAllCatigories()
         withContext(Dispatchers.Main){
            CatigoryDataViewModel.value=newProductData
         }
      }




   }


}