
import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Color
import android.util.Size
import android.widget.ImageView
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ExperimentalUseCaseGroupLifecycle
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.myapplicationjetpackcompose.QrCodeAnalyer
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter

@Composable
fun MainMenuTabsQRcodeItem (

    string_qrcode: String = ""

)
{

    var code by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    val lifecycleOwner = LocalLifecycleOwner.current

    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }

    var hasCamPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED


        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            hasCamPermission = granted
        }
    )

    LaunchedEffect(key1 = true ) {
        launcher.launch(Manifest.permission.CAMERA)

    }

    Column  (
        modifier = Modifier.fillMaxSize()
            .background(color = androidx.compose.ui.graphics.Color.Green),


    ) {


       // if (hasCamPermission) {

            AndroidView(
                modifier = Modifier.padding(50.dp)
                    .background(color = androidx.compose.ui.graphics.Color.Red),
                factory = { context ->

                    val previewView = PreviewView(context)

                    val selector = CameraSelector
                        .Builder()
                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                        .build()

                    val preview = Preview.Builder().build()

                    preview.setSurfaceProvider(previewView.surfaceProvider)

                    val imageAnalysis = ImageAnalysis.Builder()
                        .setTargetResolution(
                            Size(
                                previewView.width,
                                previewView.height
                            )
                        )
                        .setBackpressureStrategy(
                            STRATEGY_KEEP_ONLY_LATEST
                        )
                        .build()

                    imageAnalysis.setAnalyzer(
                        ContextCompat.getMainExecutor(context),
                        QrCodeAnalyer { result ->
                            code = result
                        }
                    )

                    try {
                        cameraProviderFuture.get().bindToLifecycle(
                            lifecycleOwner,
                            selector,
                            preview,
                            imageAnalysis
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    previewView

                }


            )

            Text(
                text = code,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxSize()
                    .padding(10.dp)

            )
       // }

//        AndroidView(
//            factory = { ctx ->
//                ImageView(ctx).apply {
//                    val size = 512
//                    val hints = hashMapOf<EncodeHintType, Int>().also {
//                        it[EncodeHintType.MARGIN] = 1
//                    } //Make the QR code buffer border narrower
//                    val bits = QRCodeWriter().encode(
//                        string_qrcode,
//                        BarcodeFormat.QR_CODE,
//                          size,
//                         size,
//                          hints,
//                    )
//
//                    val bitmap =
//                        Bitmap.createBitmap(size,size,Bitmap.Config.RGB_565).also {
//                            for (x in 0 until size ) {
//                                for (y in 0 until size) {
//
//                                    it.setPixel(
//                                        x,
//                                        y,
//                                        if (bits[x,y]) Color.BLACK else Color.WHITE
//                                    )
//
//                                }
//
//                            }
//                        }
//
//                    setImageBitmap(bitmap)
//
//                }
//            }
//
//        )
//
//
//        Text(text=string_qrcode)
    }


}

//@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
//@Composable
//fun MainMenuTabsQRcodeItemPreview() {
//    MyApplicationJetpackComposeTheme {
//
//        val context = LocalContext.current
//        MainMenuTabsQRcodeItem(
//           string_qrcode = "https://google.com/dasdasdasd121323"
//        )
//
//    }
//}
