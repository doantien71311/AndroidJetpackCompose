package com.example.myapplicationjetpackcompose.tuyendung.kichhoathanhvien

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationjetpackcompose.lookup.chucvu.LookupChucVuViewModel
import com.example.myapplicationjetpackcompose.model.dm_ungvien_cus
import com.example.myapplicationjetpackcompose.services.IDataStoreServies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KichHoatThanhVienViewModel @Inject constructor(
    private val dataStoreServies: IDataStoreServies,
     //private val lookupChucVuViewModel: LookupChucVuViewModel

    ): ViewModel() {



   var state by mutableStateOf(dm_ungvien_cus())

    sealed class ValidationEvent {
        object Success : ValidationEvent()

    }
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    private val validateEmail : ValidateEmail = ValidateEmail()
    private var emailResult = validateEmail.execute(state.email?:"")



    fun handleEvent(kichHoatThanhVienEvent: KichHoatThanhVienEvent) {

        when (kichHoatThanhVienEvent) {

            is KichHoatThanhVienEvent.EmailChanged -> {

                //Thiết lập giá trị từ ui
                state = state.copy(
                    email = kichHoatThanhVienEvent.emailAddress,
                )

                //Hiển thị lỗi email nếu có
                emailResult = validateEmail.execute(state.email?:"")
                state = state.copy(
                    is_email_error = emailResult.isError,
                    message_email_error = emailResult.errorMessage
                )


            }
        }
    }



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




    fun layDuLieuLookupChucVu() {

        viewModelScope.launch {

            _dm_ungvien_cus.value?.vitri_ungtuyen = lookupChucVu.rowData.ma_chucvu.toString()

        }

    }



    fun kichHoatTaiKhoan()
    {
        //Kiểm tra giá trị hợp lệ trước khi lưu
        emailResult = validateEmail.execute(state.email?:"")


        val hasError = listOf(
            emailResult,
        ).any {

            it.isError

        }

        if (hasError) {

            state = state.copy(
                message_email_error = emailResult.errorMessage,
                is_email_error = true,
            )

            return
        }




        saveData()


    }


    private fun saveData() {

        viewModelScope.launch {

            validationEventChannel.send(ValidationEvent.Success)

        }

    }


}