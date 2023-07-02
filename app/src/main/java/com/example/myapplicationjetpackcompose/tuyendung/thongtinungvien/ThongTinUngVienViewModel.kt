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
    var soluongUngVien: Int by mutableStateOf(0)
    var indexUngVien : Int by mutableStateOf(-1)

    var listPhongVan : List<dm_ungvien_cus> by mutableStateOf(mutableListOf<dm_ungvien_cus>())

    //var listPhongVan : List<dm_ungvien_cus> by mutableStateOf(mutableListOf<dm_ungvien_cus>(dm_ungvien_cus()))
    var soluongPhongVan: Int by mutableStateOf(0)
    var isShowHenPhongVan: Boolean by mutableStateOf(false)

    private val _diadiem_henphongvan = MutableLiveData<String>()
    val diadiem_henphongvan : LiveData<String> = _diadiem_henphongvan
    fun onChangedDiaDiemHenPhongVan(diadiem_henphongvan: String)
    {
        _diadiem_henphongvan.value = diadiem_henphongvan

    }

    private val _link_phongvan_online = MutableLiveData<String>()
    val link_phongvan_online : LiveData<String> = _link_phongvan_online
    fun onChangedLinkPhongVanOnline(link_phongvan_online: String)
    {
        _link_phongvan_online.value = link_phongvan_online

    }

    private val _is_phongvan_online = MutableLiveData<Boolean>()
    val is_phongvan_online : LiveData<Boolean> = _is_phongvan_online
    fun onChangedIsPhongVanOnline(is_phongvan_online: Boolean) {
        _is_phongvan_online.value = is_phongvan_online

    }



    init {

        loadData()

    }


    fun chonPhongVan(para_dm_ungvien_cus: dm_ungvien_cus) {


        //Cho phép hiệu ứng
        para_dm_ungvien_cus.isAnimatedVisibility.value = false

        //Loại bỏ dòng đã chọn
        this.listUngvien.drop(this.listUngvien.indexOf(para_dm_ungvien_cus))


        //Thêm vào danh sách phỏng vấn
        listPhongVan += para_dm_ungvien_cus

        //Thiết lập lại số lượng phỏng vấn
        soluongPhongVan = listPhongVan.size



    }



    fun loadData() {

        this.listUngvien =  mutableListOf<dm_ungvien_cus> (

            dm_ungvien_cus(ten_uv = "Ung72 vien 1", email = "mrtienemail1@gmail.com"),
            dm_ungvien_cus(ten_uv = "Ung72 vien 2", email = "email2@gmail.com"),
            dm_ungvien_cus(ten_uv = "Ung72 vien 3", email = "email3@gmail.com"),
            dm_ungvien_cus(ten_uv = "Ung72 vien 4", email = "email4@gmail.com"),
            dm_ungvien_cus(ten_uv = "Ung72 vien 5", email = "email5@gmail.com"),
            dm_ungvien_cus(ten_uv = "Ung72 vien 6", email = "email6@gmail.com"),
        )

        this.listPhongVan = mutableListOf<dm_ungvien_cus>()

        //Thiết lập các giá trị măc định
        soluongPhongVan = listPhongVan.size
        soluongUngVien = listUngvien.size



    }
}