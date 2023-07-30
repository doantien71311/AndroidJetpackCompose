package com.example.myapplicationjetpackcompose.mainmenu

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplicationjetpackcompose.ViewModelFactoryProvider
import com.example.myapplicationjetpackcompose.hanhchinhnhansu.nhanvien.NhanVienEditViewModel
import com.example.myapplicationjetpackcompose.tuyendung.thongtinungvien.ThongTinUngVienViewModel
import dagger.hilt.android.EntryPointAccessors

@Composable
fun getNhanVienEditViewModel(
    pKeyvalue: String = "",
 )
        : NhanVienEditViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        ViewModelFactoryProvider::class.java
    )
        .nhanVienEditViewModelFactory()

    return viewModel(
        factory = NhanVienEditViewModel.providerMainViewModelFactory
            (
            factory,
            pKeyvalue,

        )
    )
}