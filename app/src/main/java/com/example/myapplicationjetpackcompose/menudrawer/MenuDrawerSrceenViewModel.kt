package com.example.myapplicationjetpackcompose.menudrawer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationjetpackcompose.services.IDataStoreServies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuDrawerSrceenViewModel @Inject constructor (
    private val dataStoreServies: IDataStoreServies,
): ViewModel() {


    fun clearDataStore() {

        viewModelScope.launch {

            dataStoreServies.clearDataStore()

        }

    }

}
