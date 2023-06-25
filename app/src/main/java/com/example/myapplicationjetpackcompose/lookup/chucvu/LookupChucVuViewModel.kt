package com.example.myapplicationjetpackcompose.lookup.chucvu

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationjetpackcompose.model.dm_chucvu_ds
import com.example.myapplicationjetpackcompose.model.dto_menu_app
import com.example.myapplicationjetpackcompose.model.ht_dm_nsd
import com.example.myapplicationjetpackcompose.model.response_dm_chucvu_ds
import com.example.myapplicationjetpackcompose.model.response_dto_menu_app
import com.example.myapplicationjetpackcompose.services.IDataStoreServies
import com.example.myapplicationjetpackcompose.services.RetrofitService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

interface ILookupChucVuViewModel {
    fun loadData ()
    fun layDuLieu ()
}


@HiltViewModel
class LookupChucVuViewModel @Inject constructor(
    private val dataStoreServies: IDataStoreServies,
): ViewModel() {

    var isShowLookup: Boolean by mutableStateOf(false)
    var rowData:  dm_chucvu_ds by mutableStateOf(dm_chucvu_ds())
    var listdata : List<dm_chucvu_ds> by mutableStateOf(mutableListOf<dm_chucvu_ds>(dm_chucvu_ds()))

   init {



   }

    fun loadData() {

        viewModelScope.launch {
            RetrofitService.IRetrofitService
                .getDanhMucChucVu(dataStoreServies.getBearToken(), ""  )
                .enqueue(object : Callback<response_dm_chucvu_ds?> {
                    override fun onResponse(
                        call: Call<response_dm_chucvu_ds?>,
                        response: Response<response_dm_chucvu_ds?>
                    ) {
                        response.body()?.data?.let {
                            listdata = it
                        }


                    }

                    override fun onFailure(
                        call: Call<response_dm_chucvu_ds?>,
                        t: Throwable
                    ) {

                        var s = t.localizedMessage
                        Log.e("Faild", t.message.toString())
                    }

                })


        }

    }


//    fun layDuLieu()
//    {
//        rowData = listdata[0]
//        isShowLookup = false;
//
//    }

}