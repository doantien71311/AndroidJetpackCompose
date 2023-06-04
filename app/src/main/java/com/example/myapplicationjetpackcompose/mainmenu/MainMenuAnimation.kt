package com.example.myapplicationjetpackcompose.mainmenu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myapplicationjetpackcompose.R
import kotlinx.coroutines.delay
import okhttp3.internal.wait


@Composable
fun MainMenuLoadingAnimation(
    isLoadding: Boolean ,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    circleSize: Dp = 25.dp,
    circleColor: Color = MaterialTheme.colorScheme.primary,
    spaceBetween: Dp = 10.dp,
    travelDistance: Dp = 20.dp

) {

    if (isLoadding) {

        val circles = listOf(
            remember { Animatable(initialValue = 0f) },
            remember { Animatable(initialValue = 0f) },
            remember { Animatable(initialValue = 0f) }
        )

        circles.forEachIndexed { index, animatable ->
            LaunchedEffect(key1 = animatable) {
                delay(index * 100L)
                animatable.animateTo(
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = keyframes {
//                        durationMillis = 1200
//                        0.0f at 0 with LinearOutSlowInEasing
//                        1.0f at 300 with LinearOutSlowInEasing
//                        0.0f at 600 with LinearOutSlowInEasing
//                        0.0f at 1200 with LinearOutSlowInEasing

                            durationMillis = 600
                            0.0f at 0 with LinearOutSlowInEasing
                            1.0f at 200 with LinearOutSlowInEasing
                            0.0f at 400 with LinearOutSlowInEasing
                            0.0f at 600 with LinearOutSlowInEasing

                        },
                        repeatMode = RepeatMode.Restart
                    )
                )
            }
        }

        val circleValues = circles.map { it.value }
        val distance = with(LocalDensity.current) { travelDistance.toPx() }
        val lastCircle = circleValues.size - 1

        Column (

            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(R.drawable.main_menu_loading_v2)
                        .crossfade(true)
                        .build(),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(300.dp)
                        .width(300.dp)
                )

                //Divider()


                Row(
                    modifier = modifier,
                    horizontalArrangement = Arrangement.spacedBy(spaceBetween)
                ) {
                    circleValues.forEachIndexed { index, value ->
                        Box(
                            modifier = Modifier
                                .size(circleSize)
                                .graphicsLayer {
                                    translationY = -value * distance
                                }
                                .background(
                                    color = circleColor,
                                    shape = CircleShape
                                )
                        )

                        if (index != lastCircle) {

                            Spacer(modifier = Modifier.width(spaceBetween))

                        }


                    }
                }
            }

    }
    else {


            contentAfterLoading()

    }
}


