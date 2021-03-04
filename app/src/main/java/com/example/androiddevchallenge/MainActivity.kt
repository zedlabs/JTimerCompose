/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.ui.MainViewModel
import com.example.androiddevchallenge.ui.TimePicker
import com.example.androiddevchallenge.ui.theme.MyTheme
import kotlin.math.max
import kotlin.math.min


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    Surface(color = Color.DarkGray) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            TimePicker()
            TimerBox(elapsedTime = 2000, totalTime = 10000)
        }

    }
}

@Composable
fun TimerBox(
    modifier: Modifier = Modifier,
    elapsedTime: Long,
    totalTime: Long,

    ) {
    val vm: MainViewModel = viewModel()
    val time by vm.remTime1.observeAsState("")

    Column {
        Box(modifier = modifier) {
            TimerCircle(elapsedTime = elapsedTime, totalTime = totalTime)
            Text(
                text = time,
                modifier = Modifier.offset(x = 110.dp, y = 190.dp),
                color = Color.Yellow
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        StartAndPauseButton(vm)
    }

}

@Composable
fun StartAndPauseButton(vm: MainViewModel) {

    val time3 by vm.time3.observeAsState("")
    val time2 by vm.time2.observeAsState("")
    val time1 by vm.time1.observeAsState("")

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            vm.onRemChanged(getTimeInMs(time1, time2, time3))
        }) {
            Text(text = "Start")
        }
        Spacer(modifier = Modifier.width(10.dp))
        Button(onClick = {
            vm.setTime3("")
            vm.setTime2("")
            vm.setTime1("")
            vm.onRemChanged("Set Time to start Timer")
        }) {
            Text(text = "Reset")
        }
    }
}

fun getTimeInMs(time1: String, time2: String, time3: String): String {

    var time = 0
    if(time3 != "") time+=(time3.toInt()*10)
    if(time2 != "") time+=(time2.toInt()*60*10)
    if(time1 != "") time+=(time1.toInt()*60*60*10)

    return time.toString()
}

@Composable
fun TimerCircle(
    modifier: Modifier = Modifier,
    elapsedTime: Long,
    totalTime: Long
) {
    Canvas(modifier = modifier
        .fillMaxWidth()
        .height(400.dp), onDraw = {
        val height = size.height
        val width = size.width
        val dotDiameter = 12.dp
        val strokeSize = 20.dp
        val radiusOffset = calculateRadiusOffset(strokeSize.value, dotDiameter.value, 0f)

        val xCenter = width / 2f
        val yCenter = height / 2f
        val radius = min(xCenter, yCenter)
        val arcWidthHeight = ((radius - radiusOffset) * 2f)
        val arcSize = Size(arcWidthHeight, arcWidthHeight)

        val remainderColor = Color.White.copy(alpha = 0.25f)
        val completedColor = Color.Gray

        val whitePercent =
            min(1f, elapsedTime.toFloat() / totalTime.toFloat())
        val greenPercent = 1 - whitePercent

        drawArc(
            completedColor,
            270f,
            -greenPercent * 360f,
            false,
            topLeft = Offset(radiusOffset, radiusOffset),
            size = arcSize,
            style = Stroke(width = strokeSize.value)
        )

        drawArc(
            remainderColor,
            270f,
            whitePercent * 360,
            false,
            topLeft = Offset(radiusOffset, radiusOffset),
            size = arcSize,
            style = Stroke(width = strokeSize.value)
        )

    })
}

fun calculateRadiusOffset(strokeSize: Float, dotStrokeSize: Float, markerStrokeSize: Float)
        : Float {
    return max(strokeSize, max(dotStrokeSize, markerStrokeSize))
}