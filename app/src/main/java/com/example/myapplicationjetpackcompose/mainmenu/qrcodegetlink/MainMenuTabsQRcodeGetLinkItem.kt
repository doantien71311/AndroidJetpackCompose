package com.example.myapplicationjetpackcompose.mainmenu.qrcodegetlink

import MainMenuTabsQRcodeScanItemScreen
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Color
import android.widget.ImageView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplicationjetpackcompose.HyperlinkTextGetLink
import com.example.myapplicationjetpackcompose.hanhchinhnhansu.nhanvien.NhanVienViewModel
import com.example.myapplicationjetpackcompose.lookup.chucvu.LookupChucVuViewModel
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter

@Composable
fun MainMenuTabsQRcodeGetLinkItem(

    string_qrcode: String = "https://fwm.theworldlink.vn/thong-tin-nhan-vien/FM600001"


) {

    val mainMenuTabsQRcodeScanItemViewModel = hiltViewModel<MainMenuTabsQRcodeScanItemViewModel>()
    if (mainMenuTabsQRcodeScanItemViewModel.isShow) {
        MainMenuTabsQRcodeScanItemScreen(mainMenuTabsQRcodeScanItemViewModel = mainMenuTabsQRcodeScanItemViewModel)
    }

    Column(
        modifier = Modifier.padding(
            10.dp
        ),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Button(

            onClick = {
                mainMenuTabsQRcodeScanItemViewModel.isShow = true
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
//                .padding(
//                    start = 10.dp,
//                    end = 10.dp
//                )
                .fillMaxWidth()
                .height(40.dp)
        ) {
            Text(text = "Quyét QR code")
        }


        Text(
            text = "ADMIN",
           // modifier = Modifier.align(Alignment.CenterHorizontally),
        )

        AndroidView(
            factory = { ctx ->
                ImageView(ctx).apply {
                    val size = 512
                    val hints = hashMapOf<EncodeHintType, Int>().also {
                        it[EncodeHintType.MARGIN] = 1
                    } //Make the QR code buffer border narrower
                    val bits = QRCodeWriter().encode(
                        string_qrcode,
                        BarcodeFormat.QR_CODE,
                        size,
                        size,
                        hints,
                    )

                    val bitmap =
                        Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
                            for (x in 0 until size) {
                                for (y in 0 until size) {

                                    it.setPixel(
                                        x,
                                        y,
                                        if (bits[x, y]) Color.BLACK else Color.WHITE
                                    )

                                }

                            }
                        }

                    setImageBitmap(bitmap)

                }
            }

        )

        HyperlinkTextGetLink(
            linkText = string_qrcode,
            modifier = Modifier
                .fillMaxWidth()
        )

    }



}



@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainMenuTabsQRcodeItemPreview() {
    MyApplicationJetpackComposeTheme {

        val context = LocalContext.current
        MainMenuTabsQRcodeGetLinkItem(
           string_qrcode = "https://fwm.theworldlink.vn/thong-tin-nhan-vien/FM600001"
        )

    }
}