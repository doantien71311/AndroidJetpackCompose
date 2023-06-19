package com.example.myapplicationjetpackcompose.caidat

import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationjetpackcompose.MyApp
import com.example.myapplicationjetpackcompose.mainmenu.MainMenuActivity
import com.example.myapplicationjetpackcompose.model.Enum_Status
import com.example.myapplicationjetpackcompose.model.dto_paramater
import com.example.myapplicationjetpackcompose.model.ht_dm_nsd
import com.example.myapplicationjetpackcompose.model.response_EncryptDES
import com.example.myapplicationjetpackcompose.model.response_boolean
import com.example.myapplicationjetpackcompose.model.response_data
import com.example.myapplicationjetpackcompose.model.tb_nhanvien_thietbi
import com.example.myapplicationjetpackcompose.services.IDataStoreServies
import com.example.myapplicationjetpackcompose.services.RetrofitService
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CaiDatThongBaoViewModel @Inject constructor (
    private val dataStoreServies: IDataStoreServies,
    @ApplicationContext context: Context
): ViewModel() {

    var tb_nhanvien_thietbi: tb_nhanvien_thietbi by mutableStateOf(tb_nhanvien_thietbi())

    private val _thongbao_tuyendung = MutableLiveData<Boolean>()
    val thongbao_tuyendung : LiveData<Boolean> = _thongbao_tuyendung
    fun thongbao_tuyendungOnChange(thongbao_tuyendung: Boolean) {
        _thongbao_tuyendung.value = thongbao_tuyendung
        tb_nhanvien_thietbi.ma_nv = "ADMIN"
        tb_nhanvien_thietbi.installation_id = "installation_id"
        tb_nhanvien_thietbi.token_pcm = "token_pcm"
    }


    private val _thongbao_doinhom = MutableLiveData<Boolean>()
    val thongbao_doinhom : LiveData<Boolean> = _thongbao_doinhom

    private val _thongbao_kiemtra = MutableLiveData<Boolean>()
    val thongbao_kiemtra : LiveData<Boolean> = _thongbao_kiemtra
    fun thongbao_kiemtraOnChange(thongbao_kiemtra: Boolean) {

        LuuThongBaoTuyenDung(thongbao_kiemtra)

    }



    init {

        CHECK_POST_NOTIFICATIONS (context)

        // Get token
        // [START log_reg_token]
        Firebase.messaging.getToken().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Get new FCM registraddtion token
                tb_nhanvien_thietbi.token_pcm = task.result
                Log.w("TAG", "ToKenPCM: " + tb_nhanvien_thietbi.token_pcm)
            } else {
                Log.w("TAG", "Fetching FCM registration token failed", task.exception)

            }

        }

        FirebaseInstallations.getInstance().id.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                tb_nhanvien_thietbi.installation_id = task.result
                Log.d("Installations", "Installation ID: " + tb_nhanvien_thietbi.installation_id)
            } else {
                Log.e("Installations", "Unable to get Installation ID")
            }
        }
    }


    private fun CHECK_POST_NOTIFICATIONS (context: Context) {

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        var permissionCheck: Boolean = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permission = ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.POST_NOTIFICATIONS
            )

            if (permission == PackageManager.PERMISSION_GRANTED)
                _thongbao_kiemtra.value = true

        } else {
            _thongbao_kiemtra.value = true

        }

    }

    private fun ThongBao_KiemTra(context: Context) {

        val PERMISSIONS_STORAGE = arrayOf<String>(
            android.Manifest.permission.POST_NOTIFICATIONS
        )


        val requestPermission  = ActivityResultContracts.RequestPermission()


        //context.registerForActivityResult


    }



    fun LuuThongBaoTuyenDung(boolean: Boolean)
    {

        viewModelScope.launch {

            RetrofitService.IRetrofitService
                .tb_nhanvien_thietbi_insert(dataStoreServies.getBearToken(),
                    tb_nhanvien_thietbi,
                    dataStoreServies.getDTOParamater().toMap()
                )
                .enqueue(object : Callback<response_data?> {
                    override fun onResponse(
                        call: Call<response_data?>,
                        response: Response<response_data?>
                    ) {

                        val m_response = response.body() ?: response_data()
                        _thongbao_tuyendung.value = m_response.status == Enum_Status.OK

                    }

                    override fun onFailure(
                        call: Call<response_data?>,
                        t: Throwable
                    ) {

                        var s = t.localizedMessage
                        Log.e("Faild", t.message.toString())
                    }

                })


        }

    }



}
