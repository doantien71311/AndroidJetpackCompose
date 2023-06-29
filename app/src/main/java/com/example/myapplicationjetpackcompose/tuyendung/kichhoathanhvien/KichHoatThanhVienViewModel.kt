package com.example.myapplicationjetpackcompose.tuyendung.kichhoathanhvien

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplicationjetpackcompose.lookup.chucvu.LookupChucVuViewModel
import com.example.myapplicationjetpackcompose.model.dm_ungvien_cus
import com.example.myapplicationjetpackcompose.model.ht_dm_nsd
import com.example.myapplicationjetpackcompose.services.IDataStoreServies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class KichHoatThanhVienViewModel @Inject constructor(
    private val dataStoreServies: IDataStoreServies,
     //private val lookupChucVuViewModel: LookupChucVuViewModel
    ): ViewModel() {



    //var dm_ungvien_cus: dm_ungvien_cus by mutableStateOf(dm_ungvien_cus())

   // var dm_ungvien_cus : dm_ungvien_cus = dm_ungvien_cus()

   private val _dm_ungvien_cus = MutableLiveData<dm_ungvien_cus>()
    val dm_ungvien_cus: LiveData<dm_ungvien_cus> = _dm_ungvien_cus




    fun vitri_ungtuyenOnChanged(vitri_ungtuyen: String) {

        _dm_ungvien_cus.value?.let {
            _dm_ungvien_cus.value = it.copy(vitri_ungtuyen = vitri_ungtuyen)
        }

    }

    fun ma_uvOnChanged(ma_uv: String) {

        _dm_ungvien_cus.value?.let {
            _dm_ungvien_cus.value = it.copy(ma_uv = ma_uv)
        }

    }



    var lookupChucVu: LookupChucVuViewModel = LookupChucVuViewModel(dataStoreServies)

    init {

            loadData()


    }

    private fun loadData()
    {

        this._dm_ungvien_cus.value = dm_ungvien_cus()
        this._dm_ungvien_cus.value?.vitri_ungtuyen = "AG"

    }

    fun kichHoatTaiKhoan() {

        val dasdas = this._dm_ungvien_cus.value

    }


    fun layDuLieuLookupChucVu() {

        viewModelScope.launch {

            _dm_ungvien_cus.value?.vitri_ungtuyen = lookupChucVu.rowData.ma_chucvu.toString()

//            if (lookupChucVu.isShowLookup) {
//
//                //Gán giá trị
//                _dm_ungvien_cus.value?.vitri_ungtuyen = lookupChucVu.rowData.ma_chucvu.toString()
//
//            }

        }

    }


}