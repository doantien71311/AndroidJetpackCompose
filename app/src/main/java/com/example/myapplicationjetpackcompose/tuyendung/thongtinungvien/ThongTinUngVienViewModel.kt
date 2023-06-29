package com.example.myapplicationjetpackcompose.tuyendung.thongtinungvien

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationjetpackcompose.model.dm_ungvien_cus
import com.example.myapplicationjetpackcompose.model.dto_menu_app
import com.example.myapplicationjetpackcompose.model.dto_menu_app_chitiet
import com.example.myapplicationjetpackcompose.model.ht_thongtinhdoanhnghiep
import com.example.myapplicationjetpackcompose.services.IDataStoreServies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ThongTinUngVienViewModel @Inject constructor (
    private val dataStoreServies: IDataStoreServies

): ViewModel() {

    var listUngvien : List<dm_ungvien_cus> by mutableStateOf(mutableListOf<dm_ungvien_cus>(dm_ungvien_cus()))
  //  var listPhongVan : List<dm_ungvien_cus> by mutableStateOf(mutableListOf<dm_ungvien_cus>(dm_ungvien_cus()))

    var listPhongVan : List<dm_ungvien_cus> by mutableStateOf(mutableListOf<dm_ungvien_cus>(dm_ungvien_cus()))

    var soluongPhongVan: Int by mutableStateOf(0)
    var soluongUngVien: Int by mutableStateOf(0)


    var indexUngVien : Int by mutableStateOf(-1)

//    private var _listUngvien = MutableLiveData<List<dm_ungvien_cus>>()
//    val listUngvien: LiveData<List<dm_ungvien_cus>> = _listUngvien
//

    init {


        loadData()


    }


    fun chonPhongVan(para_dm_ungvien_cus: dm_ungvien_cus) {

        //Cho phép hiệu ứng
        para_dm_ungvien_cus.isAnimatedVisibility.value = false

        //Loại bỏ dòng đã chọn
        this.listUngvien.drop(this.listUngvien.indexOf(para_dm_ungvien_cus))
        //Thêm vào danh sách phỏng vấn

        // this.listPhongVan.set(para_dm_ungvien_cus)
        //Tăng số lượng phỏng vấn
        soluongPhongVan += 1

        val newList = mutableListOf<dm_ungvien_cus>()
        newList.add(para_dm_ungvien_cus)
        listPhongVan = newList

    }



    fun loadData() {

        this.listUngvien =  mutableListOf<dm_ungvien_cus> (

            dm_ungvien_cus(ten_uv = "Ung72 vien 1"),
            dm_ungvien_cus(ten_uv = "Ung72 vien 2"),
            dm_ungvien_cus(ten_uv = "Ung72 vien 3"),
            dm_ungvien_cus(ten_uv = "Ung72 vien 4"),
            dm_ungvien_cus(ten_uv = "Ung72 vien 5"),
            dm_ungvien_cus(ten_uv = "Ung72 vien 6"),
        )


        //Thiết lập các giá trị măc định
        soluongPhongVan = 0;
        soluongUngVien = listUngvien.size


    }
}