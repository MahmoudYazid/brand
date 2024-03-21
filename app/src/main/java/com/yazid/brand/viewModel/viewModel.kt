package com.yazid.brand.viewModel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yazid.brand.Repository.offlineResourse.RoomImplementationClass
import com.yazid.brand.Repository.onlineResourse.apiServiceImplement
import com.yazid.brand.model.ResponseItem
import com.yazid.brand.view.CategoryActivity

import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class viewModel @Inject constructor(
   val ApiserviceInst: apiServiceImplement,
   @ApplicationContext val context: Context,
   val RoomImpl:RoomImplementationClass

):ViewModel() {
   val ProductDataViewModel = MutableLiveData<List<ResponseItem>>()
   val ProductDataViewModelLiveData: LiveData<List<ResponseItem>> = ProductDataViewModel

   val CatigoryDataViewModel = MutableLiveData<List<String>>()
   val CatigoryDataViewModelLiveData: LiveData<List<String>> = CatigoryDataViewModel


   // For Api Control Functions
   suspend fun LimitGetData(LimitNo:Int): List<ResponseItem> {



            return ApiserviceInst.GetAllProductDataWithLimit(
               LimitNo
            )







   }
   fun GetAllData() {

      viewModelScope.launch {
         val newProductData = ApiserviceInst.GetAllProductData()
         withContext(Dispatchers.Main){
            ProductDataViewModel.value=newProductData
         }
      }




   }
   suspend fun SearchGetAllData(): List<ResponseItem> {

         return ApiserviceInst.GetAllProductData()





   }
   fun GetCatigoriesData() {

      viewModelScope.launch {
         val newProductData = ApiserviceInst.GetAllCatigories()
         withContext(Dispatchers.Main){
            CatigoryDataViewModel.value=newProductData
         }
      }




   }
   suspend fun getSpecificCatigoriesData(Cat_Name: String): List<ResponseItem> {
      try {
         return ApiserviceInst.GetSpecificCatigory(Cat_Name)
      } catch (e: Exception) {
         // Handle the exception here, for example, log it
         Log.e("GetSpecificCatigoriesData", "Error: ${e.message}", e)
         // Throw the exception or return empty list as per your requirement
         throw e
      }
   }

   suspend fun getSpecificId(Id: String): ResponseItem {
      try {
         return ApiserviceInst.GetSpecificId(Id)
      } catch (e: Exception) {
         // Handle the exception here, for example, log it
         Log.e("GetSpecificCatigoriesData", "Error: ${e.message}", e)
         // Throw the exception or return empty list as per your requirement
         throw e
      }
   }


   // For Room Control Functions

}