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
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun TimePicker(vm: MainViewModel = viewModel()) {

    val input1: String by vm.time1.observeAsState("")
    val input2: String by vm.time2.observeAsState("")
    val input3: String by vm.time3.observeAsState("")


    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        TextField(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            value = input1,
            onValueChange = { vm.setTime1(it) },
            placeholder = { Text(text = "HH") },
            modifier = Modifier
                .padding(4.dp)
                .width(60.dp),
        )
        Spacer(modifier = Modifier.width(10.dp))
        TextField(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            value = input2,
            onValueChange = { vm.setTime2(it) },
            placeholder = { Text(text = "MM") },
            modifier = Modifier
                .padding(4.dp)
                .width(60.dp),

            )
        Spacer(modifier = Modifier.width(10.dp))
        TextField(
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            value = input3,
            onValueChange = { vm.setTime3(it) },
            placeholder = { Text(text = "SS") },
            modifier = Modifier
                .padding(4.dp)
                .width(60.dp),
        )
    }
}