
import android.Manifest
import android.content.pm.PackageManager
import android.util.Size
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat
import androidx.work.await
import com.example.myapplicationjetpackcompose.HyperlinkTextGetLink
import com.example.myapplicationjetpackcompose.QrCodeAnalyer
import com.example.myapplicationjetpackcompose.mainmenu.qrcodegetlink.MainMenuTabsQRcodeScanItemViewModel

@Composable
fun MainMenuTabsQRcodeScanItemScreen (
    mainMenuTabsQRcodeScanItemViewModel: MainMenuTabsQRcodeScanItemViewModel,

)
{

    var code by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var hasCamPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestPermission(),
//        onResult = { granted ->
//            hasCamPermission = granted
//        }
//    )

    //region code camra 1
//    val cameraProviderFuture = remember {
//        ProcessCameraProvider.getInstance(context)
//    }
//
//    LaunchedEffect(key1 = true ) {
//        launcher.launch(Manifest.permission.CAMERA)
//
//    }
//
//    Dialog(onDismissRequest = {
//
//
//    },
//        properties = DialogProperties(
//            usePlatformDefaultWidth = false
//        ),
//
//        )
//    {
//        Column(
//            modifier = Modifier.fillMaxSize()
//                .background(color = androidx.compose.ui.graphics.Color.Green),
//
//
//            ) {
//
//
//            Button (
//                onClick = {
//
//                    mainMenuTabsQRcodeScanItemViewModel.isShow = false
//                }
//            ) {
//                Text ("Đóng")
//            }
//
//             if (hasCamPermission) {
//
//            AndroidView(
//                modifier = Modifier.padding(50.dp)
//                    .background(color = androidx.compose.ui.graphics.Color.Red),
//                factory = { context ->
//
//                    val previewView = PreviewView(context)
//
//                    val selector = CameraSelector
//                        .Builder()
//                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
//                        .build()
//
//                    val preview = Preview.Builder().build()
//
//                    preview.setSurfaceProvider(previewView.surfaceProvider)
//
//                    val imageAnalysis = ImageAnalysis.Builder()
//                        .setTargetResolution(
//                            Size(
//                                previewView.width,
//                                previewView.height
//                            )
//                        )
//                        .setBackpressureStrategy(
//                            STRATEGY_KEEP_ONLY_LATEST
//                        )
//                        .build()
//
//                    imageAnalysis.setAnalyzer(
//                        ContextCompat.getMainExecutor(context),
//                        QrCodeAnalyer { result ->
//                            code = result
//                        }
//                    )
//
//
//
//                    try {
//                        cameraProviderFuture.get().bindToLifecycle(
//                            lifecycleOwner,
//                            selector,
//                            preview,
//                            imageAnalysis
//                        )
//                    } catch (e: Exception) {
//                        e.printStackTrace()
//                    }
//
//                    previewView
//
//                }
//
//
//            )
//
//            Text(
//                text = code,
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.fillMaxSize()
//                    .padding(10.dp)
//
//            )
//             }
//
//        }
//    }

    //endregion code camera 1

    //region code camra 2
    val cameraProvider by produceState<ProcessCameraProvider?>(initialValue = null) {
        value = ProcessCameraProvider.getInstance(context).await()
    }

    val cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

    val preview: Preview = remember { Preview.Builder().build() }
    val previewView = PreviewView(context)
    val implementationMode: PreviewView.ImplementationMode = PreviewView.ImplementationMode.COMPATIBLE
    val enableTorch: Boolean = false

    val imageCapture = ImageCapture.Builder().build()
    val scaleType: PreviewView.ScaleType = PreviewView.ScaleType.FILL_CENTER

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

    // TODO: add cameraSelector
    val camera = remember(cameraProvider) {
        cameraProvider?.let {
            it.unbindAll()
            it.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                *listOfNotNull(imageAnalysis, imageCapture, preview).toTypedArray()
            )
        }
    }


    LaunchedEffect(camera, enableTorch) {
        camera?.let {
            if (it.cameraInfo.hasFlashUnit()) {
                it.cameraControl.enableTorch(enableTorch).await()
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            cameraProvider?.unbindAll()
        }
    }

    Dialog(onDismissRequest = {


    },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),

        )
    {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(color = androidx.compose.ui.graphics.Color.Green),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {


            Button(
                onClick = {

                    mainMenuTabsQRcodeScanItemViewModel.isShow = false
                }
            ) {
                Text("Đóng")
            }

            if (hasCamPermission) {

                AndroidView(
                    modifier = Modifier.padding(50.dp)
                        .background(color = androidx.compose.ui.graphics.Color.Red),

//                    modifier = Modifier.pointerInput(camera, focusOnTap) {
//                        if (!focusOnTap) return@pointerInput
//
//                        detectTapGestures {
//                            val meteringPointFactory = SurfaceOrientedMeteringPointFactory(
//                                size.width.toFloat(),
//                                size.height.toFloat()
//                            )
//
//                            val meteringAction = FocusMeteringAction.Builder(
//                                meteringPointFactory.createPoint(it.x, it.y),
//                                FocusMeteringAction.FLAG_AF
//                            ).disableAutoCancel().build()
//
//                            camera?.cameraControl?.startFocusAndMetering(meteringAction)
//                        }
//                    },
                    factory = { _ ->
                        PreviewView(context).also {
                            it.scaleType = scaleType
                            it.implementationMode = implementationMode
                            preview.setSurfaceProvider(it.surfaceProvider)
                        }
                    }
                )

                HyperlinkTextGetLink(
                    linkText = code,
                    modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                bottom = 30.dp
                            )
                )
            }

        }
    }

    //endregion code camera 2

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
